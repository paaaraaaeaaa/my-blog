---
title: "Cloud"
layout: single
permalink: /cloud/
---

<style>
/* 이 페이지는 오른쪽 목차가 없으니, 목차용으로 비워두던 여백까지 본문 폭으로 다 쓰게 함 */
.page {
  width: 100% !important;
  max-width: 100% !important;
  padding-right: 0 !important;
  float: none !important;
}
.cloud-top-grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 1.2rem;
  align-items: start;
  margin-bottom: 1.5rem;
  width: 100%;
}
@media (max-width: 700px) {
  .cloud-top-grid {
    grid-template-columns: 1fr;
  }
}
.cloud-top-section {
  margin-bottom: 0;
  border: 1px solid rgba(0,0,0,.1);
  border-radius: 14px;
  overflow: hidden;
}
.cloud-top-section > summary {
  cursor: pointer;
  padding: .9rem 1.3rem;
  font-size: 1.1rem;
  font-weight: 700;
  background: rgba(0,0,0,.03);
  list-style: none;
  user-select: none;
}
.cloud-top-section > summary::-webkit-details-marker {
  display: none;
}
.cloud-top-section > summary::before {
  content: '▶ ';
  color: #888;
}
.cloud-top-section[open] > summary::before {
  content: '▼ ';
}
.cloud-top-section > summary:hover {
  background: rgba(0,0,0,.06);
}
.cloud-top-body {
  padding: .2rem 1.3rem 1rem;
}
.module-section {
  margin: 1.5rem 0;
  padding-left: .9rem;
  border-left: 3px solid rgba(66,133,244,.25);
}
.module-section h3 {
  margin-top: 0;
  border-bottom: 2px solid rgba(0,0,0,.1);
  padding-bottom: .4rem;
}
.module-num {
  font-size: .7em;
  font-weight: 500;
  color: #888;
}
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
.post-list__subtitle {
  font-size: .85rem;
  color: #666;
  margin-top: .3rem;
  line-height: 1.4;
}
.post-list__empty {
  color: #888;
  padding: 1rem 0;
}
</style>

{% assign empty_array = "" | split: "," %}
{% assign cat_posts = site.categories.Cloud | default: empty_array %}

{% if cat_posts.size == 0 %}
<p class="post-list__empty">아직 작성된 글이 없습니다.</p>
{% else %}

{% assign daily_posts = cat_posts | where_exp: "post", "post.type != 'practice'" %}
{% assign practice_posts = cat_posts | where_exp: "post", "post.type == 'practice'" %}

<div class="cloud-top-grid">
<details class="cloud-top-section" open>
<summary>📅 일차별 학습노트</summary>
<div class="cloud-top-body">
{% if daily_posts.size == 0 %}
<p class="post-list__empty">아직 작성된 글이 없습니다.</p>
{% else %}
{% assign modules = daily_posts | group_by: 'module' | sort: 'name' %}
{% for mod in modules %}
<div class="module-section">
{% assign mod_name = site.data.modules[mod.name] %}
<h3>
{% if mod_name %}📦 {{ mod_name }} <span class="module-num">(모듈{{ mod.name }})</span>{% else %}📦 모듈 {{ mod.name }}{% endif %}
</h3>
<div class="post-list">
{% assign mod_posts = mod.items | sort: 'date' %}
{% for post in mod_posts %}
<a class="post-list__card" href="{{ post.url | relative_url }}">
<div class="post-list__date">{{ post.date | date: "%Y-%m-%d" }}</div>
<div class="post-list__title">{{ post.title }}</div>
</a>
{% endfor %}
</div>
</div>
{% endfor %}
{% endif %}
</div>
</details>

<details class="cloud-top-section" open>
<summary>🧩 문제풀이</summary>
<div class="cloud-top-body">
{% if practice_posts.size == 0 %}
<p class="post-list__empty">아직 작성된 글이 없습니다.</p>
{% else %}
{% assign practice_topics = practice_posts | group_by: 'topic' | sort: 'name' %}
{% for grp in practice_topics %}
<div class="module-section">
<h3>🎯 {% if grp.name %}{{ grp.name }}{% else %}미분류{% endif %}</h3>
<div class="post-list">
{% assign topic_posts = grp.items | sort: 'date' %}
{% for post in topic_posts %}
<a class="post-list__card" href="{{ post.url | relative_url }}">
<div class="post-list__date">{{ post.date | date: "%Y-%m-%d" }}</div>
<div class="post-list__title">{{ post.title }}</div>
<div class="post-list__subtitle">{{ post.subtitle }}</div>
</a>
{% endfor %}
</div>
</div>
{% endfor %}
{% endif %}
</div>
</details>
</div>

{% endif %}
