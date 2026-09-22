---
title: "Projects"
layout: single
permalink: /projects/
---

<style>
.page {
  width: 100% !important;
  max-width: 100% !important;
  padding-right: 0 !important;
  float: none !important;
}
.post-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(220px, 1fr));
  gap: 1rem;
  margin: 1.5rem 0;
}
.post-list {
  grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
}
.post-list__card {
  display: block;
  padding: 0;
  border: 1px solid rgba(0,0,0,.1);
  border-radius: 14px;
  overflow: hidden;
  text-decoration: none !important;
  color: inherit;
  background: #fff;
  transition: transform .2s cubic-bezier(0.34, 1.56, 0.64, 1), box-shadow .2s ease, border-color .2s ease;
}
.post-list__card,
.post-list__card:hover,
.post-list__card:visited,
.post-list__card * {
  text-decoration: none !important;
}
.post-list__card:hover {
  transform: translateY(-4px);
  box-shadow: 0 10px 24px rgba(0,0,0,.1);
  border-color: rgba(26,115,232,.3);
}
.post-list__banner {
  height: 88px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 2.2rem;
}
.post-list__body {
  padding: 1rem 1.2rem 1.2rem;
}
.post-list__date {
  font-size: .8rem;
  color: #888;
  margin-bottom: .4rem;
}
.post-list__title {
  font-weight: 700;
  font-size: 1.08rem;
  line-height: 1.4;
  transition: color .2s ease;
}
.post-list__card:hover .post-list__title {
  color: #1a73e8;
}
.post-list__excerpt {
  font-size: .82rem;
  color: #666;
  line-height: 1.5;
  margin-top: .45rem;
}
.post-list__tags {
  display: flex;
  flex-wrap: wrap;
  gap: .35rem;
  margin-top: .7rem;
}
.post-list__tag {
  font-size: .68rem;
  font-weight: 600;
  color: #1a73e8;
  background: rgba(26,115,232,.08);
  padding: .2rem .5rem;
  border-radius: 999px;
}
.post-list__link-row {
  display: flex;
  align-items: center;
  gap: .3rem;
  margin-top: .8rem;
  font-size: .78rem;
  font-weight: 700;
  color: #1a73e8;
}
.post-list__empty {
  color: #888;
  padding: 1rem 0;
}
</style>

{%- assign empty_array = "" | split: "," -%}
{%- assign cat_posts = site.categories.Projects | default: empty_array -%}
{%- assign cat_posts = cat_posts | sort: 'date' | reverse -%}
<div class="post-list">
{%- for post in cat_posts -%}
<a class="post-list__card" href="{{ post.url | relative_url }}">
<div class="post-list__banner" style="background: {{ post.banner_gradient | default: 'linear-gradient(135deg, #1a73e8, #6ec6ff)' }};">{{ post.banner_emoji | default: "🚀" }}</div>
<div class="post-list__body">
<div class="post-list__date">{{ post.date | date: "%Y-%m-%d" }}{% if post.module %} · 모듈 {{ post.module }}{% endif %}</div>
<div class="post-list__title">{{ post.title }}</div>
{%- if post.excerpt and post.excerpt != empty_string -%}
<div class="post-list__excerpt">{{ post.excerpt | strip_html | truncate: 70 }}</div>
{%- endif -%}
{%- if post.tags.size > 0 -%}
<div class="post-list__tags">
{%- for tag in post.tags limit: 4 -%}
<span class="post-list__tag">{{ tag }}</span>
{%- endfor -%}
</div>
{%- endif -%}
{%- if post.live_url -%}
<div class="post-list__link-row">🔗 라이브 사이트 보기</div>
{%- endif -%}
</div>
</a>
{%- endfor -%}
</div>
{%- if cat_posts.size == 0 -%}
<p class="post-list__empty">아직 작성된 글이 없습니다.</p>
{%- endif -%}
