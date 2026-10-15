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
  오른쪽 칸(.board-side)은 방명록 안내 + 둘러보기. 1023px 이하에서는 방명록 아래로 내려간다.
  스타일: .page-intro .guestbook .guestbook--wide .board .board-side (DESIGN.md 4장 재사용)
{%- endcomment -%}

<header class="page-intro is-signal">
<p class="term__cmd">cat ~/guestbook.log</p>
<h1 class="page-intro__title">방명록</h1>
<p class="page-intro__desc">GitHub 계정으로 한 줄 남길 수 있습니다. 글은 이 저장소의 GitHub Discussions에 쌓이고, 홈의 방명록과 같은 목록입니다.</p>
</header>

<div class="board">
{% include guestbook.html wide=true %}

<aside class="board-side" aria-label="방명록 안내">
<div class="section-head"><h2>안내</h2><span class="section-head__aside">cat README</span></div>
<div class="panel board-side__note">
<ul>
<li>GitHub 계정으로 로그인하면 글을 남길 수 있습니다.</li>
<li>공부하다 궁금한 점이나 틀린 내용도 환영합니다.</li>
<li>글은 이 저장소의 GitHub Discussions에 쌓입니다.</li>
</ul>
</div>

<div class="section-head"><h2>둘러보기</h2><span class="section-head__aside">ls ~/</span></div>
<ul class="row-list">
<li><a class="row is-cloud" href="{{ '/cloud/' | relative_url }}"><span class="row__date"><span class="cat-label">cloud</span></span><span class="row__main"><span class="row__title">학습노트</span><span class="row__sub">모듈별 일차 학습노트</span></span></a></li>
<li><a class="row is-database" href="{{ '/database/' | relative_url }}"><span class="row__date"><span class="cat-label">database</span></span><span class="row__main"><span class="row__title">문제와 자료</span><span class="row__sub">문제 풀이와 참고 자료</span></span></a></li>
<li><a class="row is-projects" href="{{ '/projects/' | relative_url }}"><span class="row__date"><span class="cat-label">projects</span></span><span class="row__main"><span class="row__title">프로젝트</span><span class="row__sub">모듈별 결과물과 연동기</span></span></a></li>
</ul>
</aside>
</div>
