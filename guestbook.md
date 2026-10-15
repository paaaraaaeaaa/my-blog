---
title: "Guestbook"
layout: single
permalink: /guestbook/
classes: section-page
---

{%- comment -%}
  방명록 전체 보기. 홈 방명록과 같은 giscus 스레드(term: guestbook)를 넓게 보여준다.
  상단 탭에는 없고 홈 방명록의 "전체 보기" 링크나 주소(/guestbook/)로만 들어온다.
  홈과 달리 댓글 목록의 안쪽 스크롤 제한이 없다(assets/css/giscus-board-*.css).
  스타일: .page-intro .guestbook .guestbook--wide (DESIGN.md 4장 재사용)
{%- endcomment -%}

<header class="page-intro is-signal">
<p class="term__cmd">cat ~/guestbook.log</p>
<h1 class="page-intro__title">방명록</h1>
<p class="page-intro__desc">GitHub 계정으로 한 줄 남길 수 있습니다. 글은 이 저장소의 GitHub Discussions에 쌓이고, 홈의 방명록과 같은 목록입니다.</p>
</header>

{% include guestbook.html wide=true %}
