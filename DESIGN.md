# DESIGN.md — 예린님의 학습 노트 디자인 시스템

이 문서는 블로그의 **모든 시각 규칙의 기준**이다. 사람이든 AI Agent(Claude Code)든, 화면·페이지·글의 스타일을 만들거나 고칠 때는 이 문서를 먼저 읽고 따른다.

- 값(색·글꼴·간격): `assets/css/tokens.css`
- 컴포넌트 스타일: `assets/css/site.css`
- 연결부: `_includes/head/custom.html` (위 두 파일을 불러오기만 함)
- 작업 방법: 디자인을 바꿀 때는 `/design` 스킬로 작업하고, 커밋 전에 `design-guard` 에이전트로 검사한다.

---

## 1. 콘셉트: Terminal Log

174일 부트캠프를 **터미널에 남기는 작업 로그**로 표현한다. 개발자가 매일 보는 화면(터미널, GitHub)의 문법을 빌려서, 꾸밈 없이 정보가 바로 읽히게 한다.

- 배경은 터미널 먹색(다크) / 흰색(라이트), 포인트는 **프롬프트 초록** 하나. 카테고리는 시안(Cloud)·마젠타(Database)·앰버(Projects).
- UI(제목·메뉴·라벨·숫자·날짜)는 **고정폭 글꼴**(`--font-ui`), 게시글 본문은 읽기 편한 **Pretendard**(`--font-sans`).
- 터미널 문법을 표현 수단으로 쓴다: `$ 명령` 프롬프트, `## 제목` 기호, `[1/3]` `[done]` 대괄호, `./cloud` 경로, `ls -l` 목록, `█░` ASCII 막대, 깜빡이는 커서.

### 디자인 원칙 (우선순위 순)

1. **한눈에 들어온다.** 목록은 "날짜 | 제목 + 한 줄" 행 구조. 카드 격자는 정말 필요한 곳(달력, 자료)에만.
2. **읽기 편하다.** 본문 17~18px, 줄간격 1.8, 한 줄 최대 44rem. 긴 한글 본문에는 고정폭 글꼴을 쓰지 않는다.
3. **AI가 만든 티를 내지 않는다.** 아래는 쓰지 않는다.
   - UI 속 이모지 아이콘(동그란 배경에 넣은 ☁️🚀 등). 이모지는 게시글 콜아웃 표시(💡🔴🟢⚠️🚨)에만.
   - 빛 번짐(glow, radial-gradient 배경), 그라데이션 글자
   - 알약 모양(999px) 칩·버튼. 모서리는 최대 6px.
   - 박스 안에 박스를 겹겹이. 구분은 선(`--c-line`)과 면 색 차이로.
4. **색은 정보다.** 카테고리 색 3개, 상태 색 4개 외에 새 색을 만들지 않는다.
5. **말투는 담백하게.** UI 라벨은 짧은 명령어·영문 소문자(`done`, `running`, `copy link`, `cd ~/cloud`) 또는 짧은 한국어. 감탄사·이모지 문구는 쓰지 않는다.

---

## 2. 토큰

모든 값은 `tokens.css`의 CSS 변수로만 쓴다. **`site.css`와 페이지 파일에 `#hex`, `rgb()`를 직접 쓰지 않는다.**

### 2.1 색

| 토큰 | 값 | 용도 |
|---|---|---|
| `--c-bg` | `#0D1117` | 페이지 배경 |
| `--c-surface` | `#10151C` | 패널·카드·터미널 창 |
| `--c-surface-2` | `#161B22` | hover, 표 머리, 창 제목줄 |
| `--c-code-bg` | `#0A0E14` | 코드 블록 |
| `--c-line` | `#21262D` | 기본 구분선 |
| `--c-line-strong` | `#30363D` | 버튼·칩 테두리 |
| `--c-text` | `#E6EDF3` | 본문, 제목 |
| `--c-text-2` | `#A9B4BF` | 보조 설명, 요약 |
| `--c-text-3` | `#7D8590` | 날짜, 캡션, 기호(`##`, `[ ]`) |
| `--c-signal` | `#7EE787` | **메인 포인트(프롬프트 초록)**: `$`, 링크, 활성, 진행 막대, 포커스 |

**카테고리 색** — 상단 탭 점, 섹션 머리, 홈 바로가기 카드 윗선에만 쓴다.

| 토큰 | 값 | 카테고리 |
|---|---|---|
| `--c-cloud` | `#79C0FF` | Cloud (시안) |
| `--c-database` | `#F778BA` | Database (마젠타) |
| `--c-projects` | `#E3B341` | Projects (앰버) |

**상태 색** — 콜아웃 박스에만 쓴다.

| 토큰 | 값 | 콜아웃 |
|---|---|---|
| `--c-info` | `#79C0FF` | 💡 요약 |
| `--c-danger` | `#FF7B72` | 🔴 에러 |
| `--c-success` | `#7EE787` | 🟢 해결 |
| `--c-warning` | `#E3B341` | ⚠️ 🚨 주의 |

### 2.1-1 라이트 테마 값

상단바 해·달 버튼으로 모든 페이지에서 바꿀 수 있다. 선택은 브라우저에 저장되고, 고른 적이 없으면 기기 설정(다크/라이트)을 따른다.

| 토큰 | 다크 | 라이트 |
|---|---|---|
| `--c-bg` | `#0D1117` | `#FFFFFF` |
| `--c-surface` / `--c-surface-2` | `#10151C` / `#161B22` | `#F6F8FA` / `#EAEEF2` |
| `--c-line` / `--c-line-strong` | `#21262D` / `#30363D` | `#D8DEE4` / `#C3CBD3` |
| `--c-text` / `-2` / `-3` | `#E6EDF3` / `#A9B4BF` / `#7D8590` | `#1F2328` / `#464E57` / `#656D76` |
| `--c-signal` | `#7EE787` | `#1A7F37` |
| `--c-cloud` / `--c-database` / `--c-projects` | `#79C0FF` / `#F778BA` / `#E3B341` | `#0969DA` / `#BF3989` / `#9A6700` |
| `--c-danger` / `--c-success` / `--c-warning` | `#FF7B72` / `#7EE787` / `#E3B341` | `#CF222E` / `#1A7F37` / `#9A6700` |
| `--c-code-*` (코드 블록) | 두 테마 공통으로 어두움 | ← 같음 |

라이트 값은 흰 배경에서 **글자로 읽히도록(대비 4.5:1 이상)** 한 단계 진하다. 새 색 토큰은 반드시 두 블록에 모두 넣는다.

테마를 바꾸면 함께 바뀌는 것(`_includes/footer/custom.html`):
- `data-src-light` 속성이 있는 이미지(GitHub Streak, 기술 스택 아이콘)는 라이트용 주소로 교체
- 댓글(utterances)은 `github-light` / `github-dark`로 전환
- Mermaid 다이어그램은 새 토큰 색으로 다시 그림
- 기여 캘린더(ghchart)는 다크에서만 색 반전 필터
- 전환 순간에만 0.3초 색 전환 (`.theme-anim`)

### 2.2 글꼴

| 토큰 | 글꼴 | 용도 |
|---|---|---|
| `--font-sans` | Pretendard Variable | 게시글 본문, 긴 설명 |
| `--font-ui` | JetBrains Mono (한글은 Pretendard로 대체) | 제목·메뉴·라벨·숫자·날짜·버튼 |
| `--font-mono` | JetBrains Mono | 코드 |

고정폭은 인터페이스에만 쓴다. JetBrains Mono에는 한글이 없어서 한글은 자동으로 Pretendard로 나온다(의도한 조합). 위계는 크기와 굵기로만 만든다.

`1rem`은 모바일 16px, 1024px 이상 17px이다. 아래 크기는 이 기준으로 변한다.

| 토큰 | 크기 | 쓰는 곳 |
|---|---|---|
| `--fs-3xl` | 48px | 홈의 "N일차" 숫자 (한 곳뿐) |
| `--fs-2xl` | 36px | 페이지 제목 |
| `--fs-xl` | 28px | 본문 `##` |
| `--fs-lg` | 22px | 본문 `###`, 섹션 머리 |
| `--fs-md` | 19px | `####`, 카드 제목 |
| `--fs-base` | 17~18px | 본문 |
| `--fs-sm` | 15~16px | 요약, 메타, 버튼, 경로(breadcrumb) |
| `--fs-xs` | 13~14px | 캡션 |

규칙: 제목에 한 단어만 색칠하기, 영어 대문자 라벨(ALL CAPS), 제목 위의 장식용 작은 라벨은 쓰지 않는다.

### 2.3 간격 · 모서리 · 효과

- 간격은 4px 단위: `--s-1`(4) `--s-2`(8) `--s-3`(12) `--s-4`(16) `--s-5`(24) `--s-6`(32) `--s-7`(48) `--s-8`(64)
- 모서리는 **거의 각지게**: 칩·버튼 `--r-sm`(2), 카드·패널·표 `--r-md`(4), 터미널 창 `--r-lg`(6). `--r-pill`도 2px로 둔다(알약 모양 금지).
- 면 구분은 그림자가 아니라 **선(`--c-line`)과 면 색 차이**로 한다.
- `--glow`는 `none`이다. 빛 번짐으로 강조하지 않는다. "지금 여기"는 색·밑줄·`[running]` 같은 글자로 표시한다.
- 자동 애니메이션은 **터미널 창 끝의 깜빡이는 커서 하나**뿐. `prefers-reduced-motion`이면 멈춘다.

---

## 3. 레이아웃

```
┌─────────────────────────────────────────────────────────┐
│ ◔ 예린님의 학습 노트                ● Cloud ● Database ● Projects │  상단바 (고정, 반투명)
├─────────────────────────────────────────────────────────┤
│ Home / Cloud / 20일차                              views │  경로 (게시글·섹션 페이지)
├──────────┬───────────────────────────────┬──────────────┤
│ 프로필    │ 본문 (최대 44rem)              │ 목차 / 홈 패널 │
│ (좌측)    │                               │ (우측, sticky) │
└──────────┴───────────────────────────────┴──────────────┘
```

- 사이트 최대 폭 `--site-max-width`(1320px). 넓은 모니터에서 줄 길이가 끝없이 늘어나지 않게 한다.
- 모든 글자는 **왼쪽 정렬**. 가운데 정렬은 빈 상태 안내문(`.empty-note`)뿐.
- 1023px 이하에서는 1열. 홈의 오른쪽 패널은 맨 아래로 내려간다.

### 3.1 정보 구조: "모듈"이 모든 것을 잇는다

부트캠프는 6개 모듈(`_data/modules.yml`)로 나뉘고, 세 섹션은 모두 **모듈 번호(`module:`)**로 연결된다.

```
모듈 N ──┬── Cloud     일차 학습노트 (type: daily)      → 주 단위 달력
         ├── Database  문제(yml) ← 풀이 글(type: practice) → 문제 카드 아래 단계 목록
         └── Projects  결과물 1개 + 연동기 시리즈         → 타임라인 정거장
```

| 페이지 | 구조 (위에서 아래로) |
|---|---|
| 홈 `index.md` | 미션 패널(인사 · N일차 · D-day · 궤적 · **이번 주 기록 5칸** · 목표) → 구분선 + **둘러보기** 띠(카테고리 색으로 물든 카드 3개) → 최근에 쓴 글 5편 → GitHub 잔디. 오른쪽 패널: **수료까지 모듈 진척 6칸** · 방문자 · **자주 쓴 태그**(자동, 누르면 검색) · **기술 스택**(태그로 자동, `_data/skills.yml`) |
| Cloud `cloud.md` | 섹션 머리 → **모듈 스테이지 6칸**(클릭 시 전환) → 모듈 제목 + **통계 타일 4개**(기간·학습노트는 중립, 문제풀이는 핑크·결과물은 라임 링크 타일) → **주 단위 달력** |
| Database `Database.md` | 섹션 머리 → **섹션 필터 칩**(하나만 선택, 같은 칩 다시 누르면 해제, 오른쪽 ↻ 전체로 초기화, `/database/#problems`처럼 주소로 바로 필터) → 🧩 문제와 풀이(문제 카드 + Lv 단계) → `_data/database_sections.yml` 순서대로 자료 섹션(아이콘·색·설명) → 거기 없는 분류는 기본 모양으로 맨 뒤 |
| Projects `Projects.md` | 섹션 머리 → **모듈 타임라인**. 완료 = 2열 카드(왼쪽 결과물 요약, 오른쪽 연동기 상세), 진행 중 = 안내, 시작 전 모듈은 맨 아래 **다음 정거장** 한 줄로 |
| 게시글 | 제목 → (선택) `#` 부제목 → 본문 → 이전/다음 → 댓글. 우측 목차(`##`부터) |

섹션 페이지(Cloud/Database/Projects)는 사이드바가 없으므로 본문이 사이트 폭 전체를 쓴다.

### 3.2 글 front matter 규칙 (디자인이 읽는 값)

| 글 종류 | 필수 | 선택 |
|---|---|---|
| Cloud 학습노트 | `categories: [Cloud]`, `type: daily`, `module: N`, `summary`, `excerpt` | `tags` (달력 칸에 앞 2개 표시). `summary`는 달력 칸 한 줄, `excerpt`는 목록·검색 요약 |
| Cloud 문제풀이 | `type: practice`, `module: N`, `topic`, `level_order` | 제목을 `Lv1 · 제목` 형식으로 쓰면 단계 라벨이 자동 분리됨 |
| Projects 결과물 | `categories: [Projects]`, `module: N` (type 없음) | `project_name`, `banner_emoji`, `live_url`, `tags` |
| Projects 연동기 | `type: practice`, `module: N`, `topic`, `level_order` | `tags` (첫 번째가 칩으로 표시) |
| Database 문제 (yml) | `category: "문제"`, `title`, `url` | `module`, `topics: ["풀이 글의 topic"]` |
| Database 자료 (yml) | `category`, `title`, `url` 또는 `file` | `description`. 새 category는 `_data/database_sections.yml`에 `name`·`emoji`·`tone`·`desc` 한 줄 추가 |

---

## 4. 컴포넌트 카탈로그

새 화면을 만들 때 **아래 클래스를 조합**한다. 비슷한 것을 새로 만들기 전에 여기서 찾는다.

| 클래스 | 설명 |
|---|---|
| `.is-cloud` `.is-database` `.is-projects` | 요소에 붙이면 `--cat` 색이 정해진다. 아래 컴포넌트들이 이 색을 쓴다 |
| `.cat-label` | `[cloud]` 형태의 카테고리 표시 |
| `.page-intro` (+ `__title` `__desc` `__stats`) | 섹션 페이지 머리. 밑줄 앞부분이 카테고리 색으로 빛남 |
| `.section-head` (+ `__aside`) | `## 제목` + 오른쪽 보조 정보(명령어·주석) |
| `.row-list` > `li` > `a.row` | **기본 목록.** `.row__date` `.row__main`(`.row__title` `.row__sub`) `.row__aside` |
| `.row--numbered` + `.row__num` | 순서가 있는 시리즈(1편, 2편…)에만 |
| `.group` (+ `__head` `__key` `__title` `__count`) | 모듈/주제/분류 묶음 머리 |
| `.tabs` > `.tab.is-active` (+ `.tab__count`), `.tab-panel` | 탭 전환 |
| `.panel` | 선으로 구분된 어두운 면 |
| `.chip-list` > `.chip` | 태그, 키워드 (색 없음) |
| `.btn-line` / `.btn-solid` | 보조 버튼 / 주요 버튼 (화면당 주요 버튼 1개) |
| `.stages` > `.stage.is-done/.is-current/.is-upcoming` | Cloud 모듈 스테이지 (윗선이 진행 상태) |
| `.module-panel` (+ `__head` `__facts`) | 선택한 모듈의 요약 |
| `.week-grid` > `.week` > `.day` / `.day--off` / `.day--empty` | 주 단위 달력 칸 |
| `.problem-list` > `.problem` (+ `.problem--unlinked`) | 문제 카드 |
| `.steps` > `.step` (+ `__lv` `__title`) | 풀이 단계 목록 |
| `.module-stats` > `.stat` / `.stat--link` / `.stat--muted` | 모듈 통계 타일. 링크 타일은 `is-섹션`색 |
| `.jump-nav` > `.jump[data-filter]` + `.jump-reset` | 섹션 필터 칩 (단일 선택) + 초기화 버튼 |
| `.mbars` > `.mbar.is-done/.is-current/.is-upcoming` | 모듈 진척 막대. 진행 중 막대는 `_data/module_schedule.yml` 날짜 비율, 없으면 빗금 |
| `.section-head--band` | 홈에서 미션 패널과 아래 영역을 나누는 구분 머리 |
| `.res-section` (+ `__head` `__icon` `__title` `__desc` `__count`) | Database 자료 섹션 머리. `is-색이름`으로 톤 지정 |
| `.res-grid` > `.res-card` | 자료 카드: 파비콘 + 도메인, 제목(카드 전체 클릭), 설명, 첨부파일 버튼 |
| `.proj-card--split` > `.proj-card__main` + `.proj-card__side` | 결과물 2열 카드 |
| `.chapter-list` > `.chapter` | 연동기 챕터 (번호 원 + 세로선) |
| `.next-stops` > `.next-stop` | 시작 전 모듈 모음 |
| `.week-strip` > `.wdot` | 홈 이번 주 기록 점 |
| `.chip--btn[data-search]` | 누르면 검색창이 열리며 그 단어로 검색 |
| `.sr-*` | 검색 결과 (탭, 그룹, 항목) |
| `.theme-toggle` | 상단바 라이트/다크 전환 버튼 (`_includes/masthead.html`) |
| `img[data-src-light]` | 라이트 테마에서 다른 주소로 바뀌는 외부 이미지 |
| `.timeline` > `.timeline__item.is-done/.is-current/.is-upcoming` | Projects 모듈 타임라인 |
| `.proj-series` | 결과물 카드 안 연동기 목록 |
| `.proj-slot` | 진행 중인 모듈의 빈 결과물 자리 |
| `.proj-card` | Projects 대표 카드 |
| `.project-hero` | 게시글 안 프로젝트 소개 박스 |
| `.term` (+ `__bar` `__path` `__now` `__body` `__cmd` `__cursor`) | 터미널 창. 홈 미션 패널과 404에서 사용. `.term__cmd`는 앞에 `$ `가 자동으로 붙는다 |
| `.readout` `.asciibar` | 홈 진행 상황 (큰 일차 숫자, key-value, █░ 막대) |
| `.ls` > `.ls__row` | `ls -l` 형태의 섹션 바로가기 |
| `.empty-note` | "아직 글이 없습니다" 안내 |
| `.code-popup-trigger` | 게시글 안 "원본 코드 보기" 버튼(`{% include code-popup.html %}`) |
| `.code-popup` (+ `__win` `__actions` `__close` `__body`) | 원본 코드 팝업(터미널 창 `.term` 재사용, `__body`는 코드 블록 색 사용) |

### 4.1 게시글 안에서 자동으로 적용되는 것 (`_includes/footer/custom.html`)

| 작성 방법 | 결과 |
|---|---|
| 문단/목록 맨 앞에 💡 🔴 🟢 ⚠️ 🚨 | 상태 색 콜아웃 박스 |
| `- **용어**` + 하위 목록 | 개념 카드 (위 구분선 + 용어 강조) |
| 15줄 넘는 코드 블록 | 접기/펼치기 |
| 모든 코드 블록 | 복사 버튼 |
| 본문 이미지 클릭 | 화면 전체로 확대 보기. 확대 화면에서 이미지를 다시 누르면 실제 크기로 토글, 바깥을 누르거나 `[esc] 닫기`·Esc 키로 닫힘 |
| 표 | 가로 스크롤 틀로 감싸짐 |
| 글 안의 `#` 부제목 | 목차에서 제외 (`_includes/toc.html`이 `##`부터 목차로 만듦) |
| 코드 블록 언어 (```bash) | 오른쪽 위 언어 라벨 |
| 글 front matter | 제목 아래 정보줄: 모듈 · 날짜 · 읽는 시간 · 태그(누르면 검색) — `_includes/page__meta.html` |
| `type: practice` + `topic` | 시리즈 진행 표시: "N편 중 M번째" + 단계 칸, 문제풀이는 "문제 보기" 링크 |
| 긴 글 | 한 화면 넘게 내리면 오른쪽 아래 맨 위로 버튼 |
| ` ```mermaid ` | 토큰 색으로 그려진 다이어그램 |
| `{% include code-popup.html dir="..." file="..." title="..." %}` | "원본 코드 보기" 버튼. 누르면 `assets/code/<dir>/<file>`을 fetch해서 터미널 창 팝업으로 보여줌(복사 버튼 포함) |

---

### 4.2 검색

검색 데이터는 **돋보기를 처음 누를 때만** 불러온다(`_includes/search/lunr-search-scripts.html`). 다른 페이지 로딩에는 영향이 없다.

상단 돋보기를 누르면 본문 자리에 검색 화면이 열린다. 테마 기본 lunr 대신 `assets/js/lunr/lunr-en.js`의 부분 문자열 검색을 쓴다(한글 단어 중간 일치).

- 데이터: `assets/js/lunr/lunr-store.js`가 모든 글 + `database_links.yml` 자료를 모은다.
- 분류: Cloud 학습노트 → **Cloud**, 문제풀이·문제·자료 → **Database**, 결과물·연동기 → **Projects**.
- 결과 화면: **전체 / Cloud / Database / Projects** 탭(개수 표시). 전체 탭은 섹션별로 3개씩 보여주고 "모두 보기"로 해당 탭 이동.
- 점수: 제목 > 태그 > 요약 > 본문. 여러 단어는 모두 포함된 글만(AND). Enter는 첫 결과로 이동, Esc는 닫기.
- 어느 페이지든 `data-search="단어"` 속성을 가진 버튼은 검색 트리거가 된다.

## 5. 글 작성 규칙 (스타일 관련)

- **게시글 `.md`에 `<style>`을 넣지 않는다.** 글자 크기 조절(`.page__content { font-size: ... }`)도 금지 — 모든 글이 같은 크기로 읽혀야 한다.
- 인라인 `style="color: ..."` 금지. 필요한 표현이 있으면 `site.css`에 컴포넌트를 추가하고 이 문서 4장에 한 줄 기록한다.
- 제목(`##`, `###`)에 이모지를 넣지 않는다. 이모지는 콜아웃 표시용으로만 쓴다.
- Projects 글 front matter: `banner_emoji`(카드 아이콘), `live_url`(라이브 버튼), `tags`(칩)를 쓴다. `banner_gradient`는 더 이상 쓰지 않는다.

---

## 6. 외부 위젯 예외

외부 이미지 서비스는 CSS 변수를 못 읽기 때문에 **URL에 색을 직접 넣는 것만 예외로 허용**한다. 토큰을 바꿨다면 이곳도 같이 바꾼다.

| 위젯 | 파일 | 색 파라미터 |
|---|---|---|
| 방문자·조회수 뱃지 | `index.md`, `_includes/footer/custom.html` | `left_color=161B22` `right_color=238636` |
| GitHub Streak | `index.md` | `ring` `currStreakLabel` = signal, `fire` = projects 앰버, 숫자 = text |
| 기여 캘린더 (ghchart) | `index.md` | `1A7F37` — 라이트는 그대로, 다크는 CSS 필터로 반전 |
| 댓글 (utterances) | `_config.yml` | `theme: "github-dark"` (라이트에서는 스크립트가 `github-light`로 전환) |
| 라이트 테마 위젯 | `index.md` | 각 `<img>`의 `data-src-light` (Streak: 라이트 토큰 색, skillicons: `theme=light`) |
| 브라우저 테마색 | `_includes/head/custom.html` | `theme-color` = `--c-bg` |

---

## 7. 한 번에 분위기를 바꾸는 방법

1. `assets/css/tokens.css`의 값만 바꾼다. (예: `--c-signal`을 다른 색으로) — 다크 블록과 라이트 블록을 **둘 다**
2. 6장 외부 위젯 표의 색을 맞춘다.
3. 끝. `site.css`와 페이지 파일은 건드리지 않아도 전체에 반영된다.

---

## 8. 변경 전 체크리스트

- [ ] 새로 쓴 색이 모두 `var(--...)`인가? (`site.css`에서 `#`로 검색했을 때 결과가 없어야 함)
- [ ] 새 색 토큰에 다크·라이트 **두 값**이 있는가? 두 테마에서 모두 눈으로 확인했는가?
- [ ] 외부 이미지를 추가했다면 `data-src-light`를 넣었는가?
- [ ] 목록을 카드 대신 `.row`로 만들 수 있는지 먼저 검토했는가?
- [ ] 390px 폭(모바일)에서 가로 스크롤이 생기지 않는가?
- [ ] 키보드 Tab으로 이동할 때 포커스 링이 보이는가?
- [ ] 새 애니메이션을 추가하지 않았는가? (추가했다면 사용자 동작에 반응하는 것인가?)
