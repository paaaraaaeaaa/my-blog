---
title: "Projects"
layout: single
permalink: /projects/
---

<link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@500;700;800&family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">

<style>
.page {
  width: 100% !important;
  max-width: 100% !important;
  padding-right: 0 !important;
  float: none !important;
}

.proj-wrap {
  font-family: 'Inter', -apple-system, BlinkMacSystemFont, 'Segoe UI', sans-serif;
}

/* ===== 페이지 헤더 ===== */
.proj-hero {
  position: relative;
  padding: 2.2rem 2rem;
  margin: .5rem 0 2.4rem;
  border-radius: 22px;
  overflow: hidden;
  background: linear-gradient(135deg, #0f1b3d 0%, #16296b 55%, #1a3fa8 100%);
  isolation: isolate;
}
.proj-hero::before,
.proj-hero::after {
  content: '';
  position: absolute;
  border-radius: 50%;
  filter: blur(46px);
  z-index: -1;
  opacity: .55;
}
.proj-hero::before {
  width: 260px;
  height: 260px;
  background: #6ec6ff;
  top: -90px;
  right: 6%;
}
.proj-hero::after {
  width: 220px;
  height: 220px;
  background: #b06ee8;
  bottom: -100px;
  left: 8%;
}
.proj-hero__eyebrow {
  display: inline-flex;
  align-items: center;
  gap: .4em;
  font-size: .72rem;
  font-weight: 700;
  letter-spacing: .12em;
  text-transform: uppercase;
  color: #bcd4ff;
  margin-bottom: .7rem;
}
.proj-hero__title {
  font-family: 'Plus Jakarta Sans', 'Inter', sans-serif;
  font-size: clamp(1.7rem, 3vw, 2.3rem);
  font-weight: 800;
  letter-spacing: -0.02em;
  color: #fff;
  margin: 0 0 .55rem;
}
.proj-hero__desc {
  font-size: .92rem;
  line-height: 1.7;
  color: #cfd9f7;
  max-width: 640px;
  margin: 0 0 1.3rem;
}
.proj-hero__stats {
  display: flex;
  flex-wrap: wrap;
  gap: .55rem;
}
.proj-hero__stat {
  display: inline-flex;
  align-items: center;
  gap: .4em;
  padding: .45rem .9rem;
  border-radius: 999px;
  background: rgba(255,255,255,.1);
  border: 1px solid rgba(255,255,255,.16);
  backdrop-filter: blur(6px);
  color: #eef2ff;
  font-size: .8rem;
  font-weight: 600;
}
.proj-hero__stat b {
  color: #fff;
  font-weight: 800;
}

/* ===== 섹션 공통 ===== */
.proj-section {
  margin: 2.6rem 0;
}
.proj-section__head {
  display: flex;
  align-items: baseline;
  justify-content: space-between;
  gap: 1rem;
  margin-bottom: 1.1rem;
  flex-wrap: wrap;
}
.proj-section__title {
  font-family: 'Plus Jakarta Sans', 'Inter', sans-serif;
  font-size: 1.15rem;
  font-weight: 800;
  letter-spacing: -0.01em;
  color: #17203a;
  display: flex;
  align-items: center;
  gap: .5em;
}
.proj-section__title .proj-dot {
  width: 8px;
  height: 8px;
  border-radius: 50%;
  background: linear-gradient(135deg, #4285f4, #6ec6ff);
  flex-shrink: 0;
}
.proj-section__sub {
  font-size: .8rem;
  color: #8b93a7;
}

/* ===== 스포트라이트 카드 (메인 프로젝트) ===== */
.proj-spotlight {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(360px, 1fr));
  gap: 1.3rem;
}
.proj-spotlight__card {
  position: relative;
  display: block;
  border-radius: 20px;
  overflow: hidden;
  text-decoration: none !important;
  color: inherit;
  border: 1px solid rgba(15,23,60,.08);
  background: #fff;
  box-shadow: 0 2px 10px rgba(15,23,60,.05);
  transition: transform .28s cubic-bezier(0.34,1.56,0.64,1), box-shadow .28s ease, border-color .28s ease;
}
.proj-spotlight__card,
.proj-spotlight__card:hover,
.proj-spotlight__card:visited,
.proj-spotlight__card * {
  text-decoration: none !important;
}
.proj-spotlight__card:hover {
  transform: translateY(-6px);
  box-shadow: 0 20px 40px rgba(26,63,168,.16);
  border-color: rgba(66,133,244,.35);
}
.proj-spotlight__banner {
  position: relative;
  height: 132px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 2.6rem;
  overflow: hidden;
}
.proj-spotlight__banner::after {
  content: '';
  position: absolute;
  inset: 0;
  background-image: radial-gradient(rgba(255,255,255,.35) 1.4px, transparent 1.4px);
  background-size: 16px 16px;
  opacity: .5;
}
.proj-spotlight__banner span {
  position: relative;
  z-index: 1;
  filter: drop-shadow(0 4px 10px rgba(0,0,0,.18));
  transition: transform .3s ease;
}
.proj-spotlight__card:hover .proj-spotlight__banner span {
  transform: scale(1.08) rotate(-3deg);
}
.proj-spotlight__badge {
  position: absolute;
  top: .7rem;
  right: .8rem;
  z-index: 2;
  font-size: .68rem;
  font-weight: 700;
  padding: .25rem .6rem;
  border-radius: 999px;
  background: rgba(255,255,255,.9);
  color: #17203a;
}
.proj-spotlight__body {
  padding: 1.2rem 1.35rem 1.35rem;
}
.proj-spotlight__meta {
  font-size: .78rem;
  color: #97a0b5;
  margin-bottom: .5rem;
  font-weight: 600;
}
.proj-spotlight__title {
  font-family: 'Plus Jakarta Sans', 'Inter', sans-serif;
  font-weight: 800;
  font-size: 1.16rem;
  line-height: 1.4;
  letter-spacing: -0.01em;
  color: #14192b;
  transition: color .2s ease;
}
.proj-spotlight__card:hover .proj-spotlight__title {
  color: #1a3fa8;
}
.proj-spotlight__excerpt {
  font-size: .85rem;
  color: #5c6478;
  line-height: 1.6;
  margin-top: .55rem;
}
.proj-spotlight__tags {
  display: flex;
  flex-wrap: wrap;
  gap: .4rem;
  margin-top: .9rem;
}
.proj-spotlight__tag {
  font-size: .68rem;
  font-weight: 700;
  padding: .22rem .58rem;
  border-radius: 999px;
  border: 1px solid;
}
.proj-spotlight__link-row {
  display: flex;
  align-items: center;
  gap: .35rem;
  margin: 0 1.35rem 1.2rem;
  padding-top: .9rem;
  border-top: 1px solid rgba(15,23,60,.07);
  font-size: .8rem;
  font-weight: 700;
  color: #1a3fa8 !important;
  text-decoration: none !important;
}
.proj-spotlight__link-row:hover {
  color: #4285f4 !important;
  gap: .55rem;
}

/* ===== 챕터 타임라인 (심화 기록 시리즈) ===== */
.proj-chapters {
  position: relative;
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(240px, 1fr));
  gap: 1.1rem;
  counter-reset: chapter;
}
.proj-chapter {
  position: relative;
  display: block;
  border-radius: 16px;
  padding: 1.15rem 1.2rem 1.3rem;
  background: #fbfcff;
  border: 1px solid rgba(15,23,60,.08);
  text-decoration: none !important;
  color: inherit;
  transition: transform .25s cubic-bezier(0.34,1.56,0.64,1), box-shadow .25s ease, border-color .25s ease, background .25s ease;
}
.proj-chapter,
.proj-chapter:hover,
.proj-chapter:visited,
.proj-chapter * {
  text-decoration: none !important;
}
.proj-chapter:hover {
  transform: translateY(-4px);
  background: #fff;
  box-shadow: 0 14px 28px rgba(15,23,60,.1);
  border-color: rgba(66,133,244,.35);
}
.proj-chapter__num {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  width: 26px;
  height: 26px;
  border-radius: 50%;
  background: linear-gradient(135deg, #4285f4, #6ec6ff);
  color: #fff;
  font-size: .74rem;
  font-weight: 800;
  margin-bottom: .7rem;
}
.proj-chapter__title {
  font-weight: 800;
  font-size: .96rem;
  line-height: 1.45;
  color: #14192b;
  letter-spacing: -0.005em;
  transition: color .2s ease;
}
.proj-chapter:hover .proj-chapter__title {
  color: #1a3fa8;
}
.proj-chapter__excerpt {
  font-size: .78rem;
  color: #6b7288;
  line-height: 1.55;
  margin-top: .5rem;
}
.proj-chapter__tags {
  display: flex;
  flex-wrap: wrap;
  gap: .3rem;
  margin-top: .8rem;
}
.proj-chapter__tag {
  font-size: .64rem;
  font-weight: 700;
  color: #4a5375;
  background: rgba(15,23,60,.05);
  padding: .18rem .5rem;
  border-radius: 999px;
}

@media (min-width: 900px) {
  .proj-chapters {
    grid-template-columns: repeat(4, 1fr);
  }
  .proj-chapters::before {
    content: '';
    position: absolute;
    top: 13px;
    left: calc(12.5% + 13px);
    right: calc(12.5% + 13px);
    height: 2px;
    background: linear-gradient(90deg, rgba(66,133,244,.35), rgba(110,198,255,.35));
    z-index: 0;
  }
}

.proj-empty {
  color: #97a0b5;
  padding: 1.2rem 0;
  font-size: .9rem;
}
</style>

<div class="proj-wrap">

{%- assign empty_array = "" | split: "," -%}
{%- assign cat_posts = site.categories.Projects | default: empty_array -%}
{%- assign cat_posts = cat_posts | sort: 'date' | reverse -%}
{%- assign main_posts = cat_posts | where_exp: "p", "p.type == nil" -%}
{%- assign series_posts = cat_posts | where_exp: "p", "p.type == 'practice'" -%}
{%- assign series_groups = series_posts | group_by: 'topic' -%}

<div class="proj-hero">
  <div class="proj-hero__eyebrow">✦ Portfolio</div>
  <h2 class="proj-hero__title">아이디어를 서비스로 만드는 과정</h2>
  <p class="proj-hero__desc">기획부터 배포, 그리고 배포 이후의 트러블슈팅까지 — 팀 프로젝트에서 실제로 맡았던 몫을 기록합니다. 잘 풀린 것만큼 막혔던 지점도 그대로 남겨둡니다.</p>
  <div class="proj-hero__stats">
    <span class="proj-hero__stat">🧭 프로젝트 <b>{{ main_posts.size }}</b>개</span>
    <span class="proj-hero__stat">📘 연동기 <b>{{ series_posts.size }}</b>편</span>
    {%- if series_groups.size > 0 -%}
    <span class="proj-hero__stat">🔧 시리즈 <b>{{ series_groups.size }}</b>개</span>
    {%- endif -%}
  </div>
</div>

<div class="proj-section">
  <div class="proj-section__head">
    <div class="proj-section__title"><span class="proj-dot"></span>완성한 프로젝트</div>
  </div>

{%- if main_posts.size > 0 -%}
<div class="proj-spotlight">
{%- for post in main_posts -%}
<a class="proj-spotlight__card" href="{{ post.url | relative_url }}">
  <div class="proj-spotlight__banner" style="background: {{ post.banner_gradient | default: 'linear-gradient(135deg, #1a73e8, #6ec6ff)' }};">
    {%- if post.module -%}<span class="proj-spotlight__badge">모듈 {{ post.module }}</span>{%- endif -%}
    <span>{{ post.banner_emoji | default: "🚀" }}</span>
  </div>
  <div class="proj-spotlight__body">
    <div class="proj-spotlight__meta">{{ post.date | date: "%Y.%m.%d" }}</div>
    <div class="proj-spotlight__title">{{ post.title }}</div>
    {%- if post.excerpt and post.excerpt != empty_string -%}
    <div class="proj-spotlight__excerpt">{{ post.excerpt | strip_html | truncate: 90 }}</div>
    {%- endif -%}
    {%- if post.tags.size > 0 -%}
    <div class="proj-spotlight__tags">
    {%- for tag in post.tags limit: 5 -%}
    {%- assign tag_colors = "#1a73e8,#8e44ec,#0aa06e,#e8710a" | split: "," -%}
    {%- assign border_colors = "rgba(26,115,232,.25),rgba(142,68,236,.25),rgba(10,160,110,.25),rgba(232,113,10,.25)" | split: "," -%}
    {%- assign ci = forloop.index0 | modulo: 4 -%}
    <span class="proj-spotlight__tag" style="color: {{ tag_colors[ci] }}; border-color: {{ border_colors[ci] }}; background: {{ tag_colors[ci] }}14;">{{ tag }}</span>
    {%- endfor -%}
    </div>
    {%- endif -%}
  </div>
</a>
{%- if post.live_url -%}
<a class="proj-spotlight__link-row" href="{{ post.live_url }}" target="_blank" rel="noopener">🔗 라이브 사이트 바로가기 →</a>
{%- endif -%}
{%- endfor -%}
</div>
{%- else -%}
<p class="proj-empty">아직 등록된 프로젝트가 없습니다.</p>
{%- endif -%}
</div>

{%- for group in series_groups -%}
{%- assign chapters = group.items | sort: 'level_order' -%}
<div class="proj-section">
  <div class="proj-section__head">
    <div class="proj-section__title"><span class="proj-dot"></span>{{ group.name }}</div>
    <div class="proj-section__sub">가장 힘들었던 연동 과정만 따로 깊게 기록한 시리즈 · {{ chapters.size }}편</div>
  </div>
  <div class="proj-chapters">
  {%- for post in chapters -%}
  <a class="proj-chapter" href="{{ post.url | relative_url }}">
    <span class="proj-chapter__num">{{ post.level_order | default: forloop.index }}</span>
    <div class="proj-chapter__title">{{ post.title }}</div>
    {%- if post.excerpt and post.excerpt != empty_string -%}
    <div class="proj-chapter__excerpt">{{ post.excerpt | strip_html | truncate: 66 }}</div>
    {%- endif -%}
    {%- if post.tags.size > 0 -%}
    <div class="proj-chapter__tags">
    {%- for tag in post.tags limit: 3 -%}
    <span class="proj-chapter__tag">{{ tag }}</span>
    {%- endfor -%}
    </div>
    {%- endif -%}
  </a>
  {%- endfor -%}
  </div>
</div>
{%- endfor -%}

</div>
