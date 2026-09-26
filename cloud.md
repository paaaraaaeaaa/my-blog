---
title: "Cloud"
layout: single
permalink: /cloud/
classes: section-page
---

{%- comment -%}
  Cloud = 모듈별 일차 학습노트 (type: daily).
  - 모듈 이름: _data/modules.yml (1~6)
  - 글 front matter의 module 값으로 모듈에 들어간다
  - 문제풀이(type: practice)는 Database 페이지에서 문제별로 모아 보여준다
  스타일: assets/css/site.css "11. Cloud"
{%- endcomment -%}

{%- assign empty_array = "" | split: "," -%}
{%- assign cat_posts = site.categories.Cloud | default: empty_array -%}
{%- assign daily_posts = cat_posts | where_exp: "p", "p.type != 'practice'" | where_exp: "p", "p.module" -%}
{%- assign practice_posts = cat_posts | where_exp: "p", "p.type == 'practice'" -%}
{%- assign proj_main = site.categories.Projects | default: empty_array | where_exp: "p", "p.type == nil" -%}

{%- assign current = 1 -%}
{%- for p in daily_posts -%}{%- if p.module > current -%}{%- assign current = p.module -%}{%- endif -%}{%- endfor -%}

<header class="page-intro is-cloud">
<span class="cat-label">Cloud</span>
<h1 class="page-intro__title">모듈별 학습 로그</h1>
<p class="page-intro__desc">부트캠프 6개 모듈을 하루 단위로 기록합니다. 모듈을 고르면 그 기간의 학습노트가 주 단위 달력으로 펼쳐집니다.</p>
<p class="page-intro__stats"><span>학습노트 <b>{{ daily_posts.size }}</b>편</span><span>진행 모듈 <b>{{ current }}</b> / 6</span><span><a href="{{ '/database/' | relative_url }}#problems">문제풀이 {{ practice_posts.size }}편은 Database에서 보기</a></span></p>
</header>

<div class="stages is-cloud" role="tablist" aria-label="모듈 선택">
{%- for i in (1..6) -%}
{%- assign key = i | append: "" -%}
{%- assign mod_posts = daily_posts | where_exp: "p", "p.module == i" -%}
{%- if i < current -%}{%- assign st = "done" -%}{%- assign st_label = "완료 ✓" -%}
{%- elsif i == current -%}{%- assign st = "current" -%}{%- assign st_label = "지금 여기" -%}
{%- else -%}{%- assign st = "upcoming" -%}{%- assign st_label = "예정" -%}{%- endif -%}
<button type="button" class="stage is-{{ st }}{% if i == current %} is-active{% endif %}" data-tab="m{{ i }}" role="tab" aria-selected="{% if i == current %}true{% else %}false{% endif %}" aria-controls="panel-m{{ i }}">
<span class="stage__top"><span class="stage__num">모듈 {{ i }}</span><span class="stage__status">{{ st_label }}</span></span>
<span class="stage__name">{{ site.data.modules[key] | default: "미정" }}</span>
<span class="stage__meta">{% if mod_posts.size > 0 %}{{ mod_posts.size }}편{% else %}곧 출발{% endif %}</span>
</button>
{%- endfor -%}
</div>

{%- for i in (1..6) -%}
{%- assign key = i | append: "" -%}
{%- assign mod_posts = daily_posts | where_exp: "p", "p.module == i" | sort: "date" -%}
{%- assign mod_practice = practice_posts | where_exp: "p", "p.module == i" -%}
{%- assign mod_project = proj_main | where_exp: "p", "p.module == i" | first -%}
<section class="tab-panel module-panel" id="panel-m{{ i }}" data-panel="m{{ i }}" role="tabpanel"{% if i != current %} hidden{% endif %}>
<div class="module-panel__head">
<div class="module-panel__titles">
<span class="module-panel__eyebrow">모듈 {{ i }}</span>
<h2 class="module-panel__title">{{ site.data.modules[key] | default: "미정" }}</h2>
</div>
{%- if mod_posts.size > 0 -%}
<div class="module-stats">
<div class="stat">
<span class="stat__label">기간</span>
<span class="stat__value">{{ mod_posts.first.date | date: "%-m.%-d" }} – {{ mod_posts.last.date | date: "%-m.%-d" }}</span>
</div>
<div class="stat">
<span class="stat__label">학습노트</span>
<span class="stat__value">{{ mod_posts.size }}<small>편</small></span>
</div>
{%- if mod_practice.size > 0 -%}
<a class="stat stat--link is-database" href="{{ '/database/' | relative_url }}#problems">
<span class="stat__icon" aria-hidden="true">🧩</span>
<span class="stat__body"><span class="stat__label">문제풀이</span><span class="stat__value">{{ mod_practice.size }}<small>편</small></span></span>
<span class="stat__cta">Database에서 보기</span>
</a>
{%- else -%}
<div class="stat stat--muted"><span class="stat__label">문제풀이</span><span class="stat__value">아직 없음</span></div>
{%- endif -%}
{%- if mod_project -%}
<a class="stat stat--link is-projects" href="{{ mod_project.url | relative_url }}">
<span class="stat__icon" aria-hidden="true">{{ mod_project.banner_emoji | default: "🚀" }}</span>
<span class="stat__body"><span class="stat__label">결과물</span><span class="stat__value">{{ mod_project.project_name | default: mod_project.title | truncate: 16 }}</span></span>
<span class="stat__cta">회고 읽기</span>
</a>
{%- else -%}
<div class="stat stat--muted"><span class="stat__label">결과물</span><span class="stat__value">모듈이 끝나면 공개</span></div>
{%- endif -%}
</div>
{%- endif -%}
</div>

{%- if mod_posts.size == 0 -%}
<p class="empty-note">모듈 {{ i }}은 아직 시작 전입니다. 첫 학습노트를 쓰면 이곳에 주 단위 달력이 생깁니다.</p>
{%- else -%}
<div class="week-grid" role="list">
<div class="week-grid__head" aria-hidden="true"><span></span><span>월</span><span>화</span><span>수</span><span>목</span><span>금</span></div>
{%- assign weeks = mod_posts | group_by_exp: "p", "p.date | date: '%G-%V'" -%}
{%- for w in weeks -%}
{%- assign first = w.items.first -%}
{%- assign f_ts = first.date | date: "%s" | plus: 0 -%}
{%- assign f_u = first.date | date: "%u" | plus: 0 -%}
{%- assign back = f_u | minus: 1 | times: 86400 -%}
{%- assign mon_ts = f_ts | minus: back -%}
{%- assign fri_ts = mon_ts | plus: 345600 -%}
<div class="week" role="listitem">
<div class="week__label"><span class="week__n">{{ forloop.index }}주차</span><span class="week__range">{{ mon_ts | date: "%-m.%-d" }}–{{ fri_ts | date: "%-m.%-d" }}</span></div>
{%- for d in (1..5) -%}
{%- assign cell_off = d | minus: 1 | times: 86400 -%}
{%- assign cell_ts = mon_ts | plus: cell_off -%}
{%- assign cell_date = cell_ts | date: "%Y-%m-%d" -%}
{%- assign hit = nil -%}
{%- for p in w.items -%}{%- assign pu = p.date | date: "%u" | plus: 0 -%}{%- if pu == d -%}{%- assign hit = p -%}{%- endif -%}{%- endfor -%}
{%- if hit -%}
<a class="day" href="{{ hit.url | relative_url }}">
<span class="day__top"><span class="day__n">{{ hit.title }}</span><span class="day__date">{{ hit.date | date: "%-m.%-d" }}</span></span>
<span class="day__text">{{ hit.excerpt | strip_html | strip_newlines | truncate: 64 }}</span>
{%- if hit.tags.size > 0 -%}<span class="day__tags">{% for t in hit.tags limit: 2 %}<span class="chip">{{ t }}</span>{% endfor %}</span>{%- endif -%}
</a>
{%- elsif site.data.holidays contains cell_date -%}
<span class="day day--off"><span class="day__date">{{ cell_ts | date: "%-m.%-d" }}</span><span class="day__note">쉬는 날 🌙</span></span>
{%- else -%}
<span class="day day--empty" aria-hidden="true"><span class="day__date">{{ cell_ts | date: "%-m.%-d" }}</span></span>
{%- endif -%}
{%- endfor -%}
</div>
{%- endfor -%}
</div>
{%- endif -%}
</section>
{%- endfor -%}

<script>
(function () {
  var tabs = document.querySelectorAll('.stages .stage');
  var panels = document.querySelectorAll('.module-panel');
  tabs.forEach(function (tab) {
    tab.addEventListener('click', function () {
      var target = tab.getAttribute('data-tab');
      tabs.forEach(function (t) {
        var on = t === tab;
        t.classList.toggle('is-active', on);
        t.setAttribute('aria-selected', on ? 'true' : 'false');
      });
      panels.forEach(function (p) { p.hidden = p.getAttribute('data-panel') !== target; });
    });
  });
})();
</script>
