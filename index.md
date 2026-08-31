---
layout: single
title: 학습 노트
list_title: 최근 글
author_profile: true
---

<style>
.post-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(240px, 1fr));
  gap: 1.1rem;
  margin: 1rem 0 1.5rem;
}
.post-list__card {
  display: flex;
  flex-direction: column;
  padding: 1.2rem 1.3rem 1.1rem;
  border: 1px solid rgba(0,0,0,.08);
  border-radius: 14px;
  text-decoration: none;
  color: inherit;
  background: #fff;
  box-shadow: 0 2px 8px rgba(0,0,0,.04);
  transition: transform .15s ease, box-shadow .15s ease, border-color .15s ease;
  position: relative;
  overflow: hidden;
}
.post-list__card::before {
  content: '';
  position: absolute;
  top: 0; left: 0;
  width: 100%;
  height: 4px;
  background: linear-gradient(90deg, #4285f4, #34a853);
}
.post-list__card--cloud::before { background: #4285f4; }
.post-list__card--database::before { background: #a142f4; }
.post-list__card--projects::before { background: #ff7043; }
.post-list__card:hover {
  transform: translateY(-4px);
  box-shadow: 0 10px 22px rgba(0,0,0,.1);
  border-color: rgba(0,0,0,.16);
}
.post-list__top {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: .5rem;
  margin-bottom: .7rem;
}
.post-list__badge {
  display: inline-flex;
  align-items: center;
  gap: .35em;
  font-size: .72rem;
  font-weight: 700;
  padding: .25em .7em;
  border-radius: 999px;
  color: #fff;
  white-space: nowrap;
}
.post-list__badge--cloud { background: #4285f4; }
.post-list__badge--database { background: #a142f4; }
.post-list__badge--projects { background: #ff7043; }
.post-list__badge--default { background: #888; }
.post-list__date {
  font-size: .76rem;
  color: #999;
  white-space: nowrap;
}
.post-list__title {
  font-weight: 700;
  font-size: 1.08rem;
  line-height: 1.4;
  margin-bottom: .5rem;
}
.post-list__excerpt {
  font-size: .85rem;
  color: #777;
  line-height: 1.6;
  margin-bottom: .8rem;
  flex-grow: 1;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
.post-list__more {
  font-size: .8rem;
  font-weight: 600;
  color: #4285f4;
  margin-top: auto;
}
.post-list__empty {
  color: #888;
  padding: 1rem 0;
}
.gh-stats-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
  gap: 1rem;
  margin: 1rem 0 1.5rem;
}
.gh-stats-grid img {
  width: 100%;
  border-radius: 10px;
}
.gh-chart-wrap {
  overflow-x: auto;
  margin: 1rem 0 1.5rem;
}
.gh-chart-wrap img {
  max-width: 100%;
}
.visitor-row {
  margin-bottom: .8em;
}
.visitor-row:last-of-type {
  margin-bottom: 0;
}
.visitor-label {
  display: block;
  font-size: .78em;
  color: #888;
  margin-bottom: .3em;
}
.progress-wrap {
  margin: 1rem 0 1.5rem;
}
.progress-label {
  font-size: .88rem;
  color: #555;
  margin-bottom: .4rem;
}
.progress-bar {
  width: 100%;
  height: 10px;
  border-radius: 999px;
  background: rgba(0,0,0,.08);
  overflow: hidden;
}
.progress-bar__fill {
  height: 100%;
  border-radius: 999px;
  background: linear-gradient(90deg, #4285f4, #34a853);
}
.quicknav-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(140px, 1fr));
  gap: 1rem;
  margin: 1rem 0 1.5rem;
}
.quicknav-card {
  display: block;
  text-align: center;
  padding: 1.1rem .8rem;
  border: 1px solid rgba(0,0,0,.1);
  border-radius: 12px;
  text-decoration: none;
  color: inherit;
  font-weight: 600;
  font-size: 1rem;
  transition: transform .15s ease, box-shadow .15s ease, border-color .15s ease;
}
.quicknav-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 6px 16px rgba(0,0,0,.08);
  border-color: rgba(66,133,244,.4);
}
.quicknav-card__icon {
  display: block;
  font-size: 1.6rem;
  margin-bottom: .3rem;
}
.visitor-aside {
  min-width: 245px;
}
.visitor-box {
  padding: 1.35em !important;
}
.visitor-box .visitor-label {
  font-size: .85em;
}
.visitor-box img {
  transform: scale(1.06);
  transform-origin: left center;
}
.visitor-box .copy-link-btn {
  padding: .75em;
  font-size: .92em;
}
</style>

<aside class="sidebar__right sticky visitor-aside">
<nav class="toc">
<header><h4 class="nav__title"><i class="fas fa-chart-line"></i> 방문자 정보</h4></header>
<div class="toc__menu visitor-box">
<div class="visitor-row">
<span class="visitor-label">오늘 방문자</span>
<img id="visitor-today-badge" alt="오늘 방문자 수" />
</div>
<div class="visitor-row">
<span class="visitor-label">누적 방문자</span>
<img src="https://visitor-badge.laobi.icu/badge?page_id=paaaraaaeaaa.my-blog&left_color=555555&right_color=79C83D" alt="누적 방문자 수" />
</div>
<button type="button" id="copy-link-btn" class="copy-link-btn">🔗 이 페이지 링크 복사</button>
</div>
</nav>
</aside>

안녕하세요! 개발을 처음 배우는 부트캠프 학습자입니다. 매일 배운 내용과 겪은 시행착오를 여기에 기록하면서, 몇 달 뒤에 다시 읽었을 때 "그때보다 늘었다"를 확인할 수 있는 블로그로 만들어가고 있습니다.

{% comment %}
부트캠프 "N일차"를 주말 + 한국 공휴일(_data/holidays.yml)을 제외한 평일 기준으로 계산.
전체 기간(2026-08-26~2027-02-16)을 하루씩 훑으면서, 평일이면서 공휴일이 아닌 날만 센다.
{% endcomment %}
{% assign start_ts = "2026-08-26" | date: "%s" %}
{% assign end_ts = "2027-02-16" | date: "%s" %}
{% assign today_ts = site.time | date: "%s" %}
{% assign total_calendar_days = end_ts | minus: start_ts | divided_by: 86400 %}

{% assign day_number = 0 %}
{% assign total_days = 0 %}
{% for i in (0..total_calendar_days) %}
  {% assign offset_sec = i | times: 86400 %}
  {% assign cur_ts = start_ts | plus: offset_sec %}
  {% assign cur_wday = cur_ts | date: "%w" %}
  {% assign cur_date_str = cur_ts | date: "%Y-%m-%d" %}
  {% assign is_workday = true %}
  {% if cur_wday == "0" or cur_wday == "6" %}{% assign is_workday = false %}{% endif %}
  {% if site.data.holidays contains cur_date_str %}{% assign is_workday = false %}{% endif %}
  {% if is_workday %}
    {% assign total_days = total_days | plus: 1 %}
    {% if cur_ts <= today_ts %}
      {% assign day_number = day_number | plus: 1 %}
    {% endif %}
  {% endif %}
{% endfor %}

{% assign percent = day_number | times: 100 | divided_by: total_days %}
{% if percent > 100 %}{% assign percent = 100 %}{% endif %}
{% if percent < 0 %}{% assign percent = 0 %}{% endif %}

- 배운 것: Git, GitHub, 마크다운
- 지금 하는 것: 부트캠프 {{ day_number }}일차 (주말·공휴일 제외 평일 기준)
    - 기간: 2026.08.26-2027.02.16
- 목표: 하루도 빠짐없이 기록하고, 막혔던 부분은 반드시 다시 정리하기

<div class="progress-wrap">
<div class="progress-label">전체 진행률: {{ day_number }}일차 / 총 {{ total_days }}일(평일 기준) ({{ percent }}%)</div>
<div class="progress-bar"><div class="progress-bar__fill" style="width: {{ percent }}%;"></div></div>
</div>

<div class="quicknav-grid">
<a class="quicknav-card" href="{{ '/cloud/' | relative_url }}"><span class="quicknav-card__icon">☁️</span>Cloud</a>
<a class="quicknav-card" href="{{ '/database/' | relative_url }}"><span class="quicknav-card__icon">🗄️</span>Database</a>
<a class="quicknav-card" href="{{ '/projects/' | relative_url }}"><span class="quicknav-card__icon">🚀</span>Projects</a>
</div>

## 📚 {{ page.list_title }}

{% assign recent_posts = site.posts | slice: 0, 4 %}
{% if recent_posts.size > 0 %}
<div class="post-list">
{% for post in recent_posts %}
{% assign cat = post.categories | first %}
{% if cat == "Cloud" %}{% assign card_mod = "post-list__card--cloud" %}
{% elsif cat == "Database" %}{% assign card_mod = "post-list__card--database" %}
{% elsif cat == "Projects" %}{% assign card_mod = "post-list__card--projects" %}
{% else %}{% assign card_mod = "" %}
{% endif %}
<a class="post-list__card {{ card_mod }}" href="{{ post.url | relative_url }}">
<div class="post-list__top">
{% if cat == "Cloud" %}
<span class="post-list__badge post-list__badge--cloud">☁️ Cloud</span>
{% elsif cat == "Database" %}
<span class="post-list__badge post-list__badge--database">🗄️ Database</span>
{% elsif cat == "Projects" %}
<span class="post-list__badge post-list__badge--projects">🚀 Projects</span>
{% else %}
<span class="post-list__badge post-list__badge--default">{{ cat | default: "글" }}</span>
{% endif %}
<span class="post-list__date">{{ post.date | date: "%Y.%m.%d" }}</span>
</div>
<div class="post-list__title">{{ post.title }}</div>
<div class="post-list__excerpt">{{ post.excerpt | strip_html | truncate: 70 }}</div>
<div class="post-list__more">자세히 보기 →</div>
</a>
{% endfor %}
</div>
{% else %}
<p class="post-list__empty">아직 작성된 글이 없습니다.</p>
{% endif %}

## GitHub 활동

<div class="gh-stats-grid">
<img src="https://streak-stats.demolab.com?user=paaaraaaeaaa&theme=transparent&hide_border=true" alt="GitHub Streak Stats" loading="lazy" />
</div>

<div class="gh-chart-wrap">
<img src="https://ghchart.rshah.org/2E8B57/paaaraaaeaaa" alt="GitHub Contribution Chart" loading="lazy" />
</div>

> 꾸준함이 실력이 된다고 믿습니다. 오늘도 한 줄 더 기록합니다. 🚀
