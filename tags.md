---
title: "Tags"
layout: single
permalink: /tags/
classes: section-page
---

{%- comment -%}
  태그 모아보기. 모든 글의 tags를 모아 "많이 쓴 순"으로 보여주고, 태그마다 글 목록을 묶는다.
  글 머리·홈의 태그 칩이 이 페이지의 /tags/#태그 로 연결된다. 앵커는 `slugify`를 쓴다(두 곳이 같아야 함).
  스타일: .page-intro .chip-list .group .row-list (DESIGN.md 4장 재사용)
{%- endcomment -%}

{%- assign tag_rank = "" | split: "," -%}
{%- for t in site.tags -%}
{%- capture entry -%}{{ t[1].size | plus: 1000 }}|{{ t[0] }}{%- endcapture -%}
{%- assign tag_rank = tag_rank | push: entry -%}
{%- endfor -%}
{%- assign tag_rank = tag_rank | sort | reverse -%}

<header class="page-intro is-signal">
<p class="term__cmd">cat _posts/**/*.md | grep tags | sort | uniq -c</p>
<h1 class="page-intro__title">태그로 모아보기</h1>
<p class="page-intro__desc">글에 붙인 태그를 한곳에 모았습니다. 태그를 누르면 그 태그가 붙은 글만 볼 수 있어요. 낱말로 찾고 싶다면 상단 돋보기 검색을 쓰세요.</p>
<p class="page-intro__stats"><span>태그 <b>{{ tag_rank.size }}</b>개</span><span>글 <b>{{ site.posts.size }}</b>편</span></p>
</header>

<nav class="chip-list tag-index" aria-label="태그 목록">
{%- for e in tag_rank -%}{%- assign parts = e | split: "|" -%}
<a class="chip chip--btn" href="#{{ parts[1] | slugify }}">#{{ parts[1] }}<span class="chip__n">{{ parts[0] | minus: 1000 }}</span></a>
{%- endfor -%}
</nav>

{%- for e in tag_rank -%}
{%- assign parts = e | split: "|" -%}
{%- assign tag_name = parts[1] -%}
{%- assign tag_posts = site.tags[tag_name] | sort: "date" | reverse -%}
<section class="group" id="{{ tag_name | slugify }}">
<div class="group__head">
<span class="group__key">#</span>
<h2 class="group__title">{{ tag_name }}</h2>
<span class="group__count">{{ tag_posts.size }}편</span>
</div>
<ul class="row-list">
{%- for post in tag_posts -%}
{%- assign cat = post.categories | first -%}
<li><a class="row is-{{ cat | downcase }}" href="{{ post.url | relative_url }}">
<span class="row__date">{{ post.date | date: "%Y.%m.%d" }}</span>
<span class="row__main"><span class="row__title">{{ post.title }}</span><span class="row__sub">{{ post.excerpt | strip_html | strip_newlines | truncate: 90 }}</span></span>
<span class="row__aside"><span class="cat-label">{{ cat | default: "글" }}</span></span>
</a></li>
{%- endfor -%}
</ul>
</section>
{%- endfor -%}
