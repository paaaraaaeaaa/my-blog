---
title: "Database"
layout: single
permalink: /database/
classes: section-page
---

{%- comment -%}
  Database = 자료 허브. 한 번에 한 섹션만 보인다(전체 보기 없음). 처음에는 첫 섹션(아티팩트)이 열린다.
  - 항목: _data/database_links.yml
  - 섹션 순서·톤·설명: _data/database_sections.yml (이름이 "문제"인 항목이 문제와 풀이 섹션의 자리)
  - "문제" 항목의 topics 값과 같은 topic을 가진 풀이 글(Cloud, type: practice)이 문제 카드 아래에 자동 연결된다
  - 어떤 문제에도 연결되지 않은 풀이 topic은 "문제 링크 미등록" 카드로 표시된다
  - 아티팩트와 문제와 풀이 섹션에는 모듈 칩(M1, M2 …)이 있다. 자료·문제에 module이 생기면 칩이 저절로 늘어난다.
    문제와 풀이는 /database/#problems-m2 로 그 모듈만 바로 열 수 있다
  스타일: assets/css/site.css "12. Database"
{%- endcomment -%}

{%- assign empty_array = "" | split: "," -%}
{%- assign db_links = site.data.database_links | default: empty_array -%}
{%- assign problems = db_links | where: "category", "문제" -%}
{%- assign others = db_links | where_exp: "i", "i.category != '문제'" -%}
{%- assign practice_posts = site.categories.Cloud | default: empty_array | where_exp: "p", "p.type == 'practice'" -%}
{%- assign linked_topics = "" -%}
{%- for pb in problems -%}{%- for t in pb.topics -%}{%- assign linked_topics = linked_topics | append: "|" | append: t | append: "|" -%}{%- endfor -%}{%- endfor -%}
{%- assign mod_list = "1,2,3,4,5,6,0" | split: "," -%}

<header class="page-intro is-database">
<p class="term__cmd">cd ~/database</p>
<h1 class="page-intro__title">자료와 문제, 그리고 풀이</h1>
<p class="page-intro__desc">수업에서 정리한 아티팩트, 부트캠프에서 받은 문제와 직접 푼 풀이, 감각을 익히는 게임을 한곳에 모았습니다.</p>
<p class="page-intro__stats"><span>자료 <b>{{ others.size }}</b>개</span><span>문제 <b>{{ problems.size }}</b>개</span><span>풀이 <b>{{ practice_posts.size }}</b>편</span></p>
</header>

{%- comment -%} 섹션 순서: database_sections.yml 순서 → 거기 없는 category(맨 뒤) {%- endcomment -%}
{%- assign section_defs = site.data.database_sections | default: empty_array -%}
{%- assign known = section_defs | map: "name" -%}
{%- assign other_cats = others | map: "category" | uniq -%}
{%- assign section_names = known -%}
{%- for c in other_cats -%}{%- unless known contains c -%}{%- assign section_names = section_names | push: c -%}{%- endunless -%}{%- endfor -%}
{%- comment -%} database_sections.yml에 "문제" 항목이 없어도 문제와 풀이 섹션이 사라지지 않게 맨 뒤에 붙인다 {%- endcomment -%}
{%- unless section_names contains "문제" -%}{%- assign section_names = section_names | push: "문제" -%}{%- endunless -%}

<nav class="jump-nav" aria-label="섹션 고르기">
{%- for name in section_names -%}
{%- assign def = section_defs | where: "name", name | first -%}
{%- if name == "문제" -%}
<button type="button" class="jump is-{{ def.tone | default: 'database' }}" data-filter="problems" aria-pressed="false">문제와 풀이<b>{{ problems.size }}</b></button>
{%- else -%}
{%- assign items = others | where: "category", name -%}
{%- if items.size > 0 -%}
<button type="button" class="jump is-{{ def.tone | default: 'signal' }}" data-filter="sec-{{ forloop.index }}" aria-pressed="false">{{ name }}<b>{{ items.size }}</b></button>
{%- endif -%}
{%- endif -%}
{%- endfor -%}
</nav>

{%- for name in section_names -%}
{%- assign sec_idx = forloop.index -%}
{%- assign sec_first = forloop.first -%}
{%- assign def = section_defs | where: "name", name | first -%}

{%- if name == "문제" -%}
<section class="res-section{% if sec_first %} res-section--first{% endif %} is-{{ def.tone | default: 'database' }}" id="problems" data-sec="problems">
<div class="res-section__head">
<div class="res-section__titles"><h2 class="res-section__title">문제와 풀이</h2>{% if def.desc %}<span class="res-section__desc">{{ def.desc }}</span>{% endif %}</div>
<span class="res-section__count">문제 {{ problems.size }}개 · 풀이 {{ practice_posts.size }}편</span>
</div>

{%- assign all_topics = practice_posts | sort: "date" | map: "topic" | uniq -%}

<nav class="jump-nav jump-nav--sub" aria-label="모듈 고르기">
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
<button type="button" class="jump is-database" data-mod="{{ m }}" aria-pressed="false"{% if m == 0 %} aria-label="모듈 없음"{% endif %}>{% if m > 0 %}M{{ m }}{% else %}--{% endif %}<b>{{ cards }}</b></button>
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

{%- else -%}
{%- assign items = others | where: "category", name -%}
{%- if items.size > 0 -%}
{%- assign series_items = items | where_exp: "i", "i.series" -%}
{%- assign has_series = false -%}
{%- if series_items.size > 0 -%}{%- assign has_series = true -%}{%- endif -%}
<section class="res-section{% if sec_first %} res-section--first{% endif %} is-{{ def.tone | default: 'signal' }}" id="sec-{{ sec_idx }}" data-sec="sec-{{ sec_idx }}"{% if has_series %} data-series-sec{% endif %}>
<div class="res-section__head">
<div class="res-section__titles"><h2 class="res-section__title">{{ name }}</h2>{% if def.desc %}<span class="res-section__desc">{{ def.desc }}</span>{% endif %}</div>
<span class="res-section__count">{{ items.size }}개</span>
</div>
{%- if has_series -%}
{%- comment -%} 시리즈(series)가 있는 섹션: 모듈 칩(M1, M2 …, 숫자는 그 모듈의 자료 수) + 걸러 보기 입력줄 {%- endcomment -%}
<nav class="jump-nav jump-nav--sub" aria-label="{{ name }} 모듈 고르기">
{%- for ms in mod_list -%}
{%- assign mm = ms | plus: 0 -%}
{%- if mm > 0 -%}{%- assign mitems = items | where_exp: "i", "i.module == mm" -%}{%- else -%}{%- assign mitems = items | where_exp: "i", "i.module == nil" -%}{%- endif -%}
{%- if mitems.size > 0 -%}
<button type="button" class="jump is-{{ def.tone | default: 'signal' }}" data-smod-chip="{{ mm }}" aria-pressed="false"{% if mm == 0 %} aria-label="모듈 없음"{% endif %}>{% if mm > 0 %}M{{ mm }}{% else %}--{% endif %}<b>{{ mitems.size }}</b></button>
{%- endif -%}
{%- endfor -%}
</nav>
<label class="gloss-find" for="series-find-{{ sec_idx }}">
<span class="gloss-find__prompt" aria-hidden="true">$ grep -i</span>
<input id="series-find-{{ sec_idx }}" class="gloss-find__input" type="search" placeholder="제목이나 설명으로 걸러 보기" aria-label="제목이나 설명으로 걸러 보기" autocomplete="off" data-series-find>
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
{%- if gm != -1 and has_series == false -%}
{%- assign gkey = gm | append: "" -%}
<div class="group">
<div class="group__head">
<span class="group__key">{% if gm > 0 %}M{{ gm }}{% else %}--{% endif %}</span>
<h3 class="group__title">{% if gm > 0 %}{{ site.data.modules[gkey] | default: "모듈" }}{% else %}모듈 없음{% endif %}</h3>
<span class="group__count">{{ gitems.size }}개</span>
</div>
{%- endif -%}
{%- if has_series -%}
{%- comment -%} 시리즈 묶음: 2개 이상이면 접는 <details>, 하나뿐이면 접지 않는 한 줄. 가장 최근 모듈의 시리즈만 펼쳐 둔다. 모듈 묶음 머리 없이 모듈은 M2 글자로만 표시 {%- endcomment -%}
{%- assign sgroups = gitems | group_by: "series" -%}
<div class="series-list">
{%- for sg in sgroups -%}
{%- assign smod = sg.items.first.module -%}
{%- assign sg_num = sg.items | where_exp: "i", "i.num" -%}
{%- if sg.items.size > 1 and sg.name and sg.name != "" -%}
<details class="series" data-series="{{ sg.name }}" data-smod="{{ smod | default: 0 }}"{% if gm == -1 or gm == latest_m %} open{% endif %}>
<summary class="series__head"><span class="series__name">{{ sg.name }}</span>{% if smod %}<span class="series__mod">M{{ smod }}</span>{% endif %}<span class="series__count">{{ sg.items.size }}개</span></summary>
{%- else -%}
<div class="series series--single" data-series="{{ sg.name }}" data-smod="{{ smod | default: 0 }}">
{%- endif -%}
<ul class="row-list row-list--series">
{%- for item in sg.items -%}
<li data-series-item><a class="row{% if sg_num.size > 0 %} row--numbered{% endif %}" href="{% if item.url %}{{ item.url }}{% else %}{{ item.file | relative_url }}{% endif %}" target="_blank" rel="noopener noreferrer">{% if sg_num.size > 0 %}<span class="row__num">{{ item.num }}</span>{% endif %}<span class="row__main"><span class="row__title">{{ item.short | default: item.title }}</span>{% if item.description %}<span class="row__sub">{{ item.description }}</span>{% endif %}</span><span class="row__aside" aria-hidden="true">{% if sg.items.size == 1 and smod %}M{{ smod }} · {% endif %}open ↗</span></a></li>
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
{%- if gm != -1 and has_series == false -%}
</div>
{%- endif -%}
{%- endif -%}
{%- endfor -%}
</section>
{%- endif -%}
{%- endif -%}
{%- endfor -%}

<script>
/* 섹션 고르기: 항상 한 섹션만 보인다(처음엔 첫 칩). 칩을 누르면 그 섹션으로 바뀌고, 이미 열린 칩은 그대로 둔다.
   /database/#problems 처럼 주소에 섹션이 있으면 그 섹션을, 없으면 첫 섹션을 연다 */
(function () {
  var chips = document.querySelectorAll('.jump[data-filter]');
  var sections = document.querySelectorAll('[data-sec]');
  if (!chips.length) return;
  function apply(key, writeHash) {
    chips.forEach(function (c) {
      var on = c.getAttribute('data-filter') === key;
      c.classList.toggle('is-active', on);
      c.setAttribute('aria-pressed', on ? 'true' : 'false');
    });
    sections.forEach(function (sec) {
      var was = sec.hidden;
      sec.hidden = sec.getAttribute('data-sec') !== key;
      if (was && !sec.hidden && window.swapIn) window.swapIn(sec);
    });
    document.body.classList.add('is-filtered');
    if (writeHash && history.replaceState) history.replaceState(null, '', '#' + key);
  }
  chips.forEach(function (c) {
    c.addEventListener('click', function () { apply(c.getAttribute('data-filter'), true); });
  });
  var hash = location.hash.replace('#', '');
  if (/^problems-m\d+$/.test(hash)) hash = 'problems';
  var start = hash && document.querySelector('[data-sec="' + hash + '"]') ? hash : chips[0].getAttribute('data-filter');
  apply(start, false);
  window.addEventListener('hashchange', function () {
    var h = location.hash.replace('#', '');
    if (/^problems-m\d+$/.test(h)) h = 'problems';
    if (h && document.querySelector('[data-sec="' + h + '"]')) apply(h, false);
  });
})();
/* 모듈 걸러 보기(문제와 풀이 섹션 안): 칩 하나만 선택, 같은 칩을 다시 누르면 해제.
   /database/#problems-m2 처럼 들어오면 문제 섹션을 열고 모듈 2만 보여준다 */
(function () {
  var chips = document.querySelectorAll('.jump[data-mod]');
  var groups = document.querySelectorAll('.group[data-mod]');
  if (!chips.length) return;
  var active = null;
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
    if (writeHash && history.replaceState) history.replaceState(null, '', m !== null ? '#problems-m' + m : '#problems');
  }
  chips.forEach(function (c) {
    c.addEventListener('click', function () {
      var m = c.getAttribute('data-mod');
      setMod(active === m ? null : m, true);
    });
  });
  var mm = /^problems-m(\d+)$/.exec(location.hash.replace('#', ''));
  if (mm && document.querySelector('.jump[data-mod="' + mm[1] + '"]')) {
    setMod(mm[1], false);
    var sec = document.getElementById('problems');
    if (sec && sec.scrollIntoView) sec.scrollIntoView({ block: 'start' });
  }
})();
/* 시리즈가 있는 자료 섹션(아티팩트): 모듈 칩 하나 + 걸러 보기 입력줄. 거르는 동안 맞는 시리즈는 펼치고, 풀면 원래 접힘 상태로 돌린다 */
(function () {
  document.querySelectorAll('[data-series-sec]').forEach(function (sec) {
    var chips = sec.querySelectorAll('[data-smod-chip]');
    var input = sec.querySelector('[data-series-find]');
    var status = sec.querySelector('[data-series-status]');
    var blocks = sec.querySelectorAll('.series');
    var activeMod = null;
    var filtering = false;
    function run() {
      var q = input ? input.value.trim().toLowerCase() : '';
      var now = activeMod !== null || q !== '';
      if (now && !filtering) {
        blocks.forEach(function (b) { if (b.tagName === 'DETAILS') b.setAttribute('data-was-open', b.open ? '1' : '0'); });
      }
      var total = 0;
      blocks.forEach(function (b) {
        var scope = activeMod === null || b.getAttribute('data-smod') === activeMod;
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
      filtering = now;
      if (status) {
        status.textContent = !now ? '' : (total ? (activeMod !== null ? 'M' + activeMod + ' ' : '') + total + '개 보는 중이에요.' : '맞는 자료가 없어요.');
      }
    }
    chips.forEach(function (c) {
      c.addEventListener('click', function () {
        var key = c.getAttribute('data-smod-chip');
        activeMod = activeMod === key ? null : key;
        chips.forEach(function (x) {
          var on = x.getAttribute('data-smod-chip') === activeMod;
          x.classList.toggle('is-active', on);
          x.setAttribute('aria-pressed', on ? 'true' : 'false');
        });
        run();
      });
    });
    if (input) input.addEventListener('input', run);
  });
})();
</script>
