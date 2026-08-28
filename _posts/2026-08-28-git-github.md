layout: single
---
layout: single
title: "어제 배운 Git 정리"
categories: Cloud
date: 2026-08-28
comments: true
---

## 오늘 배운 것

Git은 **저장 지점을 남기고 되돌릴 수 있게** 해주는 도구다.

### 커밋까지의 세 단계

| 단계 | 명령 | 하는 일 |
|---|---|---|
| 1 | `git add` | 커밋에 담을 것을 고른다 |
| 2 | `git commit` | 고른 것을 한 덩어리로 기록한다 |
| 3 | `git push` | 기록을 GitHub로 올린다 |

가장 헷갈렸던 건 `add`와 `commit`이 왜 나뉘어 있는가였다.
파일을 두 개 고쳤는데 **하나만 커밋하고 싶을 때**가 있기 때문이었다.

## 막혔던 것

`git log`를 쳤더니 화면이 멈춘 것처럼 보였다.
알고 보니 기록을 보여주는 화면이 열린 것이었고, `q`를 누르면 빠져나온다.

## 내일 볼 것

- 마크다운으로 글 쓰기
- 만든 것을 인터넷에 올리기

---
# 개념

💡 **오늘의 한 줄 요약**: CLI 환경의 이해를 시작으로, Git의 3단계 작업 영역을 통제하고 GitHub을 이용한 브랜치 병합(Merge), 충돌 해결, 그리고 Git Flow 협업 전략까지 완벽하게 마스터한 하루.

### 📚 1. 핵심 개념 (Theory)

단순한 명령어 암기가 아니라, 왜 CLI를 써야 하며 분산 버전 관리 시스템이 왜 현업에서 필수적인지 그 배경과 구조를 이해하는 것이 핵심입니다.
> 
- **CLI (Command Line Interface) vs GUI**
    - **개념:** 마우스로 그래픽을 클릭하는 GUI(Graphic User Interface)와 달리, 명령어(Text)로 컴퓨터와 상호작용하는 환경. 과거 Unix, MS-DOS 시절부터 이어져 옴.
    - **사용 이유:** GUI는 직관적이지만 '60%만 축소해 줘' 같은 세밀한 제어나 자동화 스크립트 작성에 한계가 있음. Git, Docker, AWS 등 핵심 서버 기술은 모두 빠르고 강력한 CLI 기반으로 동작함.
- **Git의 3가지 작업 영역과 HEAD**
    - **Working Directory (작업 폴더):** 실제 코드를 작성하고 파일을 수정하는 로컬 디렉토리.
    - **Staging Area (무대):** 커밋하기 전, 버전 관리에 포함할 파일들을 임시로 올려두는 공간. (추적 준비 완료)
    - **Repository (저장소):** 커밋(버전)이 영구적으로 기록되는 공간. 내 컴퓨터에 있으면 Local, GitHub에 있으면 Remote Repository.
    - **HEAD:** 현재 내가 작업하고 있는 위치(브랜치나 커밋)를 가리키는 일종의 포인터.
- **브랜치(Branch)와 병합(Merge) 전략**
    - **브랜치:** 메인 코드를 안전하게 유지하면서 새로운 기능 개발이나 버그 수정을 독립적으로 진행할 수 있게 복제해 둔 작업 공간.
    - **Fast-forward Merge:** 분기된 브랜치에서만 추가 작업이 일어나, 충돌 없이 단순히 포인터만 앞으로 이동하며 합쳐지는 병합.
    - **3-way Merge:** 메인 브랜치와 분기된 브랜치 양쪽 모두에서 작업이 발생해, 두 갈래의 공통 조상을 기준으로 새로운 병합 커밋(Merge Commit)을 만들어 합치는 방식.
- **실전 협업 브랜치 모델 (Git Flow vs GitHub Flow)**
    - **Git Flow:** `main`(출시용), `develop`(개발용), `feature`(기능), `release`(QA용), `hotfix`(긴급수정) 5가지 브랜치로 엄격하게 관리. 주기적 릴리즈가 있는 대형 프로젝트에 적합.
    - **GitHub Flow:** `main`과 `feature` 브랜치만 사용하는 단순한 구조. 수시로 배포(CI/CD)가 일어나는 애자일한 조직이나 소규모 팀에 적합. PR(Pull Request) 중심 협업.

### 💻 2. 실습 및 코드 (Practice)

터미널(Git Bash)과 IntelliJ IDEA 환경을 넘나들며 파일 제어부터 버전 관리, 원격지 연동까지 수행한 핵심 명령어 모음입니다.

```bash
# 1. CLI 파일 및 폴더 제어 기본
pwd                     # 현재 작업 중인 디렉토리 절대 경로 확인
ls -a                   # 숨김 폴더(.git 등)까지 포함하여 목록 확인
mkdir [폴더명]             # 새 폴더(디렉토리) 생성
cd [경로]                 # 디렉토리 이동 (cd .. 은 상위 폴더로 이동)
touch [파일명.확장자]       # 빈 파일 생성

# 2. Git 초기 설정 및 로컬 저장소 생성
git config --global user.name "이름"
git config --global user.email "깃허브 이메일"
git init                # 현재 폴더를 Git이 관리하는 워킹 디렉토리로 초기화 (.git 폴더 생성)

# 3. 스테이징 및 커밋 (버전 생성)
git status              # 현재 파일들의 추적 상태(Untracked, Modified 등) 확인
git add .               # 변경된 모든 파일을 Staging Area로 올림
git commit -m "메시지"    # 스테이징된 파일을 Local Repository에 버전으로 저장
git commit -am "메시지"   # add와 commit을 한 번에 실행 (단, 최초 추적된 파일에만 적용 가능)

# 4. 브랜치 생성 및 이동
git branch [브랜치명]       # 새 브랜치 생성
git switch [브랜치명]       # 해당 브랜치로 이동 (과거에는 checkout을 사용)
git switch -c [브랜치명]    # 브랜치 생성과 동시에 이동

# 5. 작업 취소 및 되돌리기
git restore [파일명]       # 커밋 전(워킹 디렉토리)의 변경 사항을 원래대로 되돌림
git revert [커밋 해시ID]    # 이미 커밋된 특정 버전을 취소하되, '취소했다'는 내역을 새로운 커밋으로 남김 (안전함)

# 6. 원격 저장소 연동 및 동기화
git remote add origin [GitHub URL] # 로컬 저장소를 원격 저장소에 연결
git push origin main               # 로컬의 커밋 내역을 원격 저장소(main 브랜치)로 밀어 올림
git pull origin main               # 원격 저장소의 최신 변경 사항을 당겨와 로컬에 자동 병합 (fetch + merge)
git clone [GitHub URL]             # 이미 존재하는 원격 프로젝트를 내 컴퓨터로 통째로 복제
```

### 🚨 3. 트러블슈팅 및 주의사항 (Troubleshooting)

실무와 팀 미션 중 가장 빈번하게 마주치는 충돌과 실수들입니다.

- 🔴 에러 상황 (Error): `git commit -am` 명령어 사용 시 "Untracked files..." 오류 발생
    - 새로 만든 파일(예: `screens.txt`)을 작성한 직후, `add` 없이 바로 `am` 옵션으로 커밋을 시도해 Git이 해당 파일을 인지하지 못함.
- 🟢 해결 방법 (Solution)
    - `am` 옵션은 한 번이라도 Git의 추적(Tracked)을 받은 파일의 '수정'에만 동작함. 새로 생성한 파일은 반드시 최초 1회 `git add [파일명]`을 통해 명시적으로 스테이징 에어리어에 올려주어야 함.
- 🔴 에러 상황 (Error): 브랜치 병합 시 `CONFLICT (content): Merge conflict in ...` 발생
    - 메인 브랜치와 피처 브랜치(예: `feature/gorilla`) 양쪽에서 같은 파일(`tiger.md` 등)의 동일한 라인을 서로 다르게 수정하고 `git merge`를 시도함. 컴퓨터가 어느 쪽 코드를 살려야 할지 판단하지 못해 병합이 중단됨.
- 🟢 해결 방법 (Solution)
    - 충돌이 난 파일을 열어보면 `<<<<<<< HEAD` (현재 브랜치 내용) `=======` (구분선) `>>>>>>> feature/gorilla` (가져온 브랜치 내용) 형태로 표시되어 있음.
    - 개발자가 직접 코드를 비교하여 불필요한 줄과 Git이 삽입한 충돌 기호(`<, =, >`)를 모두 지우고 원하는 최종 코드로 문서를 수정함. (IntelliJ IDEA의 Merge Tool 창을 활용하면 GUI로 쉽게 선택 가능)
    - 수정 완료 후 다시 `git add .` 와 `git commit -m "충돌 해결 완료"`를 입력하면 3-way Merge가 완료됨.
- 🔴 에러 상황 (Error): 클론(Clone) 받은 후 `git log`나 상태가 확인되지 않는 문제
    - 원격 저장소를 `git clone`으로 가져온 후, 터미널 경로가 생성된 프로젝트 폴더 바깥에 머물러 있어 Git 명령어가 먹히지 않음.
- 🟢 해결 방법 (Solution)
    - 클론을 완료하면 `cd [프로젝트 폴더명]` 명령어를 통해 반드시 `.git` 숨김 폴더가 존재하는 내부 경로까지 진입한 뒤 작업해야 함.

### 🎯 4. 회고

-  팀 미션 회고 작성 및 다른 팀과 결과 공유하기.
-  자율 실습: 내 개인 GitHub 계정에 블로그 기획 저장소 만들고 글 초안 3편 커밋 완료하기.

# Claude Git과 Github 실습 문제

Claude Artifact

## 1. 저장소 세팅 및 기본 커밋 (1-1, 1-2)

**1-1. 내 블로그 저장소를 만들고 첫 글 3편 계획하기**

- **상황:** 로컬(내 컴퓨터)에 새로운 Git 저장소를 만들고, 파일을 커밋한 뒤 깃허브(원격 저장소)에 처음 연결하여 올리는 전체 흐름을 연습합니다.
- **핵심 명령어:**
    
    ```bash
    mkdir my-blog-plan      # 폴더 만들기
    cd my-blog-plan         # 폴더로 이동
    git init                # Git 저장소 초기화
    
    # (posts.txt 파일 생성 및 1편 작성 후)
    git add posts.txt       # 스테이징(올릴 준비)
    git commit -m "1편 제목"  # 커밋(저장)
    # 위 add와 commit 과정을 3편까지 총 3번 반복합니다.
    
    git remote add origin [깃허브_빈_저장소_주소] # 원격 저장소 연결
    git branch -M main      # 기본 브랜치 이름을 main으로 설정
    git push -u origin main # 깃허브로 밀어 올리기
    ```
    

**1-2. status만 보고 무엇이 담길지 맞히기**

- **상황:** `git status`를 쳤을 때 파일의 상태가 초록색(커밋 준비됨), 빨간색(수정됐지만 준비 안 됨), Untracked(Git이 모름)로 어떻게 나뉘는지 파악합니다.
- **핵심 명령어:**
    
    ```bash
    # 지시대로 3개의 파일 상태를 만든 후
    git status
    ```
    
    - `Changes to be committed:` **posts.txt** (커밋될 파일)
    - `Changes not staged for commit:` **about.txt** (수정되었으나 스테이징 안 된 파일)
    - `Untracked files:` **todo.txt** (새로 만들어 Git이 아직 추적하지 않는 파일)

### 2. 선택적 추적과 예외 처리 (1-3, 1-4)

**1-3. 글 초안 둘 중 완성된 하나만 올리기**

- **상황:** 여러 파일이 변경되었더라도, 내가 원하는 특정 파일 하나만 골라서 커밋하는 연습입니다.
- **핵심 명령어:**
    
    ```bash
    git add draft-1.txt       # 완성된 1편만 콕 집어서 스테이징
    git commit -m "1편 완성"
    git push
    ```
    

**1-4. 아직 남에게 보이기 싫은 것 막기**

- **상황:** 개인 메모나 비밀 파일이 깃허브에 올라가지 않도록 `.gitignore` 파일을 활용하여 Git의 감시망에서 영구 제외하는 방법입니다.
- **핵심 명령어:**
    
    ```bash
    # .gitignore 파일을 만들고 그 안에 secret-draft.txt와 my-memo.txt를 적어 넣습니다.
    git add .gitignore
    git commit -m "무시할 파일 목록 추가"
    git push
    ```
    

### 3. 브랜치 흐름과 로그 확인 (1-5, 1-6)

**1-5. 브랜치를 만드는 순서 바꿔보기**

- **상황:** "커밋하지 않은 변경사항"은 브랜치를 이동해도 그대로 따라가고, "커밋을 완료한 변경사항"은 해당 브랜치에만 남는다는 Git의 중요한 성질을 비교합니다.
- **핵심 명령어:**
    
    ```bash
    # 가. 브랜치를 먼저 만들고 이동한 뒤 커밋하는 경우
    git switch -c branch-a
    git add about.txt
    git commit -m "소개 수정"
    
    # 나. 파일을 먼저 수정하고 나중에 브랜치를 만들어 커밋하는 경우
    # about.txt 파일 수정 후
    git switch -c branch-b
    git add about.txt
    git commit -m "소개 수정"
    
    # 가, 나 모두 main 브랜치로 돌아가면 수정한 내용은 보이지 않습니다.
    git switch main
    ```
    

**1-6. 기록을 세 가지 방법으로 읽기**

- **상황:** 브랜치가 갈라졌다가 다시 병합(Merge)되는 과정을 만들고, 이를 로그(Log) 명령어로 어떻게 시각화해서 볼 수 있는지 확인합니다.
- **핵심 명령어:**
    
    ```bash
    # 갈라지는 기록 만들기
    git switch -c post-new
    git add posts.txt
    git commit -m "새 글 추가"
    
    git switch main
    git add about.txt
    git commit -m "소개 수정"
    git merge post-new
    
    # 3가지 방식의 로그 확인
    git log                   # 가장 자세한 기본 로그
    git log --oneline         # 커밋당 한 줄로 요약된 로그
    git log --oneline --graph # 브랜치가 갈라지고 합쳐진 선(|, \, /) 모양까지 보여주는 로그
    ```