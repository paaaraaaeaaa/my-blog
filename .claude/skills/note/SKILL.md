---
name: note
description: 배운 것을 일차 학습노트로 정리해서 블로그 Cloud 섹션에 올린다. /note 라고 하면 실행한다.
---

# 학습 노트 만들기

규칙의 기준은 `CLAUDE.md`(글 구조·게시 규칙)와 `DESIGN.md`(front matter가 화면에 쓰이는 방식)다.

## 순서

1. 먼저 나에게 물어봐 — "뭘 배웠어요?"
   내가 답하기 전에는 글을 쓰지 마. 답이 짧으면 STAR(상황·과제·행동·결과) 중 빈 곳을 **한 번에 하나씩만** 되물어.
2. 아래 값을 **스스로 계산해서** 채운다. 나에게 묻지 않는다.
   - **N일차**: 2026-08-26을 1일차로 두고, 글 날짜까지 주말과 `_data/holidays.yml`의 날짜를 뺀 평일 수. (홈 화면 계산과 같은 규칙)
   - **module**: `_posts/Cloud/학습노트/`에서 가장 최근 글의 `module` 값. 내가 "새 모듈 시작"이라고 했으면 +1 하고 `/module` 스킬을 먼저 안내한다.
3. `_posts/Cloud/학습노트/YYYY-MM-DD-dayN.md` 로 글을 만든다. 앞머리는 아래 형식 그대로.

   ```yaml
   ---
   title: "N일차"
   excerpt: "오늘 한 일을 한두 문장으로. Cloud 달력 칸·최근 글·검색 미리보기에 그대로 보인다."
   layout: single
   categories: [Cloud]
   module: 1
   type: daily
   date: YYYY-MM-DD
   tags: [태그1, 태그2, 태그3]
   comments: true
   toc: true
   toc_sticky: true
   mermaid: true
   ---
   ```

   - `tags`는 3~5개. **앞의 2개가 달력 칸에 보이니** 가장 대표적인 것을 앞에 둔다.
   - 본문 첫 줄에 `# 오늘 배운 것: <한 문장>` 부제목을 둔다. (목차에서는 자동으로 빠진다)
   - 본문은 `CLAUDE.md` 6장 STAR 템플릿을 따른다.
   - `<style>`, 글자 크기 조절 코드, 인라인 `style="..."`는 **넣지 않는다.** (DESIGN.md 5장)
4. 새 기술 태그를 처음 썼다면 `_data/skills.yml`에 아이콘을 추가할지 제안한다. (skillicons에 있는 기술일 때만)
5. `post-checker` 에이전트로 검사하고, 결과를 글과 함께 나에게 보여준다.
   내가 좋다고 하기 전에는 커밋하지 마.
6. 내가 확인하면 커밋하고 push 한다. 커밋 메시지: `post: N일차 — <excerpt 앞부분>`
