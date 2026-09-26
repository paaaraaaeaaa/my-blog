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

{%- comment -%} 섹션 순서: 문제와 풀이 → database_sections.yml 순서 → 거기 없는 category {%- endcomment -%}
{%- assign section_defs = site.data.database_sections | default: empty_array -%}
{%- assign known = section_defs | map: "name" -%}
{%- assign other_cats = others | map: "category" | uniq -%}
{%- assign section_names = known -%}
{%- for c in other_cats -%}{%- unless known contains c -%}{%- assign section_names = section_names | push: c -%}{%- endunless -%}{%- endfor -%}

<nav class="jump-nav" aria-label="섹션 골라 보기">
<button type="button" class="jump is-database" data-filter="problems" data-label="문제와 풀이" aria-pressed="false"><span aria-hidden="true">🧩</span>문제와 풀이<b>{{ problems.size }}</b></button>
{%- for name in section_names -%}
{%- assign items = others | where: "category", name -%}
{%- if items.size > 0 -%}
{%- assign def = section_defs | where: "name", name | first -%}
<button type="button" class="jump is-{{ def.tone | default: 'signal' }}" data-filter="sec-{{ forloop.index }}" data-label="{{ name }}" aria-pressed="false"><span aria-hidden="true">{{ def.emoji | default: "📁" }}</span>{{ name }}<b>{{ items.size }}</b></button>
{%- endif -%}
{%- endfor -%}
<button type="button" class="jump-reset" data-filter-reset aria-label="전체 보기로 초기화" title="전체 보기" disabled>
<svg viewBox="0 0 24 24" width="18" height="18" aria-hidden="true"><path d="M20 11a8 8 0 1 0-2.34 5.66" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"/><path d="M20 5v6h-6" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/></svg>
<span>전체</span>
</button>
</nav>
<p class="jump-status" aria-live="polite"></p>

<section class="res-section res-section--first is-database" id="problems" data-sec="problems">
<div class="res-section__head">
<span class="res-section__icon" aria-hidden="true">🧩</span>
<div class="res-section__titles"><h2 class="res-section__title">문제와 풀이</h2><span class="res-section__desc">받은 문제와 레벨별 풀이를 한 카드에 묶었어요</span></div>
<span class="res-section__count">문제 {{ problems.size }}개 · 풀이 {{ practice_posts.size }}편</span>
</div>

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
</section>

{%- for name in section_names -%}
{%- assign items = others | where: "category", name -%}
{%- if items.size > 0 -%}
{%- assign def = section_defs | where: "name", name | first -%}
<section class="res-section is-{{ def.tone | default: 'signal' }}" id="sec-{{ forloop.index }}" data-sec="sec-{{ forloop.index }}">
<div class="res-section__head">
<span class="res-section__icon" aria-hidden="true">{{ def.emoji | default: "📁" }}</span>
<div class="res-section__titles"><h2 class="res-section__title">{{ name }}</h2>{% if def.desc %}<span class="res-section__desc">{{ def.desc }}</span>{% endif %}</div>
<span class="res-section__count">{{ items.size }}개</span>
</div>
<div class="res-grid">
{%- for item in items -%}
{%- if item.url -%}{%- assign host = item.url | split: "//" | last | split: "/" | first | remove: "www." -%}{%- else -%}{%- assign host = "첨부파일" -%}{%- endif -%}
<article class="res-card">
<div class="res-card__top">
{%- if item.url -%}<img class="res-card__favicon" src="https://www.google.com/s2/favicons?domain={{ host }}&sz=64" alt="" loading="lazy" width="20" height="20">{%- else -%}<span class="res-card__favicon res-card__favicon--file" aria-hidden="true">📎</span>{%- endif -%}
<span class="res-card__host">{{ host }}</span>
</div>
{%- if item.url -%}
<a class="res-card__title res-card__stretch" href="{{ item.url }}" target="_blank" rel="noopener noreferrer">{{ item.title }}</a>
{%- else -%}
<span class="res-card__title">{{ item.title }}</span>
{%- endif -%}
{% if item.description %}<span class="res-card__desc">{{ item.description }}</span>{% endif %}
<div class="res-card__foot">
{% if item.url %}<span class="res-card__open">새 탭에서 열기</span>{% endif %}
{% if item.file %}<a class="btn-line res-card__file" href="{{ item.file | relative_url }}" target="_blank" rel="noopener noreferrer">📎 첨부파일</a>{% endif %}
</div>
</article>
{%- endfor -%}
</div>
</section>
{%- endif -%}
{%- endfor -%}

<script>
/* 섹션 필터: 칩 하나만 선택(다른 칩을 누르면 전환), 같은 칩을 다시 누르거나 ↻를 누르면 전체 보기 */
(function () {
  var chips = document.querySelectorAll('.jump[data-filter]');
  var reset = document.querySelector('[data-filter-reset]');
  var status = document.querySelector('.jump-status');
  var sections = document.querySelectorAll('[data-sec]');
  var active = null;
  function apply(key) {
    active = key;
    chips.forEach(function (c) {
      var on = c.getAttribute('data-filter') === key;
      c.classList.toggle('is-active', on);
      c.setAttribute('aria-pressed', on ? 'true' : 'false');
    });
    sections.forEach(function (sec) { sec.hidden = key !== null && sec.getAttribute('data-sec') !== key; });
    document.body.classList.toggle('is-filtered', key !== null);
    if (reset) reset.disabled = key === null;
    if (status) {
      var chip = key && document.querySelector('.jump[data-filter="' + key + '"]');
      status.textContent = chip ? '‘' + chip.getAttribute('data-label') + '’ 섹션만 보는 중이에요. 같은 칩이나 ↻ 전체를 누르면 다시 모두 보여요.' : '';
    }
    if (history.replaceState) history.replaceState(null, '', key ? '#' + key : location.pathname);
  }
  chips.forEach(function (c) {
    c.addEventListener('click', function () {
      var key = c.getAttribute('data-filter');
      apply(active === key ? null : key);
    });
  });
  if (reset) reset.addEventListener('click', function () { apply(null); });
  // 다른 페이지에서 /database/#problems 로 들어오면 그 섹션만 보여준다
  var hash = location.hash.replace('#', '');
  if (hash && document.querySelector('[data-sec="' + hash + '"]')) apply(hash);
})();
</script>
