---
title: "Projects"
layout: single
permalink: /projects/
classes: section-page
---

{%- comment -%}
  Projects 섹션. 완성한 프로젝트(type 없음) + 연동기 시리즈(type: practice, topic별).
  스타일: assets/css/site.css "13. Projects".
  글 front matter의 banner_emoji 를 카드 아이콘으로 쓴다. (banner_gradient는 더 이상 쓰지 않음)
{%- endcomment -%}

{%- assign empty_array = "" | split: "," -%}
{%- assign cat_posts = site.categories.Projects | default: empty_array -%}
{%- assign cat_posts = cat_posts | sort: 'date' | reverse -%}
{%- assign main_posts = cat_posts | where_exp: "p", "p.type == nil" -%}
{%- assign series_posts = cat_posts | where_exp: "p", "p.type == 'practice'" -%}
{%- assign series_groups = series_posts | group_by: 'topic' -%}

<header class="page-intro is-projects">
<span class="cat-label">Projects</span>
<h1 class="page-intro__title">아이디어를 서비스로 만드는 과정</h1>
<p class="page-intro__desc">기획부터 배포, 배포 이후의 트러블슈팅까지 팀 프로젝트에서 맡았던 몫을 기록합니다. 잘 풀린 것만큼 막혔던 지점도 그대로 남겨둡니다.</p>
<p class="page-intro__stats"><span>프로젝트 <b>{{ main_posts.size }}</b>개</span><span>연동기 <b>{{ series_posts.size }}</b>편</span>{% if series_groups.size > 0 %}<span>시리즈 <b>{{ series_groups.size }}</b>개</span>{% endif %}</p>
</header>

<div class="section-head"><h2>완성한 프로젝트</h2></div>

{% if main_posts.size > 0 %}
{% for post in main_posts %}
<article class="proj-card">
<span class="proj-card__mark" aria-hidden="true">{{ post.banner_emoji | default: "🚀" }}</span>
<div>
<div class="proj-card__meta">{% if post.module %}<span>모듈 {{ post.module }}</span>{% endif %}<span>{{ post.date | date: "%Y.%m.%d" }}</span></div>
<a class="proj-card__title" href="{{ post.url | relative_url }}">{{ post.title }}</a>
{% if post.excerpt %}<p class="proj-card__excerpt">{{ post.excerpt | strip_html | strip_newlines | truncate: 140 }}</p>{% endif %}
<div class="proj-card__foot">
{% if post.tags.size > 0 %}<div class="chip-list">{% for tag in post.tags limit: 6 %}<span class="chip">{{ tag }}</span>{% endfor %}</div>{% endif %}
<div class="proj-card__links">
<a class="btn-line" href="{{ post.url | relative_url }}">회고 읽기</a>
{% if post.live_url %}<a class="btn-solid" href="{{ post.live_url }}" target="_blank" rel="noopener">라이브 사이트</a>{% endif %}
</div>
</div>
</div>
</article>
{% endfor %}
{% else %}
<p class="empty-note">아직 등록된 프로젝트가 없습니다.</p>
{% endif %}

{% for group in series_groups %}
{% assign chapters = group.items | sort: 'level_order' %}
<div class="section-head"><h2>{{ group.name }}</h2><span class="section-head__aside">가장 힘들었던 연동 과정만 따로 기록한 시리즈, {{ chapters.size }}편</span></div>
<ul class="row-list">
{% for post in chapters %}
<li><a class="row row--numbered" href="{{ post.url | relative_url }}">
<span><span class="row__num">{{ post.level_order | default: forloop.index }}</span></span>
<span class="row__main"><span class="row__title">{{ post.title }}</span>{% if post.excerpt %}<span class="row__sub">{{ post.excerpt | strip_html | strip_newlines | truncate: 100 }}</span>{% endif %}</span>
<span class="row__aside">{% if post.tags.size > 0 %}<span class="chip">{{ post.tags | first }}</span>{% endif %}</span>
</a></li>
{% endfor %}
</ul>
{% endfor %}
