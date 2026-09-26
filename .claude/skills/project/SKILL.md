---
name: project
description: 모듈이 끝날 때 팀 프로젝트 결과물 회고 글을 써서 Projects 타임라인에 올린다. /project 라고 하면 실행한다.
---

# 모듈 결과물 회고 만들기

결과물 글은 Projects 페이지 타임라인에서 **해당 모듈 정거장을 "완료"로 바꾸고**, 홈의 모듈 진척 막대도 한 칸 채운다.
모듈마다 하나만 쓴다(총 6개).

## 순서

1. 먼저 물어본다 — 한 번에 하나씩, 이미 답한 건 다시 묻지 않는다.
   - "몇 번 모듈 결과물이에요? 서비스 이름이 뭐예요?"
   - "라이브 주소나 저장소 링크가 있어요?"
   - "팀에서 맡은 몫과 가장 크게 막혔던 지점 하나만 알려줄래요?"
2. 같은 `module`의 결과물 글(`categories: [Projects]`, `type` 없음)이 이미 있으면 새로 쓰지 말고 그 글을 고칠지 묻는다.
3. `_posts/Projects/YYYY-MM-DD-<영문-서비스명>.md` 로 만든다.
4. 본문은 `CLAUDE.md` 6장 STAR 템플릿을 따르고, 맨 위에 프로젝트 소개 박스를 둔다(스타일은 `site.css`에 이미 있음, 새로 만들지 않는다).

   ```html
   <div class="project-hero">
     <div class="project-hero__icon">🧭</div>
     <div class="project-hero__body">
       <p class="project-hero__title">서비스명 — 한 줄 소개</p>
       <p class="project-hero__desc">무엇을 하는 서비스인지 두 문장.</p>
       <p class="project-hero__meta"><span>👥 팀원</span><span>📅 기간</span></p>
       <div class="project-hero__links">
         <a class="project-hero__link" href="라이브주소" target="_blank" rel="noopener">🔗 라이브 사이트</a>
         <a class="project-hero__link project-hero__link--ghost" href="저장소주소" target="_blank" rel="noopener">GitHub</a>
       </div>
     </div>
   </div>
   ```
5. 연동 과정 중 따로 깊게 쓸 만한 게 있으면 "연동기 시리즈로 따로 쓸까요? (`/series`)"라고 제안만 한다.
6. `post-checker` 에이전트로 검사하고 나에게 보여준다. 내가 좋다고 하기 전에는 커밋하지 마.
7. 내가 확인하면 커밋하고 push 한다. 커밋 메시지: `project: 모듈 N 결과물 — <project_name>`
8. push 후 "다음 모듈을 시작할 때 `/module`로 홈 상태 문구와 일정을 바꿔 주세요"라고 알려준다.

## 앞머리

```yaml
---
title: "<문제 중심 제목 — 예: 다 같이 가는 여행, 동선은 왜 매번 각자 짜야 할까>"
excerpt: "<결과물 카드에 보일 두세 문장>"
layout: single
categories: [Projects]
module: 1
project_name: "<짧은 서비스 이름 — Cloud 결과물 타일에 표시>"
banner_emoji: "🧭"
live_url: "https://..."
date: YYYY-MM-DD
tags: [태그1, 태그2, 태그3, 태그4]
comments: true
toc: true
toc_sticky: true
mermaid: true
---
```

- `type`은 **쓰지 않는다.** (`type: practice`는 연동기 글)
- `banner_gradient`는 더 이상 쓰지 않는다.
