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
</style>

안녕하세요. 개발을 배우면서 그날그날 정리한 것을 여기에 쌓습니다.

{% assign latest_post = site.posts.first %}
{% assign start_ts = "2026-08-26" | date: "%s" %}
{% if latest_post %}
{% assign latest_ts = latest_post.date | date: "%s" %}
{% assign day_number = latest_ts | minus: start_ts | divided_by: 86400 | plus: 1 %}
{% endif %}

- 배운 것: Git, GitHub, 마크다운
- 지금 하는 것: 부트캠프 {{ day_number }}일차
    - 기간: 2026.08.26-2027.02.16

## {{ page.list_title }}

{% if latest_post %}
<div class="post-list">
<a class="post-list__card" href="{{ latest_post.url | relative_url }}">
<div class="post-list__date">{{ latest_post.date | date: "%Y-%m-%d" }}</div>
<div class="post-list__title">{{ latest_post.title }}</div>
</a>
</div>
{% else %}
<p class="post-list__empty">아직 작성된 글이 없습니다.</p>
{% endif %}

## GitHub 활동

<div class="gh-stats-grid">
<img src="https://github-readme-stats.vercel.app/api?username=paaaraaaeaaa&show_icons=true&theme=transparent&hide_border=true&hide_title=false" alt="GitHub Stats" loading="lazy" />
<img src="https://streak-stats.demolab.com?user=paaaraaaeaaa&theme=transparent&hide_border=true" alt="GitHub Streak Stats" loading="lazy" />
</div>

<div class="gh-chart-wrap">
<img src="https://ghchart.rshah.org/2E8B57/paaaraaaeaaa" alt="GitHub Contribution Chart" loading="lazy" />
</div>
