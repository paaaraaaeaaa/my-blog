---
title: "Database"
layout: single
permalink: /database/
classes: section-page
---

{%- comment -%}
  Database 섹션. 항목은 _data/database_links.yml 에서 온다.
  스타일: assets/css/site.css "12. Database".
{%- endcomment -%}

{% assign db_links = site.data.database_links %}

<header class="page-intro is-database">
<span class="cat-label">Database</span>
<h1 class="page-intro__title">문제·게임·아티팩트 모음</h1>
<p class="page-intro__desc">부트캠프에서 풀었던 문제, 감각을 익히는 게임, 정리한 산출물을 한곳에 모았습니다.</p>
<p class="page-intro__stats"><span>자료 <b>{{ db_links.size | default: 0 }}</b>개</span>{% if db_links and db_links.size > 0 %}{% assign db_cat_count = db_links | map: 'category' | uniq | size %}<span>분류 <b>{{ db_cat_count }}</b>개</span>{% endif %}</p>
</header>

{% if db_links and db_links.size > 0 %}
{% assign db_groups = db_links | group_by: 'category' %}
{% for group in db_groups %}
{% case group.name %}
  {% when "문제" %}{% assign icon = "📝" %}
  {% when "게임" %}{% assign icon = "🎮" %}
  {% when "아티팩트" %}{% assign icon = "🗂️" %}
  {% else %}{% assign icon = "📁" %}
{% endcase %}
<section class="group is-database">
<div class="group__head">
<span class="group__key" aria-hidden="true">{{ icon }}</span>
<span class="group__title">{{ group.name }}</span>
<span class="group__count">{{ group.items.size }}개</span>
</div>
<div class="res-grid">
{% for item in group.items %}
<article class="res-card">
<span class="res-card__title">{{ item.title }}</span>
{% if item.description %}<span class="res-card__desc">{{ item.description }}</span>{% endif %}
<div class="res-card__actions">
{% if item.url %}<a class="btn-line" href="{{ item.url }}" target="_blank" rel="noopener noreferrer">열기</a>{% endif %}
{% if item.file %}<a class="btn-line" href="{{ item.file | relative_url }}" target="_blank" rel="noopener noreferrer">첨부파일</a>{% endif %}
</div>
</article>
{% endfor %}
</div>
</section>
{% endfor %}
{% else %}
<p class="empty-note">아직 등록된 자료가 없습니다.</p>
{% endif %}
