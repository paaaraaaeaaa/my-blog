---
layout: single
title: 학습 노트
list_title: 최근 글
author_profile: true
---

<style>
.post-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(220px, 1fr));
  gap: 1rem;
  margin: 1rem 0 1.5rem;
}
.post-list__card {
  display: block;
  padding: 1rem 1.2rem;
  border: 1px solid rgba(0,0,0,.1);
  border-radius: 12px;
  text-decoration: none;
  color: inherit;
  transition: transform .15s ease, box-shadow .15s ease, border-color .15s ease;
}
.post-list__card:hover {
  transform: translateY(-3px);
  box-shadow: 0 6px 16px rgba(0,0,0,.08);
  border-color: rgba(0,0,0,.2);
}
.post-list__date {
  font-size: .8rem;
  color: #888;
  margin-bottom: .4rem;
}
.post-list__title {
  font-weight: 600;
  font-size: 1.05rem;
  line-height: 1.4;
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

{% assign start_ts = "2026-08-26" | date: "%s" %}
{% assign end_ts = "2027-02-16" | date: "%s" %}
{% assign today_ts = site.time | date: "%s" %}
{% assign day_number = today_ts | minus: start_ts | divided_by: 86400 | plus: 1 %}
{% assign total_days = end_ts | minus: start_ts | divided_by: 86400 %}
{% assign percent = day_number | times: 100 | divided_by: total_days %}
{% if percent > 100 %}{% assign percent = 100 %}{% endif %}
{% if percent < 0 %}{% assign percent = 0 %}{% endif %}

- 배운 것: Git, GitHub, 마크다운
- 지금 하는 것: 부트캠프 {{ day_number }}일차
    - 기간: 2026.08.26-2027.02.16
- 목표: 하루도 빠짐없이 기록하고, 막혔던 부분은 반드시 다시 정리하기

<div class="progress-wrap">
<div class="progress-label">전체 진행률: {{ day_number }}일차 / {{ total_days }}일 ({{ percent }}%)</div>
<div class="progress-bar"><div class="progress-bar__fill" style="width: {{ percent }}%;"></div></div>
</div>

<div class="quicknav-grid">
<a class="quicknav-card" href="{{ '/cloud/' | relative_url }}"><span class="quicknav-card__icon">☁️</span>Cloud</a>
<a class="quicknav-card" href="{{ '/database/' | relative_url }}"><span class="quicknav-card__icon">🗄️</span>Database</a>
<a class="quicknav-card" href="{{ '/projects/' | relative_url }}"><span class="quicknav-card__icon">🚀</span>Projects</a>
</div>

## {{ page.list_title }}

{% assign recent_posts = site.posts | slice: 0, 2 %}
{% if recent_posts.size > 0 %}
<div class="post-list">
{% for post in recent_posts %}
<a class="post-list__card" href="{{ post.url | relative_url }}">
<div class="post-list__date">{{ post.date | date: "%Y-%m-%d" }}</div>
<div class="post-list__title">{{ post.title }}</div>
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

> 꾸준함이 실력이 된다고 믿습니다.  한 줄 더 기록합니다.
