---
title: "Projects"
layout: single
permalink: /projects/
---

<style>
.post-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(220px, 1fr));
  gap: 1rem;
  margin: 1.5rem 0;
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
</style>

{% assign empty_array = "" | split: "," %}
<div class="post-list">
  {% assign cat_posts = site.categories.Projects | default: empty_array %}
  {% assign cat_posts = cat_posts | sort: 'date' | reverse %}
  {% for post in cat_posts %}
  <a class="post-list__card" href="{{ post.url | relative_url }}">
    <div class="post-list__date">{{ post.date | date: "%Y-%m-%d" }}</div>
    <div class="post-list__title">{{ post.title }}</div>
  </a>
  {% endfor %}
  {% if cat_posts.size == 0 %}
    <p class="post-list__empty">아직 작성된 글이 없습니다.</p>
  {% endif %}
</div>
