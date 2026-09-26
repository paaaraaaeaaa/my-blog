---
title: "Projects"
layout: single
permalink: /projects/
classes: section-page
---

{%- comment -%}
  Projects = 모듈마다 하나씩 나오는 결과물(총 6개) + 결과물별 연동기 시리즈.
  - 결과물 글: categories [Projects], type 없음, module: N
    (선택) project_name, banner_emoji, live_url, tags
  - 연동기 글: categories [Projects], type: practice, module: N, topic, level_order
  스타일: assets/css/site.css "13. Projects"
{%- endcomment -%}

{%- assign empty_array = "" | split: "," -%}
{%- assign cat_posts = site.categories.Projects | default: empty_array -%}
{%- assign main_posts = cat_posts | where_exp: "p", "p.type == nil" -%}
{%- assign series_posts = cat_posts | where_exp: "p", "p.type == 'practice'" -%}
{%- assign daily_posts = site.categories.Cloud | default: empty_array | where_exp: "p", "p.type != 'practice'" | where_exp: "p", "p.module" -%}
{%- assign current = 1 -%}
{%- for p in daily_posts -%}{%- if p.module > current -%}{%- assign current = p.module -%}{%- endif -%}{%- endfor -%}

<header class="page-intro is-projects">
<span class="cat-label">Projects</span>
<h1 class="page-intro__title">모듈마다 하나씩, 여섯 개의 결과물</h1>
<p class="page-intro__desc">각 모듈이 끝날 때 팀 프로젝트 결과물이 하나씩 나옵니다. 기획부터 배포 이후의 트러블슈팅까지, 잘 풀린 것만큼 막혔던 지점도 그대로 남깁니다.</p>
<p class="page-intro__stats"><span>결과물 <b>{{ main_posts.size }}</b> / 6</span><span>연동기 <b>{{ series_posts.size }}</b>편</span></p>
</header>

{%- comment -%} 완료·진행 중인 모듈은 타임라인에, 아직 시작 전인 모듈은 맨 아래 "다음 정거장" 한 줄로 모은다 {%- endcomment -%}
{%- assign upcoming_count = 0 -%}
<ol class="timeline">
{%- for i in (1..6) -%}
{%- assign key = i | append: "" -%}
{%- assign project = main_posts | where_exp: "p", "p.module == i" | first -%}
{%- assign mod_series = series_posts | where_exp: "p", "p.module == i" -%}
{%- if project -%}{%- assign st = "done" -%}{%- assign st_label = "완료 ✓" -%}
{%- elsif i == current -%}{%- assign st = "current" -%}{%- assign st_label = "지금 여기" -%}
{%- else -%}{%- assign st = "upcoming" -%}{%- assign upcoming_count = upcoming_count | plus: 1 -%}{%- endif -%}
{%- if st != "upcoming" -%}
<li class="timeline__item is-{{ st }}">
<span class="timeline__marker" aria-hidden="true"></span>
<div class="timeline__head">
<span class="timeline__module">모듈 {{ i }}</span>
<span class="timeline__name">{{ site.data.modules[key] | default: "미정" }}</span>
<span class="timeline__status">{{ st_label }}</span>
</div>
{%- if project -%}
<article class="proj-card{% if mod_series.size > 0 %} proj-card--split{% endif %}">
<div class="proj-card__main">
<span class="proj-card__mark" aria-hidden="true">{{ project.banner_emoji | default: "🚀" }}</span>
<div class="proj-card__meta">{% if project.project_name %}<span class="proj-card__name">{{ project.project_name }}</span>{% endif %}<span>{{ project.date | date: "%Y.%m.%d" }} 공개</span></div>
<a class="proj-card__title" href="{{ project.url | relative_url }}">{{ project.title }}</a>
{% if project.excerpt %}<p class="proj-card__excerpt">{{ project.excerpt | strip_html | strip_newlines | truncate: 160 }}</p>{% endif %}
{% if project.tags.size > 0 %}<div class="chip-list">{% for tag in project.tags limit: 8 %}<span class="chip">{{ tag }}</span>{% endfor %}</div>{% endif %}
<div class="proj-card__links">
<a class="btn-line" href="{{ project.url | relative_url }}">회고 읽기</a>
{% if project.live_url %}<a class="btn-solid" href="{{ project.live_url }}" target="_blank" rel="noopener">라이브 사이트</a>{% endif %}
</div>
</div>
{%- if mod_series.size > 0 -%}
<aside class="proj-card__side" aria-label="연동기">
{%- assign series_groups = mod_series | group_by: "topic" -%}
{%- for g in series_groups -%}
{%- assign chapters = g.items | sort: "level_order" -%}
<div class="proj-series">
<div class="proj-series__head"><span class="proj-series__title">{{ g.name }}</span><span class="proj-series__count">{{ chapters.size }}편</span></div>
<p class="proj-series__desc">결과물을 만들며 가장 오래 막혔던 연동 과정만 따로 깊게 기록했어요.</p>
<ol class="chapter-list">
{%- for post in chapters -%}
<li><a class="chapter" href="{{ post.url | relative_url }}">
<span class="chapter__num">{{ forloop.index }}</span>
<span class="chapter__body">
<span class="chapter__top">{% if post.tags.size > 0 %}<span class="chapter__tag">{{ post.tags | first }}</span>{% endif %}<span class="chapter__date">{{ post.date | date: "%-m.%-d" }}</span></span>
<span class="chapter__title">{{ post.title }}</span>
{% if post.excerpt %}<span class="chapter__excerpt">{{ post.excerpt | strip_html | strip_newlines | truncate: 80 }}</span>{% endif %}
</span>
</a></li>
{%- endfor -%}
</ol>
</div>
{%- endfor -%}
</aside>
{%- endif -%}
</article>
{%- else -%}
<div class="proj-slot"><p class="proj-slot__text">지금 진행 중인 모듈이에요. 모듈이 끝나면 팀 프로젝트 결과물이 여기에 올라와요.</p></div>
{%- endif -%}
</li>
{%- endif -%}
{%- endfor -%}

{%- if upcoming_count > 0 -%}
<li class="timeline__item is-upcoming">
<span class="timeline__marker" aria-hidden="true"></span>
<div class="timeline__head"><span class="timeline__name">다음 정거장</span><span class="timeline__status">{{ upcoming_count }}개 모듈 남음</span></div>
<div class="next-stops">
{%- for i in (1..6) -%}
{%- assign key = i | append: "" -%}
{%- assign project = main_posts | where_exp: "p", "p.module == i" | first -%}
{%- unless project or i == current -%}
<div class="next-stop">
<span class="next-stop__num">모듈 {{ i }}</span>
<span class="next-stop__name">{{ site.data.modules[key] | default: "미정" }}</span>
<span class="next-stop__note">결과물 곧 공개</span>
</div>
{%- endunless -%}
{%- endfor -%}
</div>
</li>
{%- endif -%}
</ol>
