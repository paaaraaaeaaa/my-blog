---
title: "Cloud"
layout: single
permalink: /cloud/
---

<link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@500;700;800&family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">

<style>
/* 이 페이지는 오른쪽 목차가 없으니, 목차용으로 비워두던 여백까지 본문 폭으로 다 쓰게 함 */
.page {
  width: 100% !important;
  max-width: 100% !important;
  padding-right: 0 !important;
  float: none !important;
}

.cd-wrap {
  font-family: 'Inter', -apple-system, BlinkMacSystemFont, 'Segoe UI', sans-serif;
}

/* ===== 히어로 ===== */
.cd-hero {
  position: relative;
  padding: 2.2rem 2rem;
  margin: .5rem 0 2rem;
  border-radius: 22px;
  overflow: hidden;
  background: linear-gradient(135deg, #10223d 0%, #123468 55%, #185fa8 100%);
  isolation: isolate;
}
.cd-hero::before,
.cd-hero::after {
  content: '';
  position: absolute;
  border-radius: 50%;
  filter: blur(46px);
  z-index: -1;
  opacity: .55;
}
.cd-hero::before {
  width: 240px;
  height: 240px;
  background: #34d1c9;
  top: -80px;
  right: 10%;
}
.cd-hero::after {
  width: 220px;
  height: 220px;
  background: #6ec6ff;
  bottom: -100px;
  left: 6%;
}
.cd-hero__eyebrow {
  display: inline-flex;
  align-items: center;
  gap: .4em;
  font-size: .72rem;
  font-weight: 700;
  letter-spacing: .12em;
  text-transform: uppercase;
  color: #a9e6e0;
  margin-bottom: .7rem;
}
.cd-hero__title {
  font-family: 'Plus Jakarta Sans', 'Inter', sans-serif;
  font-size: clamp(1.7rem, 3vw, 2.3rem);
  font-weight: 800;
  letter-spacing: -0.02em;
  color: #fff;
  margin: 0 0 .55rem;
}
.cd-hero__desc {
  font-size: .92rem;
  line-height: 1.7;
  color: #cfe4f7;
  max-width: 620px;
  margin: 0 0 1.3rem;
}
.cd-hero__stats {
  display: flex;
  flex-wrap: wrap;
  gap: .55rem;
}
.cd-hero__stat {
  display: inline-flex;
  align-items: center;
  gap: .4em;
  padding: .45rem .9rem;
  border-radius: 999px;
  background: rgba(255,255,255,.1);
  border: 1px solid rgba(255,255,255,.16);
  backdrop-filter: blur(6px);
  color: #eef7ff;
  font-size: .8rem;
  font-weight: 600;
}
.cd-hero__stat b {
  color: #fff;
  font-weight: 800;
}

/* ===== 탭 (세그먼트 컨트롤) ===== */
.cloud-tabs {
  display: inline-flex;
  gap: .25rem;
  padding: .3rem;
  margin-bottom: 2rem;
  border-radius: 999px;
  background: rgba(15,23,60,.05);
}
.cloud-tab-btn {
  appearance: none;
  border: none;
  cursor: pointer;
  display: inline-flex;
  align-items: center;
  gap: .5em;
  padding: .6rem 1.15rem;
  font-size: .88rem;
  font-weight: 700;
  font-family: 'Inter', inherit;
  color: #6b7288;
  background: transparent;
  border-radius: 999px;
  transition: color .18s ease, background .18s ease, box-shadow .18s ease;
}
.cloud-tab-btn:hover {
  color: #17203a;
}
.cloud-tab-btn.is-active {
  color: #123468;
  background: #fff;
  box-shadow: 0 4px 14px rgba(15,23,60,.14);
}
.cloud-tab-count {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  min-width: 1.5em;
  height: 1.5em;
  padding: 0 .4em;
  border-radius: 999px;
  background: rgba(15,23,60,.06);
  color: #6b7288;
  font-size: .7rem;
  font-weight: 700;
}
.cloud-tab-btn.is-active .cloud-tab-count {
  background: rgba(24,95,168,.12);
  color: #185fa8;
}
.cloud-tab-panel[hidden] {
  display: none !important;
}

/* ===== 모듈/주제 섹션 ===== */
.module-section {
  margin: 0 0 2.4rem;
}
.module-section__head {
  display: flex;
  align-items: center;
  gap: .7em;
  margin-bottom: 1.05rem;
  padding-bottom: .7rem;
  border-bottom: 1px solid rgba(15,23,60,.08);
}
.module-badge {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  min-width: 32px;
  height: 32px;
  padding: 0 .5em;
  border-radius: 10px;
  color: #fff;
  font-size: .8rem;
  font-weight: 800;
  flex-shrink: 0;
}
.module-section__title {
  font-family: 'Plus Jakarta Sans', 'Inter', sans-serif;
  font-size: 1.05rem;
  font-weight: 800;
  letter-spacing: -0.01em;
  color: #14192b;
}
.module-section__title .module-num {
  font-size: .74rem;
  font-weight: 600;
  color: #97a0b5;
  margin-left: .3em;
}

.post-list {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 1rem;
}
@media (max-width: 1100px) {
  .post-list { grid-template-columns: repeat(3, 1fr); }
}
@media (max-width: 800px) {
  .post-list { grid-template-columns: repeat(2, 1fr); }
}
@media (max-width: 500px) {
  .post-list { grid-template-columns: 1fr; }
}
.post-list__card {
  position: relative;
  display: block;
  padding: 1.05rem 1.2rem;
  border: 1px solid rgba(15,23,60,.08);
  border-radius: 14px;
  text-decoration: none !important;
  color: inherit;
  background: #fff;
  overflow: hidden;
  transition: transform .22s cubic-bezier(0.34,1.56,0.64,1), box-shadow .22s ease, border-color .22s ease;
}
.post-list__card,
.post-list__card:hover,
.post-list__card:visited,
.post-list__card:active,
.post-list__card * {
  text-decoration: none !important;
}
.post-list__card::before {
  content: '';
  position: absolute;
  top: 0; left: 0; right: 0;
  height: 3px;
  background: var(--accent, linear-gradient(90deg, #185fa8, #34d1c9));
  transform: scaleX(0);
  transform-origin: left;
  transition: transform .3s ease;
}
.post-list__card:hover::before {
  transform: scaleX(1);
}
.post-list__card:hover {
  transform: translateY(-4px);
  box-shadow: 0 14px 26px rgba(15,23,60,.1);
  border-color: rgba(24,95,168,.25);
}
.post-list__card:hover .post-list__title {
  color: #185fa8;
}
.post-list__date {
  font-size: .72rem;
  color: #97a0b5;
  font-weight: 600;
  margin-bottom: .4rem;
}
.post-list__title {
  font-weight: 700;
  font-size: 0.92rem;
  line-height: 1.45;
  color: #14192b;
  transition: color 0.2s ease;
}
.post-list__subtitle {
  font-size: .76rem;
  color: #6b7288;
  margin-top: .35rem;
  line-height: 1.5;
}
.post-list__empty {
  color: #97a0b5;
  padding: 1.2rem 0;
  font-size: .9rem;
}
</style>

<div class="cd-wrap">

{% assign empty_array = "" | split: "," %}
{% assign cat_posts = site.categories.Cloud | default: empty_array %}
{% assign daily_posts = cat_posts | where_exp: "post", "post.type != 'practice'" %}
{% assign practice_posts = cat_posts | where_exp: "post", "post.type == 'practice'" %}

<div class="cd-hero">
  <div class="cd-hero__eyebrow">☁️ Bootcamp Log</div>
  <h2 class="cd-hero__title">매일 배운 것을 코드로 남기기</h2>
  <p class="cd-hero__desc">부트캠프에서 하루하루 부딪힌 문제와 풀어낸 방법을 그대로 기록합니다. 일차별 학습노트와 자율 실습 devlog로 나눠 정리했습니다.</p>
  <div class="cd-hero__stats">
    <span class="cd-hero__stat">📅 학습노트 <b>{{ daily_posts.size }}</b>편</span>
    <span class="cd-hero__stat">🧩 문제풀이 <b>{{ practice_posts.size }}</b>편</span>
  </div>
</div>

{% if cat_posts.size == 0 %}
<p class="post-list__empty">아직 작성된 글이 없습니다.</p>
{% else %}

{% assign accent_colors = "linear-gradient(90deg,#185fa8,#6ec6ff),linear-gradient(90deg,#8e44ec,#c58af0),linear-gradient(90deg,#0aa06e,#5be0ae),linear-gradient(90deg,#e8710a,#ffb066),linear-gradient(90deg,#d1348a,#ff8fc4),linear-gradient(90deg,#34495e,#7f96ad)" | split: "," %}
{% assign badge_colors = "#185fa8,#8e44ec,#0aa06e,#e8710a,#d1348a,#34495e" | split: "," %}

<div class="cloud-tabs" role="tablist">
<button type="button" class="cloud-tab-btn is-active" data-tab="daily" role="tab" aria-selected="true">📅 일차별 학습노트 <span class="cloud-tab-count">{{ daily_posts.size }}</span></button>
<button type="button" class="cloud-tab-btn" data-tab="practice" role="tab" aria-selected="false">🧩 문제풀이 <span class="cloud-tab-count">{{ practice_posts.size }}</span></button>
</div>

<div class="cloud-tab-panel" id="cloud-panel-daily" data-panel="daily">
{% if daily_posts.size == 0 %}
<p class="post-list__empty">아직 작성된 글이 없습니다.</p>
{% else %}
{% assign modules = daily_posts | group_by: 'module' | sort: 'name' %}
{% for mod in modules %}
{% assign ci = forloop.index0 | modulo: 6 %}
<div class="module-section">
{% assign mod_name = site.data.modules[mod.name] %}
<div class="module-section__head">
<span class="module-badge" style="background: {{ badge_colors[ci] }};">{{ mod.name }}</span>
<span class="module-section__title">{% if mod_name %}{{ mod_name }}{% else %}모듈 {{ mod.name }}{% endif %}<span class="module-num">모듈 {{ mod.name }} · {{ mod.items.size }}편</span></span>
</div>
<div class="post-list">
{% assign mod_posts = mod.items | sort: 'date' %}
{% for post in mod_posts %}
<a class="post-list__card" style="--accent: {{ accent_colors[ci] }};" href="{{ post.url | relative_url }}">
<div class="post-list__date">{{ post.date | date: "%Y-%m-%d" }}</div>
<div class="post-list__title">{{ post.title }}</div>
</a>
{% endfor %}
</div>
</div>
{% endfor %}
{% endif %}
</div>

<div class="cloud-tab-panel" id="cloud-panel-practice" data-panel="practice" hidden>
{% if practice_posts.size == 0 %}
<p class="post-list__empty">아직 작성된 글이 없습니다.</p>
{% else %}
{% assign practice_posts_by_date = practice_posts | sort: 'date' %}
{% assign topic_names_ordered = practice_posts_by_date | map: 'topic' | uniq %}
{% for name in topic_names_ordered %}
{% assign topic_posts = practice_posts | where_exp: "post", "post.topic == name" | sort: 'date' | sort: 'level_order' %}
{% if topic_posts.size > 0 %}
{% assign ci = forloop.index0 | modulo: 6 %}
<div class="module-section">
<div class="module-section__head">
<span class="module-badge" style="background: {{ badge_colors[ci] }};">{{ forloop.index }}</span>
<span class="module-section__title">{% if name %}{{ name }}{% else %}미분류{% endif %}<span class="module-num">{{ topic_posts.size }}편</span></span>
</div>
<div class="post-list">
{% for post in topic_posts %}
<a class="post-list__card" style="--accent: {{ accent_colors[ci] }};" href="{{ post.url | relative_url }}">
<div class="post-list__date">{{ post.date | date: "%Y-%m-%d" }}</div>
<div class="post-list__title">{{ post.title }}</div>
<div class="post-list__subtitle">{{ post.subtitle }}</div>
</a>
{% endfor %}
</div>
</div>
{% endif %}
{% endfor %}
{% endif %}
</div>

<script>
(function () {
  var buttons = document.querySelectorAll('.cloud-tab-btn');
  var panels = document.querySelectorAll('.cloud-tab-panel');
  buttons.forEach(function (btn) {
    btn.addEventListener('click', function () {
      var target = btn.getAttribute('data-tab');
      buttons.forEach(function (b) {
        var active = b === btn;
        b.classList.toggle('is-active', active);
        b.setAttribute('aria-selected', active ? 'true' : 'false');
      });
      panels.forEach(function (p) {
        p.hidden = p.getAttribute('data-panel') !== target;
      });
    });
  });
})();
</script>

{% endif %}

</div>
