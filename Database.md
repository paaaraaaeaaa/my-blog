---
title: "Database"
layout: single
permalink: /database/
classes: section-page
---

{%- comment -%}
  Database = 자료 허브.
  - 항목: _data/database_links.yml
  - "문제" 항목의 topics 값과 같은 topic을 가진 풀이 글(Cloud, type: practice)이 문제 카드 아래에 자동 연결된다
  - 어떤 문제에도 연결되지 않은 풀이 topic은 "문제 링크 미등록" 카드로 표시된다
  스타일: assets/css/site.css "12. Database"
{%- endcomment -%}

{%- assign empty_array = "" | split: "," -%}
{%- assign db_links = site.data.database_links | default: empty_array -%}
{%- assign problems = db_links | where: "category", "문제" -%}
{%- assign others = db_links | where_exp: "i", "i.category != '문제'" -%}
{%- assign practice_posts = site.categories.Cloud | default: empty_array | where_exp: "p", "p.type == 'practice'" -%}
{%- assign linked_topics = "" -%}
{%- for pb in problems -%}{%- for t in pb.topics -%}{%- assign linked_topics = linked_topics | append: "|" | append: t | append: "|" -%}{%- endfor -%}{%- endfor -%}

<header class="page-intro is-database">
<span class="cat-label">Database</span>
<h1 class="page-intro__title">문제와 풀이, 그리고 자료</h1>
<p class="page-intro__desc">부트캠프에서 받은 문제와 직접 푼 풀이를 한 묶음으로 모았습니다. 감각을 익히는 게임과 정리한 산출물도 함께 둡니다.</p>
<p class="page-intro__stats"><span>문제 <b>{{ problems.size }}</b>개</span><span>풀이 <b>{{ practice_posts.size }}</b>편</span><span>자료 <b>{{ others.size }}</b>개</span></p>
</header>

<div class="section-head" id="problems"><h2>문제와 풀이</h2><span class="section-head__aside">레벨 순서대로 풀이가 이어집니다</span></div>

<div class="problem-list">
{%- assign problems_by_module = problems | sort: "module" -%}
{%- for pb in problems_by_module -%}
{%- assign sols = empty_array -%}
{%- for t in pb.topics -%}{%- assign found = practice_posts | where: "topic", t -%}{%- assign sols = sols | concat: found -%}{%- endfor -%}
{%- assign sols = sols | sort: "date" | sort: "level_order" -%}
<article class="problem">
<div class="problem__head">
<div class="problem__info">
<span class="problem__meta">{% if pb.module %}모듈 {{ pb.module }}{% endif %}{% if sols.size > 0 %}<span>풀이 {{ sols.size }}편</span><span>{{ sols.first.date | date: "%-m.%-d" }} 풀이</span>{% endif %}</span>
<span class="problem__title">{{ pb.title }}</span>
</div>
{%- if pb.url -%}<a class="btn-line" href="{{ pb.url }}" target="_blank" rel="noopener noreferrer">문제 열기</a>{%- endif -%}
</div>
{%- if sols.size > 0 -%}
<ol class="steps">
{%- for post in sols -%}
{%- assign parts = post.title | split: " · " -%}
{%- assign head2 = parts[0] | slice: 0, 2 -%}
{%- if parts.size > 1 and head2 == "Lv" or parts.size > 1 and head2 == "Bo" -%}
{%- assign lv = parts[0] -%}{%- assign rest = post.title | remove_first: parts[0] | remove_first: " · " -%}
{%- else -%}
{%- assign lv = "풀이 " | append: forloop.index -%}{%- assign rest = post.title -%}
{%- endif -%}
<li><a class="step" href="{{ post.url | relative_url }}"><span class="step__lv">{{ lv }}</span><span class="step__title">{{ rest }}</span></a></li>
{%- endfor -%}
</ol>
{%- else -%}
<p class="problem__empty">아직 올린 풀이가 없습니다.</p>
{%- endif -%}
</article>
{%- endfor -%}

{%- assign all_topics = practice_posts | sort: "date" | map: "topic" | uniq -%}
{%- for t in all_topics -%}
{%- assign needle = "|" | append: t | append: "|" -%}
{%- unless linked_topics contains needle -%}
{%- assign sols = practice_posts | where: "topic", t | sort: "level_order" -%}
<article class="problem problem--unlinked">
<div class="problem__head">
<div class="problem__info">
<span class="problem__meta">{% if sols.first.module %}모듈 {{ sols.first.module }}{% endif %}<span>풀이 {{ sols.size }}편</span><span>문제 링크 미등록</span></span>
<span class="problem__title">{{ t }}</span>
</div>
</div>
<ol class="steps">
{%- for post in sols -%}
<li><a class="step" href="{{ post.url | relative_url }}"><span class="step__lv">풀이 {{ forloop.index }}</span><span class="step__title">{{ post.title }}</span></a></li>
{%- endfor -%}
</ol>
</article>
{%- endunless -%}
{%- endfor -%}
</div>

{%- assign groups = others | group_by: "category" -%}
{%- for group in groups -%}
{%- case group.name -%}
  {%- when "게임" -%}{%- assign desc = "브라우저에서 바로 하는 CSS 연습 게임" -%}
  {%- when "아티팩트" -%}{%- assign desc = "수업과 실습에서 정리한 산출물" -%}
  {%- else -%}{%- assign desc = "" -%}
{%- endcase -%}
<div class="section-head"><h2>{{ group.name }}</h2><span class="section-head__aside">{{ desc }}{% if desc != "" %}, {% endif %}{{ group.items.size }}개</span></div>
<div class="res-grid">
{%- for item in group.items -%}
<article class="res-card">
<span class="res-card__title">{{ item.title }}</span>
{% if item.description %}<span class="res-card__desc">{{ item.description }}</span>{% endif %}
<div class="res-card__actions">
{% if item.url %}<a class="btn-line" href="{{ item.url }}" target="_blank" rel="noopener noreferrer">열기</a>{% endif %}
{% if item.file %}<a class="btn-line" href="{{ item.file | relative_url }}" target="_blank" rel="noopener noreferrer">첨부파일</a>{% endif %}
</div>
</article>
{%- endfor -%}
</div>
{%- endfor -%}
