---
layout: single
title: 학습 노트
list_title: 최근 글
author_profile: true
---

<style>
.post-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(240px, 1fr));
  gap: 1.1rem;
  margin: 1rem 0 1.5rem;
}
.post-list__card {
  display: flex;
  flex-direction: column;
  padding: 1.2rem 1.3rem 1.1rem;
  border: 1px solid rgba(0,0,0,.08);
  border-radius: 14px;
  text-decoration: none;
  color: inherit;
  background: #fff;
  box-shadow: 0 2px 8px rgba(0,0,0,.04);
  transition: transform .15s ease, box-shadow .15s ease, border-color .15s ease;
  position: relative;
  overflow: hidden;
}
.post-list__card::before {
  content: '';
  position: absolute;
  top: 0; left: 0;
  width: 100%;
  height: 4px;
  background: linear-gradient(90deg, #4285f4, #34a853);
}
.post-list__card--cloud::before { background: #4285f4; }
.post-list__card--database::before { background: #a142f4; }
.post-list__card--projects::before { background: #ff7043; }
.post-list__card:hover {
  transform: translateY(-4px);
  box-shadow: 0 10px 22px rgba(0,0,0,.1);
  border-color: rgba(0,0,0,.16);
}
.post-list__top {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: .5rem;
  margin-bottom: .7rem;
}
.post-list__badge {
  display: inline-flex;
  align-items: center;
  gap: .35em;
  font-size: .72rem;
  font-weight: 700;
  padding: .25em .7em;
  border-radius: 999px;
  color: #fff;
  white-space: nowrap;
}
.post-list__badge--cloud { background: #4285f4; }
.post-list__badge--database { background: #a142f4; }
.post-list__badge--projects { background: #ff7043; }
.post-list__badge--default { background: #888; }
.post-list__date {
  font-size: .76rem;
  color: #999;
  white-space: nowrap;
}
.post-list__title {
  font-weight: 700;
  font-size: 1.08rem;
  line-height: 1.4;
  margin-bottom: .5rem;
}
.post-list__excerpt {
  font-size: .85rem;
  color: #777;
  line-height: 1.6;
  margin-bottom: .8rem;
  flex-grow: 1;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
.post-list__more {
  font-size: .8rem;
  font-weight: 600;
  color: #4285f4;
  margin-top: auto;
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
.gh-widget {
  margin: 0 0 1.3rem;
  padding: 1rem 1.2rem 1.2rem;
  border: 1px solid rgba(0,0,0,.08);
  border-radius: 14px;
  background: #fff;
  box-shadow: 0 2px 8px rgba(0,0,0,.04);
}
.gh-widget .gh-stats-grid,
.gh-widget .gh-chart-wrap {
  margin: 0;
}
.daily-quote {
  margin: .2rem 0 0;
  padding: 0 .4rem;
  text-align: center;
  line-height: 1.7;
}
.daily-quote__en {
  display: block;
  font-size: .85em;
  font-style: italic;
  color: #333;
}
.daily-quote__ko {
  display: block;
  margin-top: .3em;
  font-size: .78em;
  color: #999;
}
.gh-widget__label {
  font-size: .82rem;
  font-weight: 700;
  color: #888;
  text-transform: uppercase;
  letter-spacing: .03em;
  margin-bottom: .7rem;
}
.visitor-row {
  margin-bottom: .8em;
}
.visitor-row:last-of-type {
  margin-bottom: 0;
}
.visitor-row--split {
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
  gap: .6em;
}
.visitor-row--split .visitor-col {
  flex: 1 1 0;
  min-width: 0;
}
.visitor-row--split .visitor-col img {
  max-width: 100%;
}
.visitor-tech-stack img {
  max-width: 100%;
}
.visitor-quote .daily-quote {
  padding: 0;
  text-align: left;
}
.visitor-quote .daily-quote__en {
  font-size: .8em;
}
.visitor-quote .daily-quote__ko {
  font-size: .74em;
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

/* 타이핑 애니메이션 인트로 배너 */
.typing-banner {
  display: flex;
  justify-content: center;
  margin: 0.5rem 0 1.2rem;
}
.typing-banner img {
  max-width: 100%;
}

/* 인사말 카드 */
.intro-card {
  display: flex;
  align-items: flex-start;
  gap: .8rem;
  padding: 1.2rem 1.4rem;
  border-radius: 14px;
  background: linear-gradient(135deg, rgba(66,133,244,.06), rgba(52,168,83,.06));
  border: 1px solid rgba(66,133,244,.12);
  margin: 1rem 0 1.5rem;
}
.intro-card__emoji {
  font-size: 1.6rem;
  line-height: 1.4;
  flex-shrink: 0;
}
.intro-card p {
  margin: 0;
  line-height: 1.7;
}

/* 배운 것 / 진행 상황 / 목표 3분할 카드 */
.info-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 1rem;
  margin: 0 0 1.5rem;
}
@media (max-width: 700px) {
  .info-grid { grid-template-columns: 1fr; }
}
.info-tile {
  padding: 1.1rem 1.2rem;
  border: 1px solid rgba(0,0,0,.08);
  border-radius: 14px;
  background: #fff;
  box-shadow: 0 2px 8px rgba(0,0,0,.04);
}
.info-tile__icon {
  font-size: 1.4rem;
  margin-bottom: .3rem;
}
.info-tile__label {
  font-size: .78rem;
  font-weight: 700;
  color: #888;
  text-transform: uppercase;
  letter-spacing: .03em;
  margin-bottom: .6rem;
}
.info-tile__value {
  font-size: 1.15rem;
  font-weight: 700;
  margin-bottom: .55rem;
}
.info-tile__sub {
  font-size: .74rem;
  font-weight: 400;
  color: #999;
}
.chip-list {
  display: flex;
  flex-wrap: wrap;
  gap: .4rem;
}
.chip {
  display: inline-block;
  padding: .3em .75em;
  border-radius: 999px;
  background: rgba(66,133,244,.1);
  color: #3367d6;
  font-size: .82rem;
  font-weight: 600;
}
.progress-bar--mini {
  height: 6px;
  border-radius: 999px;
  background: rgba(0,0,0,.08);
  overflow: hidden;
  margin-bottom: .4rem;
}
.progress-bar--mini .progress-bar__fill {
  height: 100%;
}
.info-tile--goal {
  background: linear-gradient(135deg, rgba(255,112,67,.06), rgba(255,112,67,.02));
  border-color: rgba(255,112,67,.15);
}
.goal-quote {
  margin: 0;
  font-size: .92rem;
  font-weight: 600;
  line-height: 1.6;
  color: #444;
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
<div class="visitor-row visitor-tech-stack">
<span class="visitor-label">기술 스택</span>
<img src="https://skillicons.dev/icons?i=git,github,md,py,vscode" alt="기술 스택" loading="lazy" />
</div>
<div class="visitor-row visitor-quote">
<span class="visitor-label">오늘의 개발 명언</span>
<p class="daily-quote">
<span id="daily-quote-en" class="daily-quote__en"></span>
<span id="daily-quote-ko" class="daily-quote__ko"></span>
</p>
</div>
<button type="button" id="copy-link-btn" class="copy-link-btn">🔗 이 페이지 링크 복사</button>
</div>
</nav>
</aside>

<script>
(function () {
  // 전부 실제로 존재하는 인용문만 담았습니다 (출처: Wikiquote 및 각 저자 본인의 저서/발언 기록).
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
  var enEl = document.getElementById('daily-quote-en');
  var koEl = document.getElementById('daily-quote-ko');
  if (enEl) enEl.textContent = '“' + pick.en + '” — ' + pick.author;
  if (koEl) koEl.textContent = pick.ko;
})();
</script>

<div class="typing-banner">
<img src="https://readme-typing-svg.demolab.com/?font=Noto+Sans+KR&size=22&pause=1200&color=2E8B57&center=true&vCenter=true&width=560&lines=%EC%95%88%EB%85%95%ED%95%98%EC%84%B8%EC%9A%94%2C%20%EA%B0%9C%EB%B0%9C%20%EA%B3%B5%EB%B6%80%20%EC%A4%91%EC%9E%85%EB%8B%88%EB%8B%A4%20%F0%9F%91%8B;Git%20%C2%B7%20GitHub%20%EC%8B%A4%EC%8A%B5%20%EA%B8%B0%EB%A1%9D%20%EC%A4%91;%EA%BE%B8%EC%A4%80%ED%9E%88%20%EC%84%B1%EC%9E%A5%ED%95%98%EB%8A%94%20%EA%B0%9C%EB%B0%9C%EC%9E%90%EA%B0%80%20%EB%90%98%EA%B3%A0%20%EC%8B%B6%EC%96%B4%EC%9A%94" alt="타이핑 인트로 배너" loading="lazy" />
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
<div class="info-tile">
<div class="info-tile__icon">📚</div>
<div class="info-tile__label">배운 것</div>
<div class="chip-list">
<span class="chip">Git</span>
<span class="chip">GitHub</span>
<span class="chip">마크다운</span>
</div>
</div>

<div class="info-tile">
<div class="info-tile__icon">📅</div>
<div class="info-tile__label">진행 상황</div>
<div class="info-tile__value">{{ day_number }}일차 <span class="info-tile__sub"></span></div>
<div class="progress-bar progress-bar--mini"><div class="progress-bar__fill" style="width: {{ percent }}%;"></div></div>
<div class="info-tile__sub">총 {{ total_calendar_days }}일 · {{ percent }}% · 2026.08.26~2027.02.16</div>
</div>

<div class="info-tile info-tile--goal">
<div class="info-tile__icon">🎯</div>
<div class="info-tile__label">목표</div>
<p class="goal-quote">하루도 빠짐없이 기록하고,<br>막혔던 부분은 반드시 다시 정리하기</p>
</div>
</div>

<div class="quicknav-grid">
<a class="quicknav-card" href="{{ '/cloud/' | relative_url }}"><span class="quicknav-card__icon">☁️</span>Cloud</a>
<a class="quicknav-card" href="{{ '/database/' | relative_url }}"><span class="quicknav-card__icon">🗄️</span>Database</a>
<a class="quicknav-card" href="{{ '/projects/' | relative_url }}"><span class="quicknav-card__icon">🚀</span>Projects</a>
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
<div class="gh-widget__label">📈 Strick</div>
<div class="gh-stats-grid">
<img src="https://streak-stats.demolab.com?user=paaaraaaeaaa&theme=transparent&hide_border=true" alt="GitHub Streak Stats" loading="lazy" />
</div>
</div>

<div class="gh-widget">
<div class="gh-widget__label">🟩 Contribution Calender </div>
<div class="gh-chart-wrap">
<img src="https://ghchart.rshah.org/2E8B57/paaaraaaeaaa" alt="GitHub Contribution Chart" loading="lazy" />
</div>
</div>

> 꾸준함이 실력이 된다고 믿습니다. 오늘도 한 줄 더 기록합니다. 🚀

---
