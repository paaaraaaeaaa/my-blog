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
  - 문제와 풀이는 module 번호별 .group으로 묶이고, 모듈 칩(.jump[data-mod])으로 하나만 걸러 볼 수 있다. /database/#problems-m2 로 바로 열 수 있다
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
<p class="term__cmd">cd ~/database</p>
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
<button type="button" class="jump is-database" data-filter="problems" data-label="문제와 풀이" aria-pressed="false">문제와 풀이<b>{{ problems.size }}</b></button>
{%- for name in section_names -%}
{%- assign items = others | where: "category", name -%}
{%- if items.size > 0 -%}
{%- assign def = section_defs | where: "name", name | first -%}
<button type="button" class="jump is-{{ def.tone | default: 'signal' }}" data-filter="sec-{{ forloop.index }}" data-label="{{ name }}" aria-pressed="false">{{ name }}<b>{{ items.size }}</b></button>
{%- endif -%}
{%- endfor -%}
<button type="button" class="jump-reset" data-filter-reset aria-label="전체 보기로 초기화" title="전체 보기" disabled>
<svg viewBox="0 0 24 24" width="18" height="18" aria-hidden="true"><path d="M20 11a8 8 0 1 0-2.34 5.66" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"/><path d="M20 5v6h-6" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/></svg>
<span>reset</span>
</button>
</nav>
<p class="jump-status" aria-live="polite"></p>

<section class="res-section res-section--first is-database" id="problems" data-sec="problems">
<div class="res-section__head">
<div class="res-section__titles"><h2 class="res-section__title">문제와 풀이</h2><span class="res-section__desc">받은 문제와 레벨별 풀이를 한 카드에 묶었어요</span></div>
<span class="res-section__count">문제 {{ problems.size }}개 · 풀이 {{ practice_posts.size }}편</span>
</div>

{%- assign all_topics = practice_posts | sort: "date" | map: "topic" | uniq -%}
{%- assign mod_list = "1,2,3,4,5,6,0" | split: "," -%}

<nav class="jump-nav jump-nav--sub" aria-label="모듈 골라 보기">
{%- for ms in mod_list -%}
{%- assign m = ms | plus: 0 -%}
{%- if m > 0 -%}
{%- assign m_probs = problems | where_exp: "p", "p.module == m" -%}
{%- assign m_posts = practice_posts | where_exp: "p", "p.module == m" -%}
{%- else -%}
{%- assign m_probs = problems | where_exp: "p", "p.module == nil" -%}
{%- assign m_posts = practice_posts | where_exp: "p", "p.module == nil" -%}
{%- endif -%}
{%- if m_probs.size > 0 or m_posts.size > 0 -%}
{%- assign cards = m_probs.size -%}
{%- for t in all_topics -%}
{%- assign needle = "|" | append: t | append: "|" -%}
{%- unless linked_topics contains needle -%}
{%- assign tsols = practice_posts | where: "topic", t -%}
{%- assign tm = tsols.first.module | default: 0 -%}
{%- if tm == m -%}{%- assign cards = cards | plus: 1 -%}{%- endif -%}
{%- endunless -%}
{%- endfor -%}
<button type="button" class="jump is-database" data-mod="{{ m }}" data-label="{% if m > 0 %}모듈 {{ m }}{% else %}모듈 없음{% endif %}" aria-pressed="false">{% if m > 0 %}모듈 {{ m }}{% else %}모듈 없음{% endif %}<b>{{ cards }}</b></button>
{%- endif -%}
{%- endfor -%}
</nav>

{%- for ms in mod_list -%}
{%- assign m = ms | plus: 0 -%}
{%- assign mkey = m | append: "" -%}
{%- if m > 0 -%}
{%- assign m_probs = problems | where_exp: "p", "p.module == m" -%}
{%- assign m_posts = practice_posts | where_exp: "p", "p.module == m" -%}
{%- else -%}
{%- assign m_probs = problems | where_exp: "p", "p.module == nil" -%}
{%- assign m_posts = practice_posts | where_exp: "p", "p.module == nil" -%}
{%- endif -%}
{%- if m_probs.size > 0 or m_posts.size > 0 -%}
<div class="group" data-mod="{{ m }}">
<div class="group__head">
<span class="group__key">{% if m > 0 %}M{{ m }}{% else %}--{% endif %}</span>
<h3 class="group__title">{% if m > 0 %}{{ site.data.modules[mkey] | default: "모듈" }}{% else %}모듈 없음{% endif %}</h3>
<span class="group__count">문제 {{ m_probs.size }}개 · 풀이 {{ m_posts.size }}편</span>
</div>

<div class="problem-list">
{%- for pb in m_probs -%}
{%- assign sols = empty_array -%}
{%- for t in pb.topics -%}{%- assign found = practice_posts | where: "topic", t -%}{%- assign sols = sols | concat: found -%}{%- endfor -%}
{%- assign sols = sols | sort: "date" | sort: "level_order" -%}
<article class="problem">
<div class="problem__head">
<div class="problem__info">
<span class="problem__meta">{% if sols.size > 0 %}<span>풀이 {{ sols.size }}편</span><span>{{ sols.first.date | date: "%-m.%-d" }} 풀이</span>{% else %}<span>풀이 없음</span>{% endif %}</span>
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

{%- for t in all_topics -%}
{%- assign needle = "|" | append: t | append: "|" -%}
{%- unless linked_topics contains needle -%}
{%- assign sols = practice_posts | where: "topic", t | sort: "level_order" -%}
{%- assign tm = sols.first.module | default: 0 -%}
{%- if tm == m -%}
<article class="problem problem--unlinked">
<div class="problem__head">
<div class="problem__info">
<span class="problem__meta"><span>풀이 {{ sols.size }}편</span><span>문제 링크 미등록</span></span>
<span class="problem__title">{{ t }}</span>
</div>
</div>
<ol class="steps">
{%- for post in sols -%}
<li><a class="step" href="{{ post.url | relative_url }}"><span class="step__lv">풀이 {{ forloop.index }}</span><span class="step__title">{{ post.title }}</span></a></li>
{%- endfor -%}
</ol>
</article>
{%- endif -%}
{%- endunless -%}
{%- endfor -%}
</div>
</div>
{%- endif -%}
{%- endfor -%}
</section>

{%- for name in section_names -%}
{%- assign items = others | where: "category", name -%}
{%- if items.size > 0 -%}
{%- assign def = section_defs | where: "name", name | first -%}
{%- assign series_items = items | where_exp: "i", "i.series" -%}
{%- assign has_series = false -%}
{%- if series_items.size > 0 -%}{%- assign has_series = true -%}{%- endif -%}
<section class="res-section is-{{ def.tone | default: 'signal' }}" id="sec-{{ forloop.index }}" data-sec="sec-{{ forloop.index }}"{% if has_series %} data-series-sec{% endif %}>
<div class="res-section__head">
<div class="res-section__titles"><h2 class="res-section__title">{{ name }}</h2>{% if def.desc %}<span class="res-section__desc">{{ def.desc }}</span>{% endif %}</div>
<span class="res-section__count">{{ items.size }}개</span>
</div>
{%- if has_series -%}
{%- comment -%} 시리즈(series)가 있는 섹션: 주제 칩 + 걸러 보기 입력줄. 칩은 series 이름, 숫자는 그 시리즈의 자료 수 {%- endcomment -%}
{%- assign all_series = items | group_by: "series" -%}
<nav class="jump-nav jump-nav--sub" aria-label="{{ name }} 주제 골라 보기">
{%- for sg in all_series -%}
{%- if sg.name != nil and sg.name != "" -%}
<button type="button" class="jump" data-series-chip="{{ sg.name }}" aria-pressed="false">{{ sg.name }}<b>{{ sg.items.size }}</b></button>
{%- endif -%}
{%- endfor -%}
</nav>
<label class="gloss-find" for="series-find-{{ forloop.index }}">
<span class="gloss-find__prompt" aria-hidden="true">$ grep -i</span>
<input id="series-find-{{ forloop.index }}" class="gloss-find__input" type="search" placeholder="제목이나 설명으로 걸러 보기" aria-label="제목이나 설명으로 걸러 보기" autocomplete="off" data-series-find>
</label>
<p class="jump-status" aria-live="polite" data-series-status></p>
{%- endif -%}
{%- assign with_mod = items | where_exp: "i", "i.module" -%}
{%- assign latest_m = with_mod | map: "module" | sort | last -%}
{%- if with_mod.size > 0 -%}{%- assign glist = mod_list -%}{%- else -%}{%- assign glist = "-1" | split: "," -%}{%- endif -%}
{%- for gs in glist -%}
{%- assign gm = gs | plus: 0 -%}
{%- if gm == -1 -%}{%- assign gitems = items -%}
{%- elsif gm > 0 -%}{%- assign gitems = items | where_exp: "i", "i.module == gm" -%}
{%- else -%}{%- assign gitems = items | where_exp: "i", "i.module == nil" -%}{%- endif -%}
{%- if gitems.size > 0 -%}
{%- if gm != -1 -%}
{%- assign gkey = gm | append: "" -%}
<div class="group">
<div class="group__head">
<span class="group__key">{% if gm > 0 %}M{{ gm }}{% else %}--{% endif %}</span>
<h3 class="group__title">{% if gm > 0 %}{{ site.data.modules[gkey] | default: "모듈" }}{% else %}모듈 없음{% endif %}</h3>
<span class="group__count">{{ gitems.size }}개</span>
</div>
{%- endif -%}
{%- if has_series -%}
{%- comment -%} 시리즈 묶음: 2개 이상이면 접는 <details>, 하나뿐이면 접지 않는 한 줄. 가장 최근 모듈의 시리즈만 펼쳐 둔다 {%- endcomment -%}
{%- assign sgroups = gitems | group_by: "series" -%}
<div class="series-list">
{%- for sg in sgroups -%}
{%- if sg.items.size > 1 and sg.name and sg.name != "" -%}
<details class="series" data-series="{{ sg.name }}"{% if gm == -1 or gm == latest_m %} open{% endif %}>
<summary class="series__head"><span class="series__name">{{ sg.name }}</span><span class="series__count">{{ sg.items.size }}개</span></summary>
{%- else -%}
<div class="series series--single" data-series="{{ sg.name }}">
{%- endif -%}
<ul class="row-list row-list--series">
{%- for item in sg.items -%}
<li data-series-item><a class="row row--numbered" href="{% if item.url %}{{ item.url }}{% else %}{{ item.file | relative_url }}{% endif %}" target="_blank" rel="noopener noreferrer"><span class="row__num">{{ item.no | default: "·" }}</span><span class="row__main"><span class="row__title">{{ item.short | default: item.title }}</span>{% if item.description %}<span class="row__sub">{{ item.description }}</span>{% endif %}</span><span class="row__aside" aria-hidden="true">open ↗</span></a></li>
{%- endfor -%}
</ul>
{%- if sg.items.size > 1 and sg.name and sg.name != "" -%}</details>{%- else -%}</div>{%- endif -%}
{%- endfor -%}
</div>
{%- else -%}
<div class="res-grid">
{%- for item in gitems -%}
{%- if item.url -%}{%- assign host = item.url | split: "//" | last | split: "/" | first | remove: "www." -%}{%- else -%}{%- assign host = "첨부파일" -%}{%- endif -%}
<article class="res-card">
<div class="res-card__top">
{%- if item.url -%}<img class="res-card__favicon" src="https://www.google.com/s2/favicons?domain={{ host }}&sz=64" alt="" loading="lazy" width="20" height="20">{%- else -%}<span class="res-card__favicon res-card__favicon--file" aria-hidden="true">F</span>{%- endif -%}
<span class="res-card__host">{{ host }}</span>
</div>
{%- if item.url -%}
<a class="res-card__title res-card__stretch" href="{{ item.url }}" target="_blank" rel="noopener noreferrer">{{ item.title }}</a>
{%- else -%}
<span class="res-card__title">{{ item.title }}</span>
{%- endif -%}
{% if item.description %}<span class="res-card__desc">{{ item.description }}</span>{% endif %}
<div class="res-card__foot">
{% if item.url %}<span class="res-card__open">open ↗</span>{% endif %}
{% if item.file %}<a class="btn-line res-card__file" href="{{ item.file | relative_url }}" target="_blank" rel="noopener noreferrer">첨부파일</a>{% endif %}
</div>
</article>
{%- endfor -%}
</div>
{%- endif -%}
{%- if gm != -1 -%}
</div>
{%- endif -%}
{%- endif -%}
{%- endfor -%}
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
    sections.forEach(function (sec) {
      var was = sec.hidden;
      sec.hidden = key !== null && sec.getAttribute('data-sec') !== key;
      if (was && !sec.hidden && window.swapIn) window.swapIn(sec);
    });
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
/* 모듈 걸러 보기(문제와 풀이 섹션 안): 칩 하나만 선택, 같은 칩을 다시 누르면 해제.
   /database/#problems-m2 처럼 들어오면 문제 섹션을 열고 모듈 2만 보여준다 */
(function () {
  var chips = document.querySelectorAll('.jump[data-mod]');
  var groups = document.querySelectorAll('.group[data-mod]');
  if (!chips.length) return;
  var active = null;
  var reset = document.querySelector('[data-filter-reset]');
  var status = document.querySelector('.jump-status');
  function setMod(m, writeHash) {
    active = m;
    chips.forEach(function (c) {
      var on = c.getAttribute('data-mod') === m;
      c.classList.toggle('is-active', on);
      c.setAttribute('aria-pressed', on ? 'true' : 'false');
    });
    groups.forEach(function (g) {
      var was = g.hidden;
      g.hidden = m !== null && g.getAttribute('data-mod') !== m;
      if (was && !g.hidden && window.swapIn) window.swapIn(g);
    });
    if (reset) reset.disabled = m === null && !document.body.classList.contains('is-filtered');
    if (status && (m !== null || writeHash)) {
      var chip = m !== null && document.querySelector('.jump[data-mod="' + m + '"]');
      var top = document.querySelector('.jump[data-filter].is-active');
      status.textContent = chip ? '‘' + chip.getAttribute('data-label') + '’ 문제만 보는 중이에요. 같은 칩이나 ↻ 전체를 누르면 다시 모두 보여요.'
        : (top ? '‘' + top.getAttribute('data-label') + '’ 섹션만 보는 중이에요. 같은 칩이나 ↻ 전체를 누르면 다시 모두 보여요.' : '');
    }
    if (writeHash && history.replaceState) history.replaceState(null, '', m !== null ? '#problems-m' + m : '#problems');
  }
  chips.forEach(function (c) {
    c.addEventListener('click', function () {
      var m = c.getAttribute('data-mod');
      setMod(active === m ? null : m, true);
    });
  });
  // 위쪽 섹션 칩·전체 초기화를 누르면 모듈 걸러 보기도 풀린다
  document.querySelectorAll('.jump[data-filter], [data-filter-reset]').forEach(function (b) {
    b.addEventListener('click', function () { setMod(null, false); });
  });
  var mm = /^problems-m(\d+)$/.exec(location.hash.replace('#', ''));
  if (mm && document.querySelector('.jump[data-mod="' + mm[1] + '"]')) {
    var pc = document.querySelector('.jump[data-filter="problems"]');
    if (pc && !pc.classList.contains('is-active')) pc.click();
    setMod(mm[1], true);
    var sec = document.getElementById('problems');
    if (sec && sec.scrollIntoView) sec.scrollIntoView({ block: 'start' });
  }
})();
/* 시리즈가 있는 자료 섹션 걸러 보기: 주제 칩 하나 + 입력줄. 거르는 동안 맞는 시리즈는 펼치고, 풀면 원래 접힘 상태로 돌린다 */
(function () {
  document.querySelectorAll('[data-series-sec]').forEach(function (sec) {
    var chips = sec.querySelectorAll('[data-series-chip]');
    var input = sec.querySelector('[data-series-find]');
    var status = sec.querySelector('[data-series-status]');
    var blocks = sec.querySelectorAll('.series');
    var groups = sec.querySelectorAll('.group');
    var active = null;
    var filtering = false;
    function run() {
      var q = input ? input.value.trim().toLowerCase() : '';
      var now = active !== null || q !== '';
      if (now && !filtering) {
        blocks.forEach(function (b) { if (b.tagName === 'DETAILS') b.setAttribute('data-was-open', b.open ? '1' : '0'); });
      }
      var total = 0;
      blocks.forEach(function (b) {
        var scope = active === null || b.getAttribute('data-series') === active;
        var shown = 0;
        b.querySelectorAll('[data-series-item]').forEach(function (li) {
          var t = li.querySelector('.row__title');
          var d = li.querySelector('.row__sub');
          var text = ((t ? t.textContent : '') + ' ' + (d ? d.textContent : '')).toLowerCase();
          var hit = scope && (q === '' || text.indexOf(q) !== -1);
          li.hidden = !hit;
          if (hit) shown++;
        });
        b.hidden = shown === 0;
        total += shown;
        if (b.tagName === 'DETAILS') {
          if (now) b.open = shown > 0;
          else if (filtering) b.open = b.getAttribute('data-was-open') === '1';
        }
      });
      groups.forEach(function (g) {
        var was = g.hidden;
        g.hidden = !g.querySelector('.series:not([hidden])');
        if (was && !g.hidden && window.swapIn) window.swapIn(g);
      });
      filtering = now;
      if (status) {
        status.textContent = !now ? '' : (total ? (active !== null ? '‘' + active + '’ ' : '') + total + '개 보는 중이에요. 같은 칩을 다시 누르면 모두 보여요.' : '맞는 자료가 없어요.');
      }
    }
    chips.forEach(function (c) {
      c.addEventListener('click', function () {
        var key = c.getAttribute('data-series-chip');
        active = active === key ? null : key;
        chips.forEach(function (x) {
          var on = x.getAttribute('data-series-chip') === active;
          x.classList.toggle('is-active', on);
          x.setAttribute('aria-pressed', on ? 'true' : 'false');
        });
        run();
      });
    });
    if (input) input.addEventListener('input', run);
    // 위쪽 섹션 칩·전체 초기화(↻)를 누르면 이 걸러 보기도 함께 풀린다
    document.querySelectorAll('.jump[data-filter], [data-filter-reset]').forEach(function (b) {
      b.addEventListener('click', function () {
        if (active === null && (!input || input.value === '')) return;
        active = null;
        if (input) input.value = '';
        chips.forEach(function (x) { x.classList.remove('is-active'); x.setAttribute('aria-pressed', 'false'); });
        run();
      });
    });
  });
})();
</script>
