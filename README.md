# 예린님의 학습 노트

> 174일 부트캠프(2026.08.26 ~ 2027.02.16)를 하루 단위로 기록하는 학습 블로그

**🔗 https://paaaraaaeaaa.github.io/my-blog/**

매일 배운 내용과 부딪힌 시행착오를 남겨서, 몇 달 뒤 다시 읽었을 때 "그때보다 늘었다"를 확인할 수 있는 블로그를 만들고 있어요.

<br>

## 목차

- [사이트 구성](#사이트-구성)
- [주요 기능](#주요-기능)
- [기술 스택](#기술-스택)
- [폴더 구조](#폴더-구조)
- [글 쓰는 법](#글-쓰는-법)
- [데이터 파일로 관리하는 것들](#데이터-파일로-관리하는-것들)
- [디자인 시스템](#디자인-시스템)
- [로컬에서 실행하기](#로컬에서-실행하기)
- [AI Agent와 함께 쓰기](#ai-agent와-함께-쓰기)

<br>

## 사이트 구성

부트캠프는 **6개 모듈**로 나뉘고, 세 섹션은 모두 모듈 번호(`module:`)로 연결돼요.

```
모듈 N ──┬── ☁️ Cloud      일차별 학습노트        → 주 단위 달력
         ├── 🗄️ Database   문제 ← 풀이 글 + 자료  → 문제 카드 아래 레벨별 풀이
         └── 🚀 Projects   모듈 결과물 + 연동기    → 모듈 타임라인
```

| 페이지 | 내용 |
|---|---|
| **홈** | 오늘이 몇 일차인지, 수료까지 D-day, 출발부터 수료까지의 궤적, 이번 주 기록, 섹션 바로가기, 최근 글, GitHub 잔디 |
| **Cloud** | 6개 모듈 스테이지를 골라 보고, 모듈마다 학습노트를 주차 × 월~금 달력으로 봐요 |
| **Database** | 받은 문제와 직접 푼 풀이(Lv1 → Lv2 → Bonus)를 한 카드에 묶고, 게임·아티팩트 같은 자료를 섹션별로 모아요 |
| **Projects** | 모듈마다 하나씩 나오는 결과물(총 6개)과, 결과물을 만들며 가장 오래 막혔던 연동 과정을 기록한 연동기 |

<br>

## 주요 기능

**홈 대시보드**
- `N일차`는 주말과 공휴일(`_data/holidays.yml`)을 뺀 평일 기준, 진행률은 달력 기준으로 자동 계산
- 이번 주 월~금 중 학습노트를 쓴 날을 점으로 표시
- 수료까지 모듈 진척 막대 (결과물 글이 있으면 완료로 자동 판단)
- 자주 쓴 태그 · 기술 스택 아이콘을 글 태그에서 자동 추출
- 매번 바뀌는 개발 명언

**검색** (상단 돋보기)
- 제목 · 태그 · 요약 · 본문을 한 번에 검색, 한글 단어 중간도 찾아요 ("브랜치" → "브랜치를", "브랜치별")
- 결과를 **전체 / Cloud / Database / Projects** 탭으로 나눠서 보여줘요
- 검색어 하이라이트와 본문 속 해당 부분 미리보기, Enter로 첫 결과 이동, Esc로 닫기

**Database 섹션 필터**
- 섹션 칩을 누르면 그 섹션만, 같은 칩을 다시 누르거나 ↻ 전체를 누르면 전부 보여요
- `/database/#problems`처럼 주소로 들어오면 그 섹션만 필터된 상태로 열려요

**게시글에서 자동으로 적용되는 것**

| 쓰는 방법 | 결과 |
|---|---|
| 문단·목록 맨 앞에 💡 🔴 🟢 ⚠️ 🚨 | 색 콜아웃 박스 |
| `- **용어**` + 하위 목록 | 개념 카드 |
| 15줄 넘는 코드 블록 | 접기 / 펼치기 |
| 모든 코드 블록 | 복사 버튼 |
| ` ```mermaid ` | 다이어그램 (사이트 색으로) |
| 표 | 좁은 화면에서 가로 스크롤 |

그 밖에 오른쪽 목차(현재 읽는 위치 표시), 이전 / 다음 글(같은 카테고리 · 같은 주제 안에서), 조회수 뱃지, utterances 댓글이 있어요.

<br>

## 기술 스택

| 구분 | 사용 |
|---|---|
| 정적 사이트 | [Jekyll](https://jekyllrb.com/) + [GitHub Pages](https://pages.github.com/) |
| 테마 | [Minimal Mistakes](https://github.com/mmistakes/minimal-mistakes) (`remote_theme`) 위에 자체 디자인 시스템을 덮어씀 |
| 서체 | [Pretendard](https://github.com/orioncactus/pretendard), [JetBrains Mono](https://www.jetbrains.com/lp/mono/) |
| 다이어그램 | [Mermaid](https://mermaid.js.org/) |
| 댓글 | [utterances](https://utteranc.es/) (GitHub Issues 기반) |
| 위젯 | [visitor-badge](https://visitor-badge.laobi.icu/), [skillicons](https://skillicons.dev/), [streak-stats](https://github.com/DenverCoder1/github-readme-streak-stats), [ghchart](https://ghchart.rshah.org/) |

<br>

## 폴더 구조

```
my-blog/
├── index.md                  # 홈
├── cloud.md                  # Cloud 섹션
├── Database.md               # Database 섹션
├── Projects.md               # Projects 섹션
├── _config.yml               # 사이트 설정 (테마, 시간대, 댓글, 공개 제외 파일)
│
├── _posts/
│   ├── Cloud/학습노트/       # 일차별 학습노트 (type: daily)
│   ├── Cloud/문제풀이/       # 문제 풀이 devlog (type: practice) → Database 페이지에 표시
│   └── Projects/             # 모듈 결과물 + 연동기
│
├── _data/
│   ├── modules.yml           # 모듈 번호 → 이름
│   ├── holidays.yml          # N일차 계산에서 뺄 공휴일
│   ├── database_links.yml    # Database 자료 목록 (문제 · 게임 · 아티팩트 …)
│   ├── database_sections.yml # Database 섹션 순서 · 아이콘 · 색
│   ├── skills.yml            # 태그 → 기술 스택 아이콘 매핑
│   ├── module_schedule.yml   # (선택) 모듈 시작 · 종료일
│   ├── now.yml               # 홈 🔥 상태 문구
│   └── navigation.yml        # 상단 메뉴
│
├── _includes/
│   ├── head/custom.html      # 서체 · 디자인 CSS 연결
│   ├── footer/custom.html    # 콜아웃 · 코드 복사 · Mermaid 등 자동 기능 스크립트
│   ├── post_pagination.html  # 이전 / 다음 글 규칙
│   └── toc.html              # 목차 (## 부터)
│
├── assets/
│   ├── css/tokens.css        # 디자인 토큰 (색 · 글꼴 · 간격) — 유일한 값 저장소
│   ├── css/site.css          # 컴포넌트 스타일
│   ├── js/lunr/              # 검색 데이터 · 검색 엔진 (테마 파일을 덮어씀)
│   └── images/               # 프로필, 링크 미리보기 이미지
│
├── DESIGN.md                 # 디자인 시스템 문서
├── CLAUDE.md                 # AI Agent 글쓰기 · 작업 규칙
├── .claude/skills/           # Claude Code 스킬 7개 (/learn, /note, /practice, /project, /series, /resource, /module)
└── .claude/agents/           # post-checker 검사 에이전트
```

<br>

## 글 쓰는 법

파일 이름은 `YYYY-MM-DD-영문-제목.md`, 위치는 `_posts/` 아래예요. 디자인이 읽는 front matter 값은 아래와 같아요.

| 글 종류 | 필수 | 선택 |
|---|---|---|
| Cloud 학습노트 | `categories: [Cloud]` `type: daily` `module: N` `excerpt` | `tags` (달력 칸에 앞 2개 표시) |
| Cloud 문제풀이 | `type: practice` `module: N` `topic` `level_order` | 제목을 `Lv1 · 제목`으로 쓰면 레벨 라벨이 자동 분리 |
| Projects 결과물 | `categories: [Projects]` `module: N` (type 없음) | `project_name` `banner_emoji` `live_url` `tags` |
| Projects 연동기 | `type: practice` `module: N` `topic` `level_order` | `tags` |

학습노트 예시:

```yaml
---
title: "21일차"
excerpt: "오늘 배운 것을 한두 문장으로. 목록과 달력 칸에 이 문장이 보여요."
layout: single
categories: [Cloud]
type: daily
module: 1
date: 2026-09-23
tags: [Git, Pull Request]
comments: true
toc: true
toc_sticky: true
mermaid: true
---
```

- 글 안에 `<style>`이나 글자 크기 조절 코드는 넣지 않아요. 모든 글이 같은 크기로 읽혀야 해요.
- 글 맨 위 `# 부제목`은 목차에서 빠지고, `##`부터 목차가 돼요.
- 글 구조는 STAR(상황 → 과제 → 행동 → 결과) 기법을 따르고, 끝에 "더 학습하면 좋은 개념"과 "참고 자료"를 붙여요. 자세한 규칙은 [`CLAUDE.md`](CLAUDE.md)에 있어요.

<br>

## 데이터 파일로 관리하는 것들

페이지 파일을 고치지 않고 `_data/`의 YAML만 수정하면 반영돼요.

| 하고 싶은 것 | 고칠 파일 |
|---|---|
| 모듈 이름 바꾸기 | `modules.yml` |
| 공휴일 추가 (N일차 계산) | `holidays.yml` |
| Database에 자료 추가 | `database_links.yml`에 `category` `title` `url`(또는 `file`) `description` |
| 문제에 풀이 글 연결 | `database_links.yml`의 문제 항목 `topics`에 풀이 글의 `topic` 값 추가 |
| Database에 새 섹션 추가 | `database_links.yml`에 새 `category`를 쓰고, `database_sections.yml`에 `name` `emoji` `tone` `desc` 한 줄 |
| 기술 스택 아이콘 추가 | `skills.yml`에 skillicons `id`와 태그 `aliases` |
| 모듈 진척 막대를 날짜로 채우기 | `module_schedule.yml`에 `start` `end` |
| 홈 🔥 상태 문구 바꾸기 | `now.yml`의 `status` |

<br>

## 디자인 시스템

다크 테마 위에 네온 라일락을 포인트로 쓰는 **"Mission Log"** 콘셉트예요. 부트캠프를 하나의 비행 임무로 보고, 블로그는 그 비행 기록이에요.

- **모든 색 · 글꼴 · 간격은 `assets/css/tokens.css`에만** 있어요. `site.css`와 페이지 파일은 `var(--...)`만 써요.
- 분위기를 바꾸고 싶으면 `tokens.css`만 고치면 사이트 전체에 한 번에 반영돼요.
  예: `--c-signal: #A594FF;` → `#7CF5C8`(민트), `#FF9F6B`(코랄)
- 카테고리 색: Cloud 스카이 `#62CFFF` · Database 핑크 `#FF8AC8` · Projects 라임 `#C9F26B`

컴포넌트 목록, 페이지 구조, 체크리스트는 [`DESIGN.md`](DESIGN.md)에 정리되어 있어요.

<br>

## 로컬에서 실행하기

[Ruby](https://www.ruby-lang.org/)와 Bundler가 필요해요.

```bash
bundle install
bundle exec jekyll serve
```

브라우저에서 `http://localhost:4000/my-blog/`로 열면 돼요. `main` 브랜치에 푸시하면 GitHub Pages가 1~2분 안에 자동으로 배포해요.

<br>

## AI Agent와 함께 쓰기

이 저장소는 [Claude Code](https://docs.claude.com/en/docs/claude-code/overview)와 함께 쓰도록 구성되어 있어요.

| 파일 | 역할 |
|---|---|
| [`CLAUDE.md`](CLAUDE.md) | 글 구조, 마크다운 규칙, 게시 규칙, 디자인 규칙 |
| [`DESIGN.md`](DESIGN.md) | 디자인 토큰과 컴포넌트, 페이지 구조 |
| `/learn` | 새로 배우는 것을 질문으로 한 걸음씩 |
| `/note` | 배운 것을 일차 학습노트로 정리해서 올리기 |
| `/practice <링크>` | 문제를 읽고 회고형 devlog로 정리 + Database 문제 카드에 연결 |
| `/project` | 모듈이 끝날 때 결과물 회고 |
| `/series` | 결과물의 연동기 한 편 |
| `/resource <링크>` | 게임·아티팩트 등 자료를 Database에 추가 |
| `/module` | 모듈 시작·종료 때 홈 문구·일정 정리 |
| `post-checker` 에이전트 | 커밋 전에 글 규칙을 검사 (파일은 고치지 않음) |

<br>

---

© 2026 김예린 · [GitHub](https://github.com/paaaraaaeaaa)
