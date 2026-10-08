---
title: "Tags"
layout: single
permalink: /tags/
classes: section-page
---

{%- comment -%}
  태그 모아보기. 태그는 글자 목록으로, 글 목록은 고른 태그 하나만 보여준다(footer/custom.html 의 [data-tag-view]).
  JS가 꺼져 있으면 모든 태그의 글이 그대로 펼쳐진다. 2편 이상 쓴 태그가 위에, 1편짜리는 접어 둔다.
  글 머리·홈의 태그 칩이 /tags/#태그 로 연결된다. 앵커는 `slugify`(두 곳이 같아야 함).
  스타일: site.css "20." .tag-cloud .tag-more .row-list--compact
{%- endcomment -%}

{%- assign tag_rank = "" | split: "," -%}
{%- for t in site.tags -%}
{%- capture entry -%}{{ t[1].size | plus: 1000 }}|{{ t[0] }}{%- endcapture -%}
{%- assign tag_rank = tag_rank | push: entry -%}
{%- endfor -%}
{%- assign tag_rank = tag_rank | sort | reverse -%}

<header class="page-intro is-signal">
<p class="term__cmd">grep -h tags _posts/**/*.md | sort | uniq -c</p>
<h1 class="page-intro__title">태그로 모아보기</h1>
<p class="page-intro__desc">태그를 누르면 그 태그가 붙은 글만 보여요. 낱말로 찾으려면 상단 돋보기를 쓰세요.</p>
<p class="page-intro__stats"><span>태그 <b>{{ tag_rank.size }}</b>개</span><span>글 <b>{{ site.posts.size }}</b>편</span></p>
</header>

{%- assign single_n = 0 -%}
<nav class="tag-cloud" aria-label="태그 목록">
{%- for e in tag_rank -%}{%- assign parts = e | split: "|" -%}{%- assign n = parts[0] | minus: 1000 -%}
{%- if n >= 2 -%}<a href="#{{ parts[1] | slugify }}">#{{ parts[1] }}<span class="tag-cloud__n">{{ n }}</span></a>{%- else -%}{%- assign single_n = single_n | plus: 1 -%}{%- endif -%}
{%- endfor -%}
</nav>
{%- if single_n > 0 -%}
<details class="tag-more">
<summary>1편짜리 태그 {{ single_n }}개</summary>
<nav class="tag-cloud" aria-label="1편짜리 태그 목록">
{%- for e in tag_rank -%}{%- assign parts = e | split: "|" -%}{%- assign n = parts[0] | minus: 1000 -%}
{%- if n < 2 -%}<a href="#{{ parts[1] | slugify }}">#{{ parts[1] }}</a>{%- endif -%}
{%- endfor -%}
</nav>
</details>
{%- endif -%}

<div data-tag-view>
{%- for e in tag_rank -%}
{%- assign parts = e | split: "|" -%}
{%- assign tag_name = parts[1] -%}
{%- assign tag_posts = site.tags[tag_name] | sort: "date" | reverse -%}
<section class="group" id="{{ tag_name | slugify }}">
<div class="group__head">
<h2 class="group__title">#{{ tag_name }}</h2>
<span class="group__count">{{ tag_posts.size }}편</span>
</div>
<ul class="row-list row-list--compact">
{%- for post in tag_posts -%}
{%- assign cat = post.categories | first -%}
<li><a class="row is-{{ cat | downcase }}" href="{{ post.url | relative_url }}">
<span class="row__date">{{ post.date | date: "%Y.%m.%d" }}</span>
<span class="row__main"><span class="row__title">{{ post.title }}</span>{% if post.summary %}<span class="row__sub">{{ post.summary }}</span>{% endif %}</span>
<span class="row__aside"><span class="cat-label">{{ cat | default: "글" }}</span></span>
</a></li>
{%- endfor -%}
</ul>
</section>
{%- endfor -%}
</div>
