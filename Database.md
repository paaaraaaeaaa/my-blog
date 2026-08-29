---
title: "Database"
layout: single
permalink: /database/
---

<style>
.db-section {
  margin-bottom: 2rem;
}
.db-section h3 {
  margin-bottom: 0.8rem;
  padding-bottom: 0.4rem;
  border-bottom: 2px solid rgba(0,0,0,.08);
}
.db-link-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(240px, 1fr));
  gap: 1rem;
}
.db-link-card {
  display: block;
  padding: 1rem 1.2rem;
  border: 1px solid rgba(0,0,0,.1);
  border-radius: 12px;
  text-decoration: none;
  color: inherit;
  background: #fff;
  transition: transform .15s ease, box-shadow .15s ease, border-color .15s ease;
}
.db-link-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 6px 16px rgba(0,0,0,.08);
  border-color: rgba(66,133,244,.4);
}
.db-link-title {
  font-weight: 600;
  font-size: 1.02rem;
  line-height: 1.4;
  margin-bottom: .35rem;
}
.db-link-desc {
  font-size: .85rem;
  color: #777;
  margin-bottom: .6rem;
  line-height: 1.5;
}
.db-link-arrow {
  font-size: .8rem;
  color: #4285f4;
  font-weight: 500;
}
.db-empty {
  color: #888;
  padding: 1rem 0;
}
</style>

{% assign db_links = site.data.database_links %}
{% if db_links and db_links.size > 0 %}
{% assign db_groups = db_links | group_by: 'category' %}
{% for group in db_groups %}
<div class="db-section">
<h3>📁 {{ group.name }}</h3>
<div class="db-link-grid">
{% for item in group.items %}
<a class="db-link-card" href="{{ item.url }}" target="_blank" rel="noopener noreferrer">
<div class="db-link-title">{{ item.title }}</div>
{% if item.description %}<div class="db-link-desc">{{ item.description }}</div>{% endif %}
<div class="db-link-arrow">바로가기 →</div>
</a>
{% endfor %}
</div>
</div>
{% endfor %}
{% else %}
<p class="db-empty">아직 등록된 자료가 없습니다.</p>
{% endif %}
