---
title: "Database"
layout: single
permalink: /database/
---

<link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@500;700;800&family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">

<style>
.page {
  width: 100% !important;
  max-width: 100% !important;
  padding-right: 0 !important;
  float: none !important;
}

.db-wrap {
  font-family: 'Inter', -apple-system, BlinkMacSystemFont, 'Segoe UI', sans-serif;
}

/* ===== 히어로 ===== */
.db-hero {
  position: relative;
  padding: 2.2rem 2rem;
  margin: .5rem 0 2.2rem;
  border-radius: 22px;
  overflow: hidden;
  background: linear-gradient(135deg, #241338 0%, #3a1f66 55%, #7a3fc2 100%);
  isolation: isolate;
}
.db-hero::before,
.db-hero::after {
  content: '';
  position: absolute;
  border-radius: 50%;
  filter: blur(46px);
  z-index: -1;
  opacity: .55;
}
.db-hero::before {
  width: 240px;
  height: 240px;
  background: #ff8fc4;
  top: -80px;
  right: 8%;
}
.db-hero::after {
  width: 220px;
  height: 220px;
  background: #6ec6ff;
  bottom: -100px;
  left: 6%;
}
.db-hero__eyebrow {
  display: inline-flex;
  align-items: center;
  gap: .4em;
  font-size: .72rem;
  font-weight: 700;
  letter-spacing: .12em;
  text-transform: uppercase;
  color: #e3cbff;
  margin-bottom: .7rem;
}
.db-hero__title {
  font-family: 'Plus Jakarta Sans', 'Inter', sans-serif;
  font-size: clamp(1.7rem, 3vw, 2.3rem);
  font-weight: 800;
  letter-spacing: -0.02em;
  color: #fff;
  margin: 0 0 .55rem;
}
.db-hero__desc {
  font-size: .92rem;
  line-height: 1.7;
  color: #e6d9f7;
  max-width: 620px;
  margin: 0 0 1.3rem;
}
.db-hero__stats {
  display: flex;
  flex-wrap: wrap;
  gap: .55rem;
}
.db-hero__stat {
  display: inline-flex;
  align-items: center;
  gap: .4em;
  padding: .45rem .9rem;
  border-radius: 999px;
  background: rgba(255,255,255,.1);
  border: 1px solid rgba(255,255,255,.16);
  backdrop-filter: blur(6px);
  color: #f5edff;
  font-size: .8rem;
  font-weight: 600;
}
.db-hero__stat b {
  color: #fff;
  font-weight: 800;
}

/* ===== 섹션 ===== */
.db-section {
  margin-bottom: 2.3rem;
}
.db-section__head {
  display: flex;
  align-items: center;
  gap: .7em;
  margin-bottom: 1.05rem;
  padding-bottom: .7rem;
  border-bottom: 1px solid rgba(15,23,60,.08);
}
.db-section__icon {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  width: 34px;
  height: 34px;
  border-radius: 10px;
  font-size: 1.05rem;
  flex-shrink: 0;
}
.db-section__title {
  font-family: 'Plus Jakarta Sans', 'Inter', sans-serif;
  font-size: 1.05rem;
  font-weight: 800;
  letter-spacing: -0.01em;
  color: #14192b;
}
.db-section__count {
  font-size: .74rem;
  font-weight: 600;
  color: #97a0b5;
  margin-left: .35em;
}

.db-link-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
  gap: 1.05rem;
}
.db-link-card {
  position: relative;
  display: flex;
  flex-direction: column;
  padding: 1.1rem 1.25rem 1.2rem;
  border: 1px solid rgba(15,23,60,.08);
  border-radius: 16px;
  background: #fff;
  overflow: hidden;
  transition: transform .22s cubic-bezier(0.34,1.56,0.64,1), box-shadow .22s ease, border-color .22s ease;
}
.db-link-card::before {
  content: '';
  position: absolute;
  top: 0; left: 0; right: 0;
  height: 3px;
  background: var(--accent, #7a3fc2);
  transform: scaleX(0);
  transform-origin: left;
  transition: transform .3s ease;
}
.db-link-card:hover::before {
  transform: scaleX(1);
}
.db-link-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 14px 26px rgba(15,23,60,.1);
  border-color: rgba(122,63,194,.3);
}
.db-link-title {
  font-weight: 700;
  font-size: 1rem;
  line-height: 1.45;
  color: #14192b;
  margin-bottom: .4rem;
}
.db-link-desc {
  font-size: .83rem;
  color: #6b7288;
  margin-bottom: .9rem;
  line-height: 1.55;
  flex-grow: 1;
}
.db-link-actions {
  display: flex;
  flex-wrap: wrap;
  gap: .45rem;
}
.db-link-btn {
  display: inline-flex;
  align-items: center;
  gap: .35em;
  padding: .38em .85em;
  border-radius: 999px;
  font-size: .78em;
  font-weight: 700;
  text-decoration: none !important;
  white-space: nowrap;
  transition: transform .15s ease, background .15s ease;
}
.db-link-btn:hover {
  transform: translateY(-1px);
}
.db-link-btn.url {
  background: rgba(122,63,194,.1);
  color: #6c2bb5;
}
.db-link-btn.url:hover {
  background: rgba(122,63,194,.18);
}
.db-link-btn.file {
  background: rgba(10,160,110,.1);
  color: #0a8a5f;
}
.db-link-btn.file:hover {
  background: rgba(10,160,110,.18);
}
.db-empty {
  color: #97a0b5;
  padding: 1.2rem 0;
  font-size: .9rem;
}
</style>

<div class="db-wrap">

{% assign db_links = site.data.database_links %}

<div class="db-hero">
  <div class="db-hero__eyebrow">🗂️ Resource Hub</div>
  <h2 class="db-hero__title">문제·게임·아티팩트 모음</h2>
  <p class="db-hero__desc">부트캠프에서 풀었던 문제, 감각을 익히는 게임, 그리고 정리한 산출물을 한곳에 모아뒀습니다.</p>
  <div class="db-hero__stats">
    <span class="db-hero__stat">📦 자료 <b>{{ db_links.size }}</b>개</span>
    {% if db_links and db_links.size > 0 %}
    {% assign db_cat_count = db_links | map: 'category' | uniq | size %}
    <span class="db-hero__stat">📁 카테고리 <b>{{ db_cat_count }}</b>개</span>
    {% endif %}
  </div>
</div>

{% if db_links and db_links.size > 0 %}
{% assign db_groups = db_links | group_by: 'category' %}
{% for group in db_groups %}
{% case group.name %}
  {% when "문제" %}{% assign icon = "📝" %}{% assign accent = "#185fa8" %}{% assign icon_bg = "rgba(24,95,168,.1)" %}
  {% when "게임" %}{% assign icon = "🎮" %}{% assign accent = "#8e44ec" %}{% assign icon_bg = "rgba(142,68,236,.1)" %}
  {% when "아티팩트" %}{% assign icon = "🗂️" %}{% assign accent = "#0aa06e" %}{% assign icon_bg = "rgba(10,160,110,.1)" %}
  {% else %}{% assign icon = "📁" %}{% assign accent = "#e8710a" %}{% assign icon_bg = "rgba(232,113,10,.1)" %}
{% endcase %}
<div class="db-section">
<div class="db-section__head">
<span class="db-section__icon" style="background: {{ icon_bg }};">{{ icon }}</span>
<span class="db-section__title">{{ group.name }}<span class="db-section__count">{{ group.items.size }}개</span></span>
</div>
<div class="db-link-grid">
{% for item in group.items %}
<div class="db-link-card" style="--accent: {{ accent }};">
<div class="db-link-title">{{ item.title }}</div>
{% if item.description %}<div class="db-link-desc">{{ item.description }}</div>{% endif %}
<div class="db-link-actions">
{% if item.url %}<a class="db-link-btn url" href="{{ item.url }}" target="_blank" rel="noopener noreferrer">🔗 바로가기</a>{% endif %}
{% if item.file %}<a class="db-link-btn file" href="{{ item.file | relative_url }}" target="_blank" rel="noopener noreferrer">📎 첨부파일</a>{% endif %}
</div>
</div>
{% endfor %}
</div>
</div>
{% endfor %}
{% else %}
<p class="db-empty">아직 등록된 자료가 없습니다.</p>
{% endif %}

</div>
