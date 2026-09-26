---
name: series
description: 결과물을 만들며 오래 막혔던 연동 과정(API, 인증, 배포 등)을 연동기 시리즈 한 편으로 쓴다. Projects 결과물 카드 오른쪽 목록에 붙는다. /series 라고 하면 실행한다.
---

# 연동기 한 편 만들기

연동기는 Projects 결과물 카드의 오른쪽 "연동기" 목록에 번호 순서대로 붙는다.
같은 시리즈는 `module`과 `topic`이 같아야 한다.

## 순서

1. 먼저 물어본다 — 한 번에 하나씩.
   - "어떤 연동이었어요? (예: 구글 로그인, 이메일 인증, 지도 API)"
   - "증상이 뭐였고, 원인이 뭐였어요?"
2. `_posts/Projects/`에서 같은 `module`의 `type: practice` 글을 찾아 기존 `topic`(예: `"pinder API 연동기"`)을 그대로 쓰고,
   `level_order`는 기존 최댓값 + 1로 정한다. 시리즈가 처음이면 `topic: "<서비스명> API 연동기"`, `level_order: 1`.
3. `_posts/Projects/YYYY-MM-DD-<서비스명>-<연동대상>.md` 로 만든다.
4. 본문 흐름: `증상 → 처음 의심한 것 → 진짜 원인 → 고친 방법 → 확인 방법 → 배운 점`.
   - 🔴 증상/잘못 짚은 원인 · 🟢 고친 방법/확인 방법 · 💡 배운 점 · ⚠️ 다시 막힐 수 있는 지점
   - 비밀 키·토큰·환경변수 값은 **절대 본문에 쓰지 않는다.** 이름만 쓴다 (`GOOGLE_CLIENT_ID` 등).
5. `post-checker` 에이전트로 검사하고 나에게 보여준다. 내가 좋다고 하기 전에는 커밋하지 마.
6. 내가 확인하면 커밋하고 push 한다. 커밋 메시지: `series: <topic> #<level_order>`

## 앞머리

```yaml
---
layout: single
title: "<증상이 드러나는 제목 — 예: 지도에 선이 안 그려진다 — 버그 다섯 개가 전부 다른 원인이었다>"
excerpt: "<무슨 일이 있었고 어떻게 풀었는지 두 문장>"
date: YYYY-MM-DD
categories: [Projects]
module: 1
type: practice
topic: "<서비스명> API 연동기"
level_order: 1
tags: [<연동 대상 — 첫 번째 태그가 목록 칩으로 보임>, 태그2, 태그3]
comments: true
toc: true
toc_sticky: true
---
```
