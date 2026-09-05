---
layout: single
title: 학습 노트
list_title: 최근 글
author_profile: true
---

<style>
/* ===== 전체 리셋 및 기본 스타일 ===== */
* {
  box-sizing: border-box;
}

/* ===== 애니메이션 정의 ===== */
@keyframes fadeInUp {
  from {
    opacity: 0;
    transform: translateY(30px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

@keyframes fadeInDown {
  from {
    opacity: 0;
    transform: translateY(-20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

@keyframes slideInLeft {
  from {
    opacity: 0;
    transform: translateX(-40px);
  }
  to {
    opacity: 1;
    transform: translateX(0);
  }
}

@keyframes slideInRight {
  from {
    opacity: 0;
    transform: translateX(40px);
  }
  to {
    opacity: 1;
    transform: translateX(0);
  }
}

@keyframes float {
  0%, 100% { transform: translateY(0px); }
  50% { transform: translateY(-10px); }
}

@keyframes pulse {
  0%, 100% { opacity: 1; }
  50% { opacity: 0.8; }
}

@keyframes shimmer {
  0% { background-position: -1000px 0; }
  100% { background-position: 1000px 0; }
}

@keyframes scaleIn {
  from {
    opacity: 0;
    transform: scale(0.95);
  }
  to {
    opacity: 1;
    transform: scale(1);
  }
}

/* ===== 타이핑 배너 ===== */
/* ===== 인사 배너 (한 번만 나타나는 정적 버전) ===== */
.greeting-banner {
  text-align: center;
  margin: 1.5rem 0 2rem;
  animation: fadeInDown 0.8s ease-out;
}

.greeting-banner__title {
  font-size: 1.25rem;
  font-weight: 800;
  letter-spacing: -0.02em;
  margin: 0 0 0.55rem;
  background: linear-gradient(135deg, #2E8B57, #4285f4);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.greeting-banner__sub {
  display: inline-flex;
  align-items: center;
  gap: 0.5em;
  font-size: 0.78rem;
  font-weight: 700;
  color: #4285f4;
  background: rgba(66,133,244,.08);
  padding: 0.4em 1em;
  border-radius: 999px;
}

/* ===== 소개 카드 ===== */
.intro-card {
  display: flex;
  align-items: flex-start;
  gap: 1.2rem;
  padding: 1.5rem 1.8rem;
  border-radius: 16px;
  background: linear-gradient(135deg, rgba(66,133,244,.08), rgba(52,168,83,.08));
  border: 1.5px solid rgba(66,133,244,.2);
  margin: 1.5rem 0 2rem;
  animation: fadeInUp 0.8s ease-out 0.1s both;
  position: relative;
  overflow: hidden;
  transition: all 0.3s ease;
}

.intro-card::before {
  content: '';
  position: absolute;
  top: 0;
  left: -100%;
  width: 100%;
  height: 100%;
  background: linear-gradient(90deg, transparent, rgba(255,255,255,.15), transparent);
  transition: left 0.7s ease;
}

.intro-card:hover::before {
  left: 100%;
}

.intro-card:hover {
  border-color: rgba(66,133,244,.35);
  box-shadow: 0 8px 24px rgba(66,133,244,.12);
  transform: translateY(-3px);
}

.intro-card__emoji {
  font-size: 2rem;
  line-height: 1;
  flex-shrink: 0;
  animation: float 3s ease-in-out infinite;
  display: flex;
  align-items: center;
  justify-content: center;
}

.intro-card p {
  margin: 0;
  line-height: 1.75;
  color: #555;
  font-size: 0.9rem;
  font-weight: 500;
}

/* ===== 진행 상황 섹션 (목표와 명언 포함) ===== */
.info-grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 1.5rem;
  margin: 0 0 2rem;
}

.info-tile {
  padding: 1.8rem;
  border-radius: 16px;
  background: #fff;
  border: 1px solid rgba(0,0,0,.08);
  box-shadow: 0 4px 12px rgba(0,0,0,.05);
  animation: fadeInUp 0.8s ease-out both;
  transition: all 0.35s cubic-bezier(0.34, 1.56, 0.64, 1);
  position: relative;
  overflow: hidden;
}

.info-tile:nth-child(1) {
  animation-delay: 0.2s;
  grid-column: 1 / -1;
}

.info-tile:nth-child(2) {
  animation-delay: 0.3s;
  grid-column: 1 / -1;
}

.info-tile::after {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  height: 3px;
  background: linear-gradient(90deg, #4285f4, #34a853);
  transform: scaleX(0);
  transform-origin: left;
  transition: transform 0.5s ease;
}

.info-tile:hover::after {
  transform: scaleX(1);
}

.info-tile:hover {
  transform: translateY(-6px);
  box-shadow: 0 12px 28px rgba(0,0,0,.1);
  border-color: rgba(66,133,244,.15);
}

.info-tile__icon {
  font-size: 1.8rem;
  margin-bottom: 0.8rem;
  display: inline-block;
  animation: float 3s ease-in-out infinite;
}

.info-tile__label {
  font-size: 0.75rem;
  font-weight: 800;
  color: #999;
  text-transform: uppercase;
  letter-spacing: 0.08em;
  margin-bottom: 0.8rem;
  display: block;
}

.info-tile__value {
  font-size: 1.8rem;
  font-weight: 800;
  margin-bottom: 0.8rem;
  background: linear-gradient(135deg, #4285f4 0%, #34a853 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  letter-spacing: -0.02em;
}

.info-tile__sub {
  font-size: 0.85rem;
  font-weight: 500;
  color: #aaa;
  line-height: 1.6;
}

/* 진행 상황 카드 - 가로로 꽉 채우는 레이아웃 */
.info-tile--progress {
  display: flex;
  align-items: center;
  gap: 2.2rem;
}

.info-tile--progress .info-tile__left {
  flex: 0 0 auto;
  min-width: 150px;
}

.info-tile--progress .info-tile__left .info-tile__icon,
.info-tile--progress .info-tile__left .info-tile__label {
  margin-bottom: 0.5rem;
}

.info-tile--progress .info-tile__left .info-tile__value {
  margin-bottom: 0;
}

.info-tile--progress .info-tile__right {
  flex: 1;
  min-width: 0;
}

.info-tile--progress .info-tile__right .progress-bar {
  margin: 0 0 0.6rem;
}

.info-tile--progress .info-tile__right .info-tile__sub {
  margin: 0;
}

@media (max-width: 640px) {
  .info-tile--progress {
    flex-direction: column;
    align-items: stretch;
    gap: 1rem;
  }
  
  .info-tile--progress .info-tile__left {
    min-width: 0;
  }
}

/* 진행도 바 */
.progress-bar {
  width: 100%;
  height: 12px;
  border-radius: 999px;
  background: rgba(0,0,0,.06);
  overflow: hidden;
  margin: 1rem 0 0.8rem;
  position: relative;
}

.progress-bar::after {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: linear-gradient(90deg, rgba(255,255,255,.3), transparent);
  pointer-events: none;
}

.progress-bar__fill {
  height: 100%;
  border-radius: 999px;
  background: linear-gradient(90deg, #4285f4, #34a853);
  width: 0;
  animation: fillBar 1.8s cubic-bezier(0.34, 1.56, 0.64, 1) 0.5s forwards;
  box-shadow: 0 0 8px rgba(66,133,244,.4);
}

@keyframes fillBar {
  to { width: var(--progress-width, 50%); }
}

/* 목표 카드 특별 스타일 */
.info-tile--goal {
  background: linear-gradient(135deg, rgba(255,112,67,.06), rgba(255,112,67,.02));
  border: 1px solid rgba(255,112,67,.12);
}

.info-tile--goal::after {
  background: linear-gradient(90deg, #ff7043, #ff9100);
}

.goal-quote {
  margin: 0;
  font-size: 1.05rem;
  font-weight: 700;
  line-height: 1.7;
  color: #333;
  animation: slideInLeft 0.8s ease-out 0.4s both;
}

/* 명언 섹션 */
.quote-section {
  padding: 1.2rem;
  background: rgba(66,133,244,.04);
  border-left: 4px solid #4285f4;
  border-radius: 8px;
  margin-top: 1rem;
  animation: scaleIn 0.6s ease-out 0.5s both;
}

.daily-quote {
  margin: 0;
  padding: 0;
  line-height: 1.7;
}

.daily-quote__en {
  display: block;
  font-size: 0.9rem;
  font-style: italic;
  color: #555;
  margin-bottom: 0.5rem;
  font-weight: 500;
}

.daily-quote__ko {
  display: block;
  font-size: 0.85rem;
  color: #777;
  font-weight: 400;
}

/* ===== 빠른 네비게이션 ===== */
.quicknav-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 1.2rem;
  margin: 2rem 0;
}

.quicknav-card {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 0.8rem;
  padding: 1.8rem 1.2rem;
  border: 2px solid rgba(0,0,0,.08);
  border-radius: 14px;
  text-decoration: none;
  color: inherit;
  font-weight: 700;
  font-size: 1.05rem;
  background: #fff;
  transition: all 0.35s cubic-bezier(0.34, 1.56, 0.64, 1);
  animation: fadeInUp 0.8s ease-out both;
  position: relative;
  overflow: hidden;
}

.quicknav-card:nth-child(1) { animation-delay: 0.4s; }
.quicknav-card:nth-child(2) { animation-delay: 0.5s; }
.quicknav-card:nth-child(3) { animation-delay: 0.6s; }

.quicknav-card::before {
  content: '';
  position: absolute;
  top: 0;
  left: -100%;
  width: 100%;
  height: 100%;
  background: linear-gradient(90deg, transparent, rgba(66,133,244,.1), transparent);
  transition: left 0.6s ease;
}

.quicknav-card:hover::before {
  left: 100%;
}

.quicknav-card:hover {
  transform: translateY(-8px) scale(1.02);
  box-shadow: 0 12px 32px rgba(66,133,244,.2);
  border-color: rgba(66,133,244,.3);
  background: linear-gradient(135deg, rgba(66,133,244,.02), rgba(52,168,83,.02));
}

.quicknav-card__icon {
  font-size: 2.2rem;
  transition: transform 0.3s ease;
}

.quicknav-card:hover .quicknav-card__icon {
  transform: scale(1.15) rotateY(10deg);
  animation: float 2s ease-in-out infinite;
}

.quicknav-card__label {
  letter-spacing: -0.02em;
}

/* ===== 포스트 리스트 ===== */
.post-list {
  display: grid;
  /* auto-fill이면 카드 수(2개)보다 화면이 넓을 때 빈 트랙이 오른쪽에 남아서
     카드가 폭을 다 못 채우고 왼쪽에 몰려 보임. auto-fit으로 바꿔서 실제
     카드가 있는 만큼만 컬럼을 만들고 남은 폭을 나눠 갖도록 함(화면이 넓어질수록
     카드도 같이 넓어짐). */
  grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
  gap: 1.4rem;
  margin: 1.5rem 0 2rem;
}

.post-list__card {
  display: flex;
  flex-direction: column;
  padding: 1.35rem 1.5rem;
  border: 1px solid rgba(0,0,0,.08);
  border-radius: 14px;
  text-decoration: none !important;
  color: inherit;
  background: #fff;
  box-shadow: 0 4px 12px rgba(0,0,0,.05);
  transition: all 0.4s cubic-bezier(0.34, 1.56, 0.64, 1);
  position: relative;
  overflow: hidden;
  animation: fadeInUp 0.8s ease-out both;
}

.post-list__card,
.post-list__card:hover,
.post-list__card:visited,
.post-list__card:active,
.post-list__card * {
  text-decoration: none !important;
}

.post-list__card:nth-child(1) { animation-delay: 0.5s; }
.post-list__card:nth-child(2) { animation-delay: 0.6s; }

.post-list__card::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  height: 5px;
  background: linear-gradient(90deg, #4285f4, #34a853);
  transform: scaleY(0);
  transform-origin: top;
  transition: transform 0.4s ease;
}

.post-list__card--cloud::before { background: linear-gradient(90deg, #4285f4, #1e88e5); }
.post-list__card--database::before { background: linear-gradient(90deg, #a142f4, #7e57c2); }
.post-list__card--projects::before { background: linear-gradient(90deg, #ff7043, #ff5722); }

.post-list__card:hover::before {
  transform: scaleY(1);
}

.post-list__card:hover {
  transform: translateY(-10px);
  box-shadow: 0 16px 40px rgba(0,0,0,.12);
  border-color: rgba(0,0,0,.12);
}

.post-list__top {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 0.8rem;
  margin-bottom: 1rem;
}

.post-list__badge {
  display: inline-flex;
  align-items: center;
  gap: 0.4em;
  font-size: 0.66rem;
  font-weight: 800;
  padding: 0.35em 0.8em;
  border-radius: 999px;
  color: #fff;
  white-space: nowrap;
  text-transform: uppercase;
  letter-spacing: 0.05em;
  animation: pulse 2.5s ease-in-out infinite;
  box-shadow: 0 2px 8px rgba(0,0,0,.1);
}

.post-list__badge--cloud { background: #4285f4; }
.post-list__badge--database { background: #a142f4; }
.post-list__badge--projects { background: #ff7043; }
.post-list__badge--default { background: #888; }

.post-list__date {
  font-size: 0.72rem;
  color: #aaa;
  font-weight: 600;
  white-space: nowrap;
  transition: color 0.3s ease;
}

.post-list__card:hover .post-list__date {
  color: #4285f4;
}

.post-list__title {
  font-weight: 800;
  font-size: 0.98rem;
  line-height: 1.4;
  margin-bottom: 0.6rem;
  color: #222;
  transition: color 0.3s ease;
  letter-spacing: -0.01em;
}

.post-list__card:hover .post-list__title {
  color: #4285f4;
}

.post-list__excerpt {
  font-size: 0.8rem;
  color: #777;
  line-height: 1.55;
  margin-bottom: 0.8rem;
  flex-grow: 1;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

.post-list__more {
  font-size: 0.73rem;
  font-weight: 700;
  color: #4285f4;
  margin-top: auto;
  transition: all 0.3s ease;
  display: inline-flex;
  align-items: center;
  gap: 0.3em;
  text-transform: uppercase;
  letter-spacing: 0.05em;
}

.post-list__card:hover .post-list__more {
  gap: 0.8em;
  color: #2c5aa0;
}

.post-list__empty {
  color: #999;
  padding: 2rem 1rem;
  text-align: center;
  font-size: 0.95rem;
}

/* ===== GitHub 위젯 ===== */
.gh-widget {
  margin: 0 0 1.5rem;
  padding: 1.6rem;
  border: 1px solid rgba(0,0,0,.08);
  border-radius: 14px;
  background: #fff;
  box-shadow: 0 4px 12px rgba(0,0,0,.05);
  animation: fadeInUp 0.8s ease-out both;
  transition: all 0.3s ease;
}

.gh-widget:nth-of-type(1) { animation-delay: 0.7s; }
.gh-widget:nth-of-type(2) { animation-delay: 0.8s; }

.gh-widget:hover {
  box-shadow: 0 8px 24px rgba(0,0,0,.08);
  transform: translateY(-2px);
  border-color: rgba(66,133,244,.1);
}

.gh-widget__label {
  font-size: 0.8rem;
  font-weight: 800;
  color: #999;
  text-transform: uppercase;
  letter-spacing: 0.08em;
  margin-bottom: 1rem;
  display: flex;
  align-items: center;
  gap: 0.6em;
}

.gh-widget__label::before {
  content: '';
  width: 3px;
  height: 16px;
  background: linear-gradient(180deg, #4285f4, #34a853);
  border-radius: 999px;
}

.gh-stats-grid {
  display: grid;
  /* auto-fit: 이미지가 1장뿐이라도 화면 폭 전체를 채우도록 늘어남
     (auto-fill이면 넓은 화면에서 이미지 오른쪽에 빈 공간이 남음) */
  grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
  gap: 1rem;
  margin: 1rem 0;
}

.gh-stats-grid img {
  width: 100%;
  border-radius: 10px;
  transition: all 0.3s ease;
  display: block;
}

.gh-stats-grid img:hover {
  transform: scale(1.02);
  filter: drop-shadow(0 6px 16px rgba(0,0,0,.12));
}

.gh-chart-wrap {
  overflow-x: auto;
  margin: 1rem 0;
}

.gh-chart-wrap img {
  max-width: 100%;
  transition: filter 0.3s ease;
  display: block;
}

.gh-chart-wrap img:hover {
  filter: drop-shadow(0 4px 12px rgba(0,0,0,.1));
}

/* ===== 우측 사이드바 ===== */
.visitor-aside {
  animation: slideInRight 0.8s ease-out;
}

.visitor-box {
  padding: 1.4em !important;
  animation: fadeInUp 0.8s ease-out 0.2s both;
}

.visitor-label {
  display: block;
  font-size: 0.8em;
  color: #999;
  margin-bottom: 0.4em;
  font-weight: 700;
  text-transform: uppercase;
  letter-spacing: 0.05em;
}

.visitor-row {
  margin-bottom: 1em;
  animation: fadeInUp 0.6s ease-out both;
}

.visitor-row:nth-child(1) { animation-delay: 0.3s; }
.visitor-row:nth-child(2) { animation-delay: 0.4s; }
.visitor-row:nth-child(3) { animation-delay: 0.5s; }
.visitor-row:nth-child(4) { animation-delay: 0.6s; }

.visitor-row:last-of-type {
  margin-bottom: 0;
}

.visitor-row--split {
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
  gap: 0.8em;
}

.visitor-row--split .visitor-col {
  flex: 1;
  min-width: 0;
}

.visitor-row--split .visitor-col img {
  max-width: 100%;
  border-radius: 8px;
  transition: filter 0.3s ease;
}

.visitor-row--split .visitor-col img:hover {
  filter: drop-shadow(0 2px 8px rgba(0,0,0,.1));
}

.visitor-tech-stack img {
  max-width: 100%;
  transition: transform 0.3s ease, filter 0.3s ease;
}

.visitor-tech-stack img:hover {
  transform: scale(1.05);
  filter: drop-shadow(0 2px 8px rgba(66,133,244,.2));
}

.chip-list {
  display: flex;
  flex-wrap: wrap;
  gap: 0.5rem;
}

.chip {
  display: inline-block;
  padding: 0.35em 0.75em;
  border-radius: 999px;
  background: rgba(66,133,244,.12);
  color: #3367d6;
  font-size: 0.74rem;
  font-weight: 700;
  transition: all 0.3s ease;
  animation: fadeInUp 0.6s ease-out both;
}

.chip:nth-child(1) { animation-delay: 0.35s; }
.chip:nth-child(2) { animation-delay: 0.4s; }
.chip:nth-child(3) { animation-delay: 0.45s; }

.chip:hover {
  background: #4285f4;
  color: #fff;
  transform: translateY(-3px);
  box-shadow: 0 4px 12px rgba(66,133,244,.3);
}

.copy-link-btn {
  padding: 0.9em 1.2em;
  font-size: 0.9em;
  border: 2px solid #4285f4;
  background: #fff;
  color: #4285f4;
  border-radius: 8px;
  cursor: pointer;
  font-weight: 700;
  transition: all 0.3s ease;
  width: 100%;
  animation: fadeInUp 0.8s ease-out both;
  animation-delay: 0.8s;
  text-transform: uppercase;
  letter-spacing: 0.05em;
  font-size: 0.8em;
}

.copy-link-btn:hover {
  background: #4285f4;
  color: #fff;
  box-shadow: 0 6px 16px rgba(66,133,244,.3);
  transform: translateY(-2px);
}

.copy-link-btn:active {
  transform: translateY(0);
}

/* ===== 헤더 언더라인 ===== */
h2 {
  position: relative;
  margin-bottom: 1.5rem;
  font-size: 1.4rem;
  font-weight: 800;
  letter-spacing: -0.02em;
  color: #222;
}

h2::after {
  content: '';
  position: absolute;
  bottom: -8px;
  left: 0;
  width: 0;
  height: 4px;
  background: linear-gradient(90deg, #4285f4, #34a853);
  border-radius: 999px;
  animation: expandWidth 0.8s ease-out 0.3s forwards;
}

@keyframes expandWidth {
  to {
    width: 80px;
  }
}

/* ===== 반응형 ===== */
@media (max-width: 1024px) {
  .info-grid {
    grid-template-columns: 1fr;
  }
  
  .info-tile:nth-child(2) {
    grid-column: 1;
  }
}

@media (max-width: 768px) {
  .greeting-banner {
    margin: 1rem 0 1.5rem;
  }
  
  .intro-card {
    padding: 1.2rem 1.4rem;
    gap: 0.8rem;
  }
  
  .intro-card p {
    font-size: 0.85rem;
  }
  
  .info-grid {
    gap: 1rem;
  }
  
  .info-tile {
    padding: 1.4rem;
  }
  
  .quicknav-grid {
    grid-template-columns: 1fr;
    gap: 0.8rem;
  }
  
  .post-list {
    grid-template-columns: 1fr;
    gap: 1rem;
  }
  
  h2 {
    font-size: 1.2rem;
  }
}
</style>

<aside class="sidebar__right sticky visitor-aside">
<nav class="toc">
<header><h4 class="nav__title"><i class="fas fa-id-card"></i> 한눈에 보기</h4></header>
<div class="toc__menu visitor-box">
<div class="visitor-row visitor-row--split">
<div class="visitor-col">
<span class="visitor-label">오늘 방문자</span>
<img id="visitor-today-badge" alt="오늘 방문자 수" />
</div>
<div class="visitor-col">
<span class="visitor-label">누적 방문자</span>
<img src="https://visitor-badge.laobi.icu/badge?page_id=paaaraaaeaaa.my-blog&left_color=555555&right_color=79C83D" alt="누적 방문자 수" />
</div>
</div>
<div class="visitor-row visitor-learned">
<span class="visitor-label">배운 것</span>
<div class="chip-list">
<span class="chip">Git</span>
<span class="chip">GitHub</span>
<span class="chip">마크다운</span>
</div>
</div>
<div class="visitor-row visitor-tech-stack">
<span class="visitor-label">기술 스택</span>
<img src="https://skillicons.dev/icons?i=git,github,md,py,vscode" alt="기술 스택" loading="lazy" />
</div>
<button type="button" id="copy-link-btn" class="copy-link-btn">🔗 이 페이지 링크 복사</button>
</div>
</nav>
</aside>

<script>
(function () {
  var quotes = [
    { en: "Programs must be written for people to read, and only incidentally for machines to execute.", ko: "프로그램은 사람이 읽기 위해 작성되어야 하며, 기계가 실행하는 것은 부차적인 일이다.", author: "Harold Abelson" },
    { en: "Any fool can write code that a computer can understand. Good programmers write code that humans can understand.", ko: "바보도 컴퓨터가 이해하는 코드는 짤 수 있다. 좋은 프로그래머는 사람이 이해할 수 있는 코드를 짠다.", author: "Martin Fowler" },
    { en: "Make it work, make it right, make it fast.", ko: "일단 되게 만들고, 그다음 옳게 만들고, 그다음 빠르게 만들어라.", author: "Kent Beck" },
    { en: "Talk is cheap. Show me the code.", ko: "말은 쉽다. 코드로 보여줘라.", author: "Linus Torvalds" },
    { en: "Simplicity is prerequisite for reliability.", ko: "단순함은 신뢰성의 전제조건이다.", author: "Edsger W. Dijkstra" },
    { en: "Perfection is achieved, not when there is nothing more to add, but when there is nothing left to take away.", ko: "완벽함이란 더 보탤 것이 없을 때가 아니라, 더 뺄 것이 없을 때 이루어진다.", author: "Antoine de Saint-Exupéry" },
    { en: "The only way to go fast is to go well.", ko: "빨리 가는 유일한 방법은 제대로 가는 것이다.", author: "Robert C. Martin" },
    { en: "Measuring programming progress by lines of code is like measuring aircraft building progress by weight.", ko: "코드 줄 수로 개발 진척을 재는 것은 비행기 제작 진척을 무게로 재는 것과 같다.", author: "Bill Gates" },
    { en: "The most dangerous phrase in the language is, 'We've always done it this way.'", ko: "가장 위험한 말은 '우리는 항상 이렇게 해왔어'이다.", author: "Grace Hopper" },
    { en: "Learning without thought is labour lost; thought without learning is perilous.", ko: "배우기만 하고 생각하지 않으면 얻는 것이 없고, 생각만 하고 배우지 않으면 위태롭다.", author: "Confucius" },
    { en: "Premature optimization is the root of all evil.", ko: "섣부른 최적화는 모든 악의 근원이다.", author: "Donald Knuth" },
    { en: "Testing shows the presence, not the absence, of bugs.", ko: "테스트는 버그가 있다는 것을 보여줄 뿐, 버그가 없다는 것을 보여주지는 않는다.", author: "Edsger W. Dijkstra" }
  ];
  
  var pick = quotes[Math.floor(Math.random() * quotes.length)];
  
  // 진행상황 박스에 명언 삽입
  document.addEventListener('DOMContentLoaded', function() {
    var goalTile = document.querySelector('.info-tile--goal');
    if (goalTile) {
      var quoteDiv = document.createElement('div');
      quoteDiv.className = 'quote-section';
      
      var dailyQuote = document.createElement('p');
      dailyQuote.className = 'daily-quote';
      
      var enQuote = document.createElement('span');
      enQuote.className = 'daily-quote__en';
      enQuote.textContent = '"' + pick.en + '" — ' + pick.author;
      
      var koQuote = document.createElement('span');
      koQuote.className = 'daily-quote__ko';
      koQuote.textContent = pick.ko;
      
      dailyQuote.appendChild(enQuote);
      dailyQuote.appendChild(koQuote);
      quoteDiv.appendChild(dailyQuote);
      
      goalTile.appendChild(quoteDiv);
    }
  });
})();

// 링크 복사
document.addEventListener('DOMContentLoaded', function() {
  var copyBtn = document.getElementById('copy-link-btn');
  if (copyBtn) {
    copyBtn.addEventListener('click', function() {
      navigator.clipboard.writeText(window.location.href).then(function() {
        var originalText = copyBtn.textContent;
        copyBtn.textContent = '✅ 복사되었습니다!';
        setTimeout(function() {
          copyBtn.textContent = originalText;
        }, 2000);
      });
    });
  }
  
  // 진행도 바 애니메이션
  var progressElements = document.querySelectorAll('.progress-bar__fill');
  progressElements.forEach(function(el) {
    var width = el.style.width;
    el.style.setProperty('--progress-width', width);
  });
});
</script>

<div class="greeting-banner">
<p class="greeting-banner__title">안녕하세요, 개발 공부 중입니다 👋</p>
<span class="greeting-banner__sub">🔥 Git · GitHub 실습 기록 중</span>
</div>

<div class="intro-card">
<span class="intro-card__emoji">👋</span>
<p>안녕하세요! 개발을 처음 배우는 부트캠프 학습자입니다. 매일 배운 내용과 겪은 시행착오를 여기에 기록하면서, 몇 달 뒤에 다시 읽었을 때 "그때보다 늘었다"를 확인할 수 있는 블로그로 만들어가고 있습니다.</p>
</div>

{% comment %}
"N일차"는 주말 + 한국 공휴일(_data/holidays.yml)을 제외한 평일만 세서 계산.
반면 "총 일수"와 진행률(%)은 전체 기간의 달력 날짜 그대로(오늘까지 지난 날짜 비율)로 계산.
{% endcomment %}
{% assign start_ts = "2026-08-26" | date: "%s" | plus: 0 %}
{% assign end_ts = "2027-02-16" | date: "%s" | plus: 0 %}
{% assign today_ts = site.time | date: "%s" | plus: 0 %}
{% assign total_calendar_days = end_ts | minus: start_ts | divided_by: 86400 %}

{% assign day_number = 0 %}
{% for i in (0..total_calendar_days) %}
  {% assign offset_sec = i | times: 86400 %}
  {% assign cur_ts = start_ts | plus: offset_sec %}
  {% if cur_ts > today_ts %}{% break %}{% endif %}
  {% assign cur_wday = cur_ts | date: "%w" %}
  {% assign cur_date_str = cur_ts | date: "%Y-%m-%d" %}
  {% assign is_workday = true %}
  {% if cur_wday == "0" or cur_wday == "6" %}{% assign is_workday = false %}{% endif %}
  {% if site.data.holidays contains cur_date_str %}{% assign is_workday = false %}{% endif %}
  {% if is_workday %}
    {% assign day_number = day_number | plus: 1 %}
  {% endif %}
{% endfor %}

{% assign calendar_elapsed = today_ts | minus: start_ts | divided_by: 86400 | plus: 1 %}
{% assign percent = calendar_elapsed | times: 100 | divided_by: total_calendar_days %}
{% if percent > 100 %}{% assign percent = 100 %}{% endif %}
{% if percent < 0 %}{% assign percent = 0 %}{% endif %}

<div class="info-grid">
<div class="info-tile info-tile--progress">
<div class="info-tile__left">
<div class="info-tile__icon">📅</div>
<div class="info-tile__label">진행 상황</div>
<div class="info-tile__value">{{ day_number }}일차</div>
</div>
<div class="info-tile__right">
<div class="progress-bar"><div class="progress-bar__fill" style="width: {{ percent }}%; --progress-width: {{ percent }}%;"></div></div>
<div class="info-tile__sub">총 {{ total_calendar_days }}일 · {{ percent }}% · 2026.08.26~2027.02.16</div>
</div>
</div>

<div class="info-tile info-tile--goal">
<div class="info-tile__icon">🎯</div>
<div class="info-tile__label">목표</div>
<p class="goal-quote">하루도 빠짐없이 기록하고,<br>막혔던 부분은 반드시 다시 정리하기</p>
</div>
</div>

<div class="quicknav-grid">
<a class="quicknav-card" href="{{ '/cloud/' | relative_url }}"><span class="quicknav-card__icon">☁️</span><span class="quicknav-card__label">Cloud</span></a>
<a class="quicknav-card" href="{{ '/database/' | relative_url }}"><span class="quicknav-card__icon">🗄️</span><span class="quicknav-card__label">Database</span></a>
<a class="quicknav-card" href="{{ '/projects/' | relative_url }}"><span class="quicknav-card__icon">🚀</span><span class="quicknav-card__label">Projects</span></a>
</div>

## 📚 {{ page.list_title }}

{% assign recent_posts = site.posts | slice: 0, 2 %}
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

<div class="gh-widget">
<div class="gh-widget__label">📈 Streak</div>
<div class="gh-stats-grid">
<img src="https://streak-stats.demolab.com?user=paaaraaaeaaa&theme=transparent&hide_border=true" alt="GitHub Streak Stats" loading="lazy" />
</div>
</div>

<div class="gh-widget">
<div class="gh-widget__label">🟩 Contribution Calendar</div>
<div class="gh-chart-wrap">
<img src="https://ghchart.rshah.org/2E8B57/paaaraaaeaaa" alt="GitHub Contribution Chart" loading="lazy" />
</div>
</div>

> 꾸준함이 실력이 된다고 믿습니다. 오늘도 한 줄 더 기록합니다. 🚀

---
