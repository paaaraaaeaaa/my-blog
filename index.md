---
layout: single
title: 학습 노트
author_profile: true
classes: home-page
home_side: true
---

{%- comment -%}
  홈 = 한눈에 보는 대시보드. 스타일은 assets/css/site.css "10. 홈" 섹션.
  순서: 첫 구역(상태 창 + 방명록, 2열) → 최근 글 → 섹션 바로가기 → GitHub 활동 (둘째 구역부터는 가운데+오른쪽 폭을 합쳐 쓴다)
{%- endcomment -%}

{%- comment -%}
  수료까지 모듈 진척: 완료 = Projects 결과물 글이 있는 모듈 / 진행 중 = 학습노트가 있고 결과물이 아직 없는 첫 모듈.
  진행 중 막대는 _data/module_schedule.yml에 날짜가 있으면 그 비율만큼, 없으면 빗금으로 표시.
{%- endcomment -%}
{%- assign mp_proj = site.categories.Projects | where_exp: "p", "p.type == nil" -%}
{%- assign mp_daily = site.categories.Cloud | where_exp: "p", "p.type != 'practice'" | where_exp: "p", "p.module" -%}
{%- assign mp_done = 0 -%}{%- assign mp_current = 0 -%}{%- assign mp_now = site.time | date: "%s" | plus: 0 -%}
{%- capture mp_bars -%}
{%- for i in (1..6) -%}
{%- assign key = i | append: "" -%}
{%- assign has_proj = mp_proj | where_exp: "p", "p.module == i" | size -%}
{%- assign notes = mp_daily | where_exp: "p", "p.module == i" | size -%}
{%- assign fill = 0 -%}{%- assign st = "upcoming" -%}{%- assign st_label = "예정" -%}
{%- if has_proj > 0 -%}
{%- assign st = "done" -%}{%- assign st_label = "완료" -%}{%- assign fill = 100 -%}{%- assign mp_done = mp_done | plus: 1 -%}
{%- elsif mp_current == 0 and notes > 0 -%}
{%- assign st = "current" -%}{%- assign st_label = "진행 중" -%}{%- assign mp_current = i -%}
{%- assign sched = site.data.module_schedule[key] -%}
{%- if sched and sched.start and sched.end -%}
{%- assign s0 = sched.start | date: "%s" | plus: 0 -%}{%- assign s1 = sched.end | date: "%s" | plus: 0 -%}
{%- assign span = s1 | minus: s0 -%}{%- assign fill = mp_now | minus: s0 | times: 100 | divided_by: span -%}
{%- if fill > 100 -%}{%- assign fill = 100 -%}{%- endif -%}{%- if fill < 0 -%}{%- assign fill = 0 -%}{%- endif -%}
{%- else -%}{%- assign fill = -1 -%}{%- endif -%}
{%- endif -%}
<span class="mbar is-{{ st }}" title="모듈 {{ i }} · {{ site.data.modules[key] }} · {{ st_label }}"><span class="mbar__track">{%- if fill == -1 -%}<span class="mbar__fill mbar__fill--hatch"></span>{%- elsif fill > 0 -%}<span class="mbar__fill" style="width: {{ fill }}%;"></span>{%- endif -%}</span><span class="mbar__n">{{ i }}</span></span>
{%- endfor -%}
{%- endcapture -%}

{% comment %}
"N일차"는 첫 학습노트 날(2026-08-27)을 1일차로 두고, 주말 + 한국 공휴일(_data/holidays.yml)을 제외한 평일만 세서 계산.
(8/26은 부트캠프 출발일이라 궤적·진행률에는 포함하지만 일차에는 세지 않는다)
반면 "총 일수"와 진행률(%)은 전체 기간의 달력 날짜 그대로(오늘까지 지난 날짜 비율)로 계산.
{% endcomment %}
{% assign start_ts = "2026-08-26" | date: "%s" | plus: 0 %}
{% assign end_ts = "2027-02-16" | date: "%s" | plus: 0 %}
{% assign today_ts = site.time | date: "%s" | plus: 0 %}
{% assign total_calendar_days = end_ts | minus: start_ts | divided_by: 86400 %}

{% assign day_start_ts = "2026-08-27" | date: "%s" | plus: 0 %}
{% assign day_number = 0 %}
{% for i in (0..total_calendar_days) %}
  {% assign offset_sec = i | times: 86400 %}
  {% assign cur_ts = day_start_ts | plus: offset_sec %}
  {% if cur_ts > today_ts %}{% break %}{% endif %}
  {% assign cur_wday = cur_ts | date: "%w" %}
  {% assign cur_date_str = cur_ts | date: "%Y-%m-%d" %}
  {% assign is_workday = true %}
  {% if cur_wday == "0" or cur_wday == "6" %}{% assign is_workday = false %}{% endif %}
  {% if site.data.holidays contains cur_date_str %}{% assign is_workday = false %}{% endif %}
  {% if is_workday %}
    {% assign day_number = day_number | plus: 1 %}
  {% endif %}
{% endfor %}

{% assign calendar_elapsed = today_ts | minus: start_ts | divided_by: 86400 | plus: 1 %}
{% assign percent = calendar_elapsed | times: 100 | divided_by: total_calendar_days %}
{% if percent > 100 %}{% assign percent = 100 %}{% endif %}
{% if percent < 0 %}{% assign percent = 0 %}{% endif %}

{% assign remaining_days = total_calendar_days | minus: calendar_elapsed %}
{% if remaining_days < 0 %}{% assign remaining_days = 0 %}{% endif %}
{% assign span_sec = end_ts | minus: start_ts %}

{%- comment -%} 이번 주 기록: 빌드 시점 기준 이번 주 월~금에 학습노트가 있으면 채운 점 {%- endcomment -%}
{%- assign today_u = site.time | date: "%u" | plus: 0 -%}
{%- assign today_str = site.time | date: "%Y-%m-%d" -%}
{%- assign back = today_u | minus: 1 | times: 86400 -%}
{%- assign week_mon = today_ts | minus: back -%}
{%- assign cloud_daily_all = site.categories.Cloud | where_exp: "p", "p.type != 'practice'" -%}
{%- assign week_hits = 0 -%}
{%- capture week_dots -%}
{%- assign labels = "월,화,수,목,금" | split: "," -%}
{%- for d in (1..5) -%}
{%- assign off = d | minus: 1 | times: 86400 -%}
{%- assign cell = week_mon | plus: off | date: "%Y-%m-%d" -%}
{%- assign hit = false -%}
{%- for p in cloud_daily_all -%}{%- assign pd = p.date | date: "%Y-%m-%d" -%}{%- if pd == cell -%}{%- assign hit = true -%}{%- endif -%}{%- endfor -%}
{%- if hit -%}{%- assign week_hits = week_hits | plus: 1 -%}{%- endif -%}
<span class="wdot{% if hit %} is-on{% endif %}{% if cell == today_str %} is-today{% endif %}{% if site.data.holidays contains cell %} is-off{% endif %}" title="{{ cell }}{% if hit %} 기록함{% elsif site.data.holidays contains cell %} 휴일{% endif %}">{{ labels[forloop.index0] }}</span>
{%- endfor -%}
{%- endcapture -%}
<h1 class="visually-hidden">김예린의 학습 노트</h1>
<div class="home-top">
<section class="term mission" aria-label="학습 진행 상황">
<div class="term__bar"><span class="term__path">yerin@learning-log: ~</span><span class="term__now">{{ site.data.now.status | default: "공부 기록 중" }}</span></div>
<div class="term__body">
<div class="term__metarow">
<p class="term__meta" data-last-login>last login: --</p>
<p class="term__visits"><span>오늘 <img id="visitor-today-badge" alt="오늘 방문자 수" /></span><span>누적 <img src="https://visitor-badge.laobi.icu/badge?page_id=paaaraaaeaaa.my-blog&left_color=161B22&right_color=238636" alt="누적 방문자 수" /></span></p>
</div>
<div class="mission__block mission__block--first">
<div class="readout">
<p class="mission__day"><span class="mission__day-value">{{ day_number }}</span><span class="mission__day-unit">일차</span></p>
<dl class="readout__kv">
<div><dt>elapsed</dt><dd>{{ percent }}%</dd></div>
<div><dt>remaining</dt><dd>D-{{ remaining_days }}</dd></div>
</dl>
</div>
<div class="pbar" role="img" aria-label="전체 {{ total_calendar_days }}일 중 {{ percent }}% 진행"><span class="pbar__fill" style="width: {{ percent }}%;"></span></div>
<p class="mission__ends"><span>2026.08.26 start</span><span>2027.02.16 end</span></p>
</div>

<div class="mission__block">
<span class="mission__key mission__key--split"><span>modules</span><b>{{ mp_done }} / 6 완료</b></span>
<a class="mbars" href="{{ '/projects/' | relative_url }}" aria-label="모듈 진척 6개 중 {{ mp_done }}개 완료, Projects로 이동">{{ mp_bars }}</a>
{%- if mp_current > 0 -%}{%- assign ck = mp_current | append: "" -%}<span class="mbars__note">지금 모듈 {{ mp_current }} · {{ site.data.modules[ck] }}</span>{%- else -%}{%- assign nk = mp_done | plus: 1 | append: "" -%}{%- if mp_done < 6 -%}<span class="mbars__note">다음 모듈 {{ nk }} · {{ site.data.modules[nk] }}</span>{%- endif -%}{%- endif -%}
</div>

<div class="mission__block mission__block--row">
<span class="mission__key">this week</span>
<div class="week-strip"><span class="week-strip__dots">{{ week_dots }}</span><span class="week-strip__count"><b>{{ week_hits }}</b>/5 days</span></div>
</div>
<div class="mission__block">
<span class="mission__key">goal</span>
<p class="goal__text">하루도 빠짐없이 기록하고, 막혔던 부분은 반드시 다시 정리하기</p>
</div>
</div>
</section>
{% include guestbook.html %}
</div>

{% assign empty_array = "" | split: "," %}
{% assign cloud_all = site.categories.Cloud | default: empty_array %}
{% assign cloud_posts = cloud_all | where_exp: "p", "p.type != 'practice'" %}
{% assign proj_posts = site.categories.Projects | default: empty_array %}
{% assign db_items = site.data.database_links | default: empty_array %}

<div class="section-head section-head--band"><h2>최근에 쓴 글</h2><span class="section-head__aside">최근 5편</span></div>

{% assign recent_posts = site.posts | slice: 0, 5 %}
{% if recent_posts.size > 0 %}
<ul class="row-list">
{% for post in recent_posts %}
{% assign cat = post.categories | first %}
{% assign cat_key = cat | downcase %}
<li><a class="row row--recent is-{{ cat_key }}" href="{{ post.url | relative_url }}">
<span class="row__date">{{ post.date | date: "%Y.%m.%d" }}</span>
<span class="row__main"><span class="row__title">{{ post.title }}</span><span class="row__sub">{{ post.excerpt | strip_html | strip_newlines | truncate: 90 }}</span></span>
<span class="row__aside"><span class="cat-label">{{ cat | default: "글" }}</span></span>
</a></li>
{% endfor %}
</ul>
{% else %}
<p class="empty-note">아직 작성된 글이 없습니다.</p>
{% endif %}

<div class="section-head"><h2>둘러보기</h2><span class="section-head__aside">ls -l ~/</span></div>

<nav class="ls" aria-label="섹션 바로가기">
<a class="ls__row is-cloud" href="{{ '/cloud/' | relative_url }}">
<span class="ls__name">cloud/</span>
<span class="ls__count"><b>{{ cloud_posts.size }}</b> notes</span>
<span class="ls__desc">모듈별 일차 학습노트</span>
<span class="ls__latest">{% if cloud_posts.size > 0 %}{{ cloud_posts.first.date | date: "%m.%d" }} {{ cloud_posts.first.title }}{% endif %}</span>
</a>
<a class="ls__row is-database" href="{{ '/database/' | relative_url }}">
<span class="ls__name">database/</span>
<span class="ls__count"><b>{{ db_items.size }}</b> items</span>
<span class="ls__desc">문제와 풀이, 게임, 아티팩트</span>
<span class="ls__latest">{% if db_items.size > 0 %}{{ db_items.last.title }}{% endif %}</span>
</a>
<a class="ls__row is-projects" href="{{ '/projects/' | relative_url }}">
<span class="ls__name">projects/</span>
<span class="ls__count"><b>{{ proj_posts.size }}</b> posts</span>
<span class="ls__desc">모듈별 결과물 6개와 연동기</span>
<span class="ls__latest">{% if proj_posts.size > 0 %}{{ proj_posts.first.date | date: "%m.%d" }} {{ proj_posts.first.project_name | default: proj_posts.first.title }}{% endif %}</span>
</a>
</nav>

<div class="section-head"><h2>GitHub 잔디</h2><span class="section-head__aside"><a href="https://github.com/paaaraaaeaaa" target="_blank" rel="noopener">github.com/paaaraaaeaaa</a></span></div>

<section class="panel gh-panel">
<div class="gh-panel__item gh-panel__streak">
<h3>streak</h3>
<img src="https://streak-stats.demolab.com?user=paaaraaaeaaa&hide_border=true&background=00000000&stroke=30363D&ring=7EE787&fire=E3B341&currStreakNum=E6EDF3&sideNums=E6EDF3&currStreakLabel=7EE787&sideLabels=A9B4BF&dates=7D8590" data-src-light="https://streak-stats.demolab.com?user=paaaraaaeaaa&hide_border=true&background=00000000&stroke=D8DEE4&ring=1A7F37&fire=9A6700&currStreakNum=1F2328&sideNums=1F2328&currStreakLabel=1A7F37&sideLabels=464E57&dates=656D76" alt="GitHub 연속 기여 통계" loading="lazy" />
</div>
<div class="gh-panel__item">
<h3>contributions</h3>
<div class="gh-panel__chart"><img src="https://ghchart.rshah.org/1A7F37/paaaraaaeaaa" alt="GitHub 기여 캘린더" loading="lazy" /></div>
</div>
</section>
