---
title: "Glossary"
layout: single
permalink: /glossary/
classes: section-page
---

{%- comment -%}
  개념 용어집. 글에서 개념 카드로 쓴 항목("- **용어**" 바로 아래 "  - 설명" 줄)을 빌드할 때 자동으로 모은다.
  따로 적을 것이 없다: 학습노트에 개념 카드를 쓰면 이 페이지에 저절로 올라온다.
  모듈별로 묶고, 모듈 안은 용어 이름 순. 스타일: .page-intro .group .row-list (DESIGN.md 4장 재사용)
{%- endcomment -%}

{%- assign terms = "" | split: "," -%}
{%- assign src_posts = site.posts | sort: "date" -%}
{%- for post in src_posts -%}
{%- comment -%} site.posts 의 content 는 이미 HTML 로 변환된 상태다. 개념 카드 = <li><strong>용어</strong> 바로 뒤에 <ul> {%- endcomment -%}
{%- assign chunks = post.content | split: "<li><strong>" -%}
{%- for chunk in chunks offset: 1 -%}
{%- assign halves = chunk | split: "</strong>" -%}
{%- assign rest = halves[1] | strip -%}
{%- assign rest_head = rest | slice: 0, 4 -%}
{%- if rest_head == "<ul>" -%}
{%- assign term = halves[0] | strip_html | strip -%}
{%- assign first_li = rest | split: "<li>" | slice: 1, 1 | first | split: "</li>" | first -%}
{%- assign desc = first_li | strip_html | strip_newlines | strip | truncate: 120 -%}
{%- if term != "" -%}
{%- capture entry -%}{{ post.module | default: 0 | plus: 100 }}|{{ term | replace: "|", "/" }}|{{ desc | replace: "|", "/" }}|{{ post.url }}|{{ post.date | date: "%m.%d" }}|{{ post.title | strip_html }}{%- endcapture -%}
{%- assign terms = terms | push: entry -%}
{%- endif -%}
{%- endif -%}
{%- endfor -%}
{%- endfor -%}
{%- assign terms = terms | sort -%}

<header class="page-intro is-signal">
<p class="term__cmd">grep -rh "^- \*\*" _posts | sort</p>
<h1 class="page-intro__title">개념 용어집</h1>
<p class="page-intro__desc">학습노트에서 개념 카드로 정리한 용어를 모았습니다. 용어를 누르면 그 개념을 설명한 글로 이동합니다.</p>
<p class="page-intro__stats"><span>용어 <b>{{ terms.size }}</b>개</span></p>
</header>

{%- if terms.size > 0 -%}
<label class="gloss-find" for="gloss-find-input">
<span class="gloss-find__prompt">$ grep -i</span>
<input id="gloss-find-input" class="gloss-find__input" type="search" placeholder="용어나 설명으로 걸러 보기" autocomplete="off" data-gloss-find>
</label>
<p class="jump-status" aria-live="polite" data-gloss-status></p>

{%- assign cur_module = "" -%}
{%- for e in terms -%}
{%- assign p = e | split: "|" -%}
{%- assign m = p[0] | minus: 100 -%}
{%- if p[0] != cur_module -%}
{%- unless forloop.first -%}</ul></section>{%- endunless -%}
{%- assign cur_module = p[0] -%}
{%- assign mkey = m | append: "" -%}
<section class="group gloss-group" data-gloss-group>
<div class="group__head">
<span class="group__key">{% if m > 0 %}M{{ m }}{% else %}--{% endif %}</span>
<h2 class="group__title">{% if m > 0 %}{{ site.data.modules[mkey] | default: "모듈" }}{% else %}모듈 없음{% endif %}</h2>
</div>
<ul class="row-list">
{%- endif -%}
<li data-gloss-item><a class="row" href="{{ p[3] | relative_url }}">
<span class="row__date">{{ p[4] }}</span>
<span class="row__main"><span class="row__title">{{ p[1] }}</span><span class="row__sub">{{ p[2] }}</span></span>
<span class="row__aside"><span class="cat-label">{{ p[5] | truncate: 14 }}</span></span>
</a></li>
{%- if forloop.last -%}</ul></section>{%- endif -%}
{%- endfor -%}
{%- else -%}
<p class="empty-note">아직 정리된 용어가 없습니다.</p>
{%- endif -%}
