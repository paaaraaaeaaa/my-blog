# C:\Lecture 의 수업 폴더에서 텍스트 파일만 골라 assets/code/lecture/ 로 복사한다.
# Cloud 모듈 패널의 "원본 코드" 팝업이 이 폴더를 읽는다 (_data/lecture_sources.yml 참고).
#
# 사용법 (저장소 루트에서):
#   powershell -NoProfile -ExecutionPolicy Bypass -File scripts/sync-lecture.ps1
#
# - 복사하는 것: 아래 $ext 확장자의 파일. 이미지·jar·바이너리는 복사하지 않는다.
# - 건너뛰는 폴더: .git .idea .gradle .claude .github out build node_modules target (경로 어디에 있든)
# - .properties 의 password/secret/token 줄은 값을 ******** 로 가려서 복사한다. 공개 저장소이므로.
#   (.java 안에 적힌 수업용 로컬 DB 계정은 가리지 않는다. 8일차 글에도 이미 공개된 값이다.)
# - 안전장치: 원본 폴더가 하나도 없으면 아무것도 지우지 않고 멈춘다. 먼저 임시 폴더에 복사하고,
#   성공하면 assets/code/lecture/ 를 통째로 교체한다. 그래서 이 폴더 안에 손으로 넣은 파일은 사라진다.
param(
  [string]$Source = 'C:\Lecture',
  [string]$Dest = (Join-Path $PSScriptRoot '..\assets\code\lecture')
)
$ErrorActionPreference = 'Stop'

$courses  = '01_Git', '01_HTML', '02_CSS', '03_JAVA', '04_MySQL', '05_JDBC', '06_Servlet'
$ext      = '.java', '.html', '.css', '.sql', '.md', '.xml', '.gradle', '.jsp', '.js', '.txt', '.properties'
$skipDirs = '.git', '.idea', '.gradle', '.claude', '.github', 'out', 'build', 'node_modules', 'target'
$maxBytes = 300KB
$utf8 = New-Object System.Text.UTF8Encoding($false)

# 1) 지울 위치가 저장소 안의 assets\code\lecture 가 맞는지 확인
$repoRoot = [System.IO.Path]::GetFullPath((Join-Path $PSScriptRoot '..'))
$Dest = [System.IO.Path]::GetFullPath($Dest).TrimEnd('\')
$expected = Join-Path $repoRoot 'assets\code\lecture'
if ($Dest -ne $expected) { throw "Dest must be $expected (got $Dest)" }

# 2) 원본이 하나라도 있어야 시작한다
$found = $courses | Where-Object { Test-Path (Join-Path $Source $_) }
if (-not $found) { throw "No course folder found under $Source. Nothing was changed." }

# 3) 임시 폴더에 복사
$tmp = Join-Path ([System.IO.Path]::GetTempPath()) ('lecture-sync-' + [guid]::NewGuid().ToString('N'))
New-Item -ItemType Directory -Path $tmp | Out-Null
$total = 0
try {
  foreach ($c in $courses) {
    $src = Join-Path $Source $c
    if (-not (Test-Path $src)) { Write-Host "skip: $c (not found)"; continue }
    $srcFull = (Resolve-Path $src).Path
    $count = 0; $masked = 0
    Get-ChildItem -LiteralPath $srcFull -Recurse -File | ForEach-Object {
      $rel = $_.FullName.Substring($srcFull.Length + 1)
      $parts = $rel -split '\\'
      $dirs = if ($parts.Length -gt 1) { $parts[0..($parts.Length - 2)] } else { @() }
      if ($dirs | Where-Object { $skipDirs -contains $_ }) { return }
      if ($ext -notcontains $_.Extension.ToLower()) { return }
      if ($_.Length -gt $maxBytes) { Write-Host "  too large, skipped: $c\$rel"; return }
      $to = Join-Path (Join-Path $tmp $c) $rel
      New-Item -ItemType Directory -Path (Split-Path $to) -Force | Out-Null
      if ($_.Extension -ieq '.properties') {
        $text = [System.IO.File]::ReadAllText($_.FullName, $utf8)
        $new = [regex]::Replace($text, '(?im)^(\s*[\w.\-]*(?:password|passwd|pwd|secret|token)[\w.\-]*\s*[=:]\s*)[^\r\n]*', '${1}********')
        if ($new -ne $text) { $masked++ }
        [System.IO.File]::WriteAllText($to, $new, $utf8)
      } else {
        Copy-Item -LiteralPath $_.FullName -Destination $to
      }
      $count++
    }
    $total += $count
    Write-Host ("{0,-11} {1,4} files copied (passwords masked in {2})" -f $c, $count, $masked)
  }

  # 4) 사후 점검: .properties 에 가려지지 않은 비밀번호 줄이 남았으면 교체하지 않고 실패한다
  $leak = Get-ChildItem -LiteralPath $tmp -Recurse -File -Filter *.properties | Select-String -Pattern '(?i)^\s*[\w.\-]*(password|passwd|pwd|secret|token)[\w.\-]*\s*[=:]\s*(?!\*{8}\s*$)\S'
  if ($leak) { $leak | ForEach-Object { Write-Host "  not masked: $($_.Path):$($_.LineNumber)" }; throw 'Unmasked secret found. Dest was not changed.' }
  if ($total -eq 0) { throw 'Copied 0 files. Dest was not changed.' }

  # 5) 통째로 교체
  if (Test-Path $Dest) { Remove-Item -LiteralPath $Dest -Recurse -Force }
  New-Item -ItemType Directory -Path (Split-Path $Dest) -Force | Out-Null
  Move-Item -LiteralPath $tmp -Destination $Dest
  Write-Host "done: $total files -> $Dest"
} finally {
  if (Test-Path $tmp) { Remove-Item -LiteralPath $tmp -Recurse -Force }
}
