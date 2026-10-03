---
layout: single
title: 학습 노트
author_profile: true
classes: home-page
---

{%- comment -%}
  홈 = 한눈에 보는 대시보드. 스타일은 assets/css/site.css "10. 홈" 섹션.
  순서: 미션 패널(진행 상황) → 섹션 바로가기 → 최근 글 → GitHub 활동
{%- endcomment -%}

<aside class="sidebar__right sticky home-aside">
<section class="home-panel" aria-label="방문자와 기술 스택">
<h2 class="home-panel__title">한눈에 보기</h2>
{%- comment -%}
  수료까지 모듈 진척: 완료 = Projects 결과물 글이 있는 모듈 / 진행 중 = 학습노트가 있고 결과물이 아직 없는 첫 모듈.
  진행 중 막대는 _data/module_schedule.yml에 날짜가 있으면 그 비율만큼, 없으면 빗금으로 표시.
{%- endcomment -%}
{%- assign mp_proj = site.categories.Projects | where_exp: "p", "p.type == nil" -%}
{%- assign mp_daily = site.categories.Cloud | where_exp: "p", "p.type != 'practice'" | where_exp: "p", "p.module" -%}
{%- assign mp_done = 0 -%}{%- assign mp_current = 0 -%}{%- assign mp_now = site.time | date: "%s" | plus: 0 -%}
{%- capture mp_bars -%}
{%- for i in (1..6) -%}
{%- assign key = i | append: "" -%}
{%- assign has_proj = mp_proj | where_exp: "p", "p.module == i" | size -%}
{%- assign notes = mp_daily | where_exp: "p", "p.module == i" | size -%}
{%- assign fill = 0 -%}{%- assign st = "upcoming" -%}{%- assign st_label = "예정" -%}
{%- if has_proj > 0 -%}
{%- assign st = "done" -%}{%- assign st_label = "완료" -%}{%- assign fill = 100 -%}{%- assign mp_done = mp_done | plus: 1 -%}
{%- elsif mp_current == 0 and notes > 0 -%}
{%- assign st = "current" -%}{%- assign st_label = "진행 중" -%}{%- assign mp_current = i -%}
{%- assign sched = site.data.module_schedule[key] -%}
{%- if sched and sched.start and sched.end -%}
{%- assign s0 = sched.start | date: "%s" | plus: 0 -%}{%- assign s1 = sched.end | date: "%s" | plus: 0 -%}
{%- assign span = s1 | minus: s0 -%}{%- assign fill = mp_now | minus: s0 | times: 100 | divided_by: span -%}
{%- if fill > 100 -%}{%- assign fill = 100 -%}{%- endif -%}{%- if fill < 0 -%}{%- assign fill = 0 -%}{%- endif -%}
{%- else -%}{%- assign fill = -1 -%}{%- endif -%}
{%- endif -%}
<span class="mbar is-{{ st }}" title="모듈 {{ i }} · {{ site.data.modules[key] }} · {{ st_label }}"><span class="mbar__track">{%- if fill == -1 -%}<span class="mbar__fill mbar__fill--hatch"></span>{%- elsif fill > 0 -%}<span class="mbar__fill" style="width: {{ fill }}%;"></span>{%- endif -%}</span><span class="mbar__n">{{ i }}</span></span>
{%- endfor -%}
{%- endcapture -%}
<div class="home-panel__row">
<span class="home-panel__label home-panel__label--split"><span>수료까지 모듈 진척</span><b>{{ mp_done }} / 6 완료</b></span>
<a class="mbars" href="{{ '/projects/' | relative_url }}" aria-label="모듈 진척: 6개 중 {{ mp_done }}개 완료">{{ mp_bars }}</a>
{%- if mp_current > 0 -%}{%- assign ck = mp_current | append: "" -%}<span class="mbars__note">지금 모듈 {{ mp_current }} · {{ site.data.modules[ck] }}</span>{%- else -%}{%- assign nk = mp_done | plus: 1 | append: "" -%}{%- if mp_done < 6 -%}<span class="mbars__note">다음 모듈 {{ nk }} · {{ site.data.modules[nk] }}</span>{%- endif -%}{%- endif -%}
</div>
<div class="home-panel__row home-panel__row--split">
<div>
<span class="home-panel__label">오늘 방문자</span>
<img id="visitor-today-badge" alt="오늘 방문자 수" />
</div>
<div>
<span class="home-panel__label">누적 방문자</span>
<img src="https://visitor-badge.laobi.icu/badge?page_id=paaaraaaeaaa.my-blog&left_color=161B22&right_color=238636" alt="누적 방문자 수" />
</div>
</div>
{%- comment -%} 자주 쓴 태그: 글 tags를 세서 상위 8개 (_data/tag_rules.yml의 분류성 태그는 제외). 누르면 검색창이 열린다 {%- endcomment -%}
{%- assign empty_array = "" | split: "," -%}
{%- assign tag_rank = "" | split: "," -%}
{%- assign tag_hidden = site.data.tag_rules.hidden_in_ranking | default: empty_array -%}
{%- for t in site.tags -%}{%- unless tag_hidden contains t[0] -%}{%- capture entry -%}{{ t[1].size | plus: 1000 }}|{{ t[0] }}{%- endcapture -%}{%- assign tag_rank = tag_rank | push: entry -%}{%- endunless -%}{%- endfor -%}
{%- assign tag_rank = tag_rank | sort | reverse -%}
<div class="home-panel__row">
<span class="home-panel__label">자주 쓴 태그</span>
<div class="chip-list">
{%- for e in tag_rank limit: 8 -%}{%- assign parts = e | split: "|" -%}
<button type="button" class="chip chip--btn" data-search="{{ parts[1] }}">{{ parts[1] }}<span class="chip__n">{{ parts[0] | minus: 1000 }}</span></button>
{%- endfor -%}
</div>
</div>
{%- comment -%}
  기술 스택: _data/skills.yml 기준, 그 기술 태그가 붙은 "글 수"가 많은 순서로 최대 10개.
  (태그 개수로 세면 별칭이 많은 기술이 한 글에서 여러 번 세어지므로 글 단위로 센다)
{%- endcomment -%}
{%- assign skill_rank = "" | split: "," -%}
{%- for sk in site.data.skills -%}
{%- assign n = 0 -%}
{%- for p in site.posts -%}
{%- assign hit = false -%}
{%- for t in p.tags -%}{%- assign td = t | downcase -%}{%- if sk.aliases contains td -%}{%- assign hit = true -%}{%- break -%}{%- endif -%}{%- endfor -%}
{%- if hit -%}{%- assign n = n | plus: 1 -%}{%- endif -%}
{%- endfor -%}
{%- if n > 0 or sk.always -%}{%- capture entry -%}{{ n | plus: 1000 }}|{{ sk.id }}{%- endcapture -%}{%- assign skill_rank = skill_rank | push: entry -%}{%- endif -%}
{%- endfor -%}
{%- assign skill_rank = skill_rank | sort | reverse -%}
{%- assign skill_ids = "" | split: "," -%}
{%- for e in skill_rank limit: 10 -%}{%- assign parts = e | split: "|" -%}{%- assign skill_ids = skill_ids | push: parts[1] -%}{%- endfor -%}
<div class="home-panel__row">
<span class="home-panel__label home-panel__label--split"><span>기술 스택</span><b>많이 쓴 순 {{ skill_ids.size }}개</b></span>
<img class="home-panel__stack" src="https://skillicons.dev/icons?i={{ skill_ids | join: ',' }}&theme=dark&perline=5" data-src-light="https://skillicons.dev/icons?i={{ skill_ids | join: ',' }}&theme=light&perline=5" alt="{{ skill_ids | join: ', ' }}" loading="lazy" />
</div>
<button type="button" id="copy-link-btn" class="btn-line">copy link</button>
</section>
</aside>

{% comment %}
"N일차"는 첫 학습노트 날(2026-08-27)을 1일차로 두고, 주말 + 한국 공휴일(_data/holidays.yml)을 제외한 평일만 세서 계산.
(8/26은 부트캠프 출발일이라 궤적·진행률에는 포함하지만 일차에는 세지 않는다)
반면 "총 일수"와 진행률(%)은 전체 기간의 달력 날짜 그대로(오늘까지 지난 날짜 비율)로 계산.
{% endcomment %}
{% assign start_ts = "2026-08-26" | date: "%s" | plus: 0 %}
{% assign end_ts = "2027-02-16" | date: "%s" | plus: 0 %}
{% assign today_ts = site.time | date: "%s" | plus: 0 %}
{% assign total_calendar_days = end_ts | minus: start_ts | divided_by: 86400 %}

{% assign day_start_ts = "2026-08-27" | date: "%s" | plus: 0 %}
{% assign day_number = 0 %}
{% for i in (0..total_calendar_days) %}
  {% assign offset_sec = i | times: 86400 %}
  {% assign cur_ts = day_start_ts | plus: offset_sec %}
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

{% assign remaining_days = total_calendar_days | minus: calendar_elapsed %}
{% if remaining_days < 0 %}{% assign remaining_days = 0 %}{% endif %}
{% assign span_sec = end_ts | minus: start_ts %}

{%- comment -%} 진행 막대: 30칸 ASCII. 채운 칸 수 = 진행률 × 30 {%- endcomment -%}
{%- assign bar_cells = 30 -%}
{%- assign bar_fill = percent | times: bar_cells | divided_by: 100 -%}
{%- capture bar_on -%}{%- for i in (1..bar_cells) -%}{%- if i <= bar_fill -%}█{%- endif -%}{%- endfor -%}{%- endcapture -%}
{%- capture bar_off -%}{%- for i in (1..bar_cells) -%}{%- if i > bar_fill -%}░{%- endif -%}{%- endfor -%}{%- endcapture -%}
{%- comment -%} 이번 주 기록: 빌드 시점 기준 이번 주 월~금에 학습노트가 있으면 채운 점 {%- endcomment -%}
{%- assign today_u = site.time | date: "%u" | plus: 0 -%}
{%- assign today_str = site.time | date: "%Y-%m-%d" -%}
{%- assign back = today_u | minus: 1 | times: 86400 -%}
{%- assign week_mon = today_ts | minus: back -%}
{%- assign cloud_daily_all = site.categories.Cloud | where_exp: "p", "p.type != 'practice'" -%}
{%- assign week_hits = 0 -%}
{%- capture week_dots -%}
{%- assign labels = "월,화,수,목,금" | split: "," -%}
{%- for d in (1..5) -%}
{%- assign off = d | minus: 1 | times: 86400 -%}
{%- assign cell = week_mon | plus: off | date: "%Y-%m-%d" -%}
{%- assign hit = false -%}
{%- for p in cloud_daily_all -%}{%- assign pd = p.date | date: "%Y-%m-%d" -%}{%- if pd == cell -%}{%- assign hit = true -%}{%- endif -%}{%- endfor -%}
{%- if hit -%}{%- assign week_hits = week_hits | plus: 1 -%}{%- endif -%}
<span class="wdot{% if hit %} is-on{% endif %}{% if cell == today_str %} is-today{% endif %}{% if site.data.holidays contains cell %} is-off{% endif %}" title="{{ cell }}{% if hit %} 기록함{% elsif site.data.holidays contains cell %} 휴일{% endif %}">{{ labels[forloop.index0] }}</span>
{%- endfor -%}
{%- endcapture -%}
<section class="term mission" aria-label="학습 진행 상황">
<div class="term__bar"><span class="term__path">yerin@learning-log: ~</span><span class="term__now">{{ site.data.now.status | default: "공부 기록 중" }}</span></div>
<div class="term__body">
<p class="term__cmd">whoami</p>
<h1 class="mission__title">안녕하세요, 개발 공부 중입니다</h1>
<p class="mission__desc">개발을 처음 배우는 부트캠프 학습자입니다. 매일 배운 내용과 시행착오를 기록해서, 몇 달 뒤 다시 읽었을 때 "그때보다 늘었다"를 확인할 수 있는 블로그로 만들고 있습니다.</p>

<p class="term__cmd">progress --bootcamp</p>
<div class="readout">
<p class="mission__day"><span class="mission__day-value">{{ day_number }}</span><span class="mission__day-unit">일차</span></p>
<dl class="readout__kv">
<div><dt>elapsed</dt><dd>{{ percent }}%</dd></div>
<div><dt>remaining</dt><dd>D-{{ remaining_days }}</dd></div>
<div><dt>total</dt><dd>{{ total_calendar_days }}d</dd></div>
</dl>
</div>
<p class="asciibar" role="img" aria-label="전체 {{ total_calendar_days }}일 중 {{ percent }}% 진행"><span class="asciibar__on">{{ bar_on }}</span><span class="asciibar__off">{{ bar_off }}</span></p>
<p class="mission__ends"><span>2026.08.26 start</span><span>2027.02.16 end</span></p>

<p class="term__cmd">log --this-week</p>
<div class="week-strip"><span class="week-strip__dots">{{ week_dots }}</span><span class="week-strip__count"><b>{{ week_hits }}</b>/5 days</span></div>

<p class="term__cmd">cat goal.txt</p>
<div class="goal info-tile--goal">
<p class="goal__text">하루도 빠짐없이 기록하고, 막혔던 부분은 반드시 다시 정리하기</p>
</div>
<p class="term__cmd term__cmd--idle" aria-hidden="true"><span class="term__cursor"></span></p>
</div>
</section>

{% assign empty_array = "" | split: "," %}
{% assign cloud_all = site.categories.Cloud | default: empty_array %}
{% assign cloud_posts = cloud_all | where_exp: "p", "p.type != 'practice'" %}
{% assign proj_posts = site.categories.Projects | default: empty_array %}
{% assign db_items = site.data.database_links | default: empty_array %}

<div class="section-head section-head--band"><h2>둘러보기</h2><span class="section-head__aside">ls -l ~/</span></div>

<nav class="ls" aria-label="섹션 바로가기">
<a class="ls__row is-cloud" href="{{ '/cloud/' | relative_url }}">
<span class="ls__name">cloud/</span>
<span class="ls__count"><b>{{ cloud_posts.size }}</b> notes</span>
<span class="ls__desc">모듈별 일차 학습노트</span>
<span class="ls__latest">{% if cloud_posts.size > 0 %}{{ cloud_posts.first.date | date: "%m.%d" }} {{ cloud_posts.first.title }}{% endif %}</span>
</a>
<a class="ls__row is-database" href="{{ '/database/' | relative_url }}">
<span class="ls__name">database/</span>
<span class="ls__count"><b>{{ db_items.size }}</b> items</span>
<span class="ls__desc">문제와 풀이, 게임, 수업 자료</span>
<span class="ls__latest">{% if db_items.size > 0 %}{{ db_items.last.title }}{% endif %}</span>
</a>
<a class="ls__row is-projects" href="{{ '/projects/' | relative_url }}">
<span class="ls__name">projects/</span>
<span class="ls__count"><b>{{ proj_posts.size }}</b> posts</span>
<span class="ls__desc">모듈별 결과물 6개와 연동기</span>
<span class="ls__latest">{% if proj_posts.size > 0 %}{{ proj_posts.first.date | date: "%m.%d" }} {{ proj_posts.first.project_name | default: proj_posts.first.title }}{% endif %}</span>
</a>
</nav>

<div class="section-head"><h2>최근에 쓴 글</h2><span class="section-head__aside">최근 5편</span></div>

{% assign recent_posts = site.posts | slice: 0, 5 %}
{% if recent_posts.size > 0 %}
<ul class="row-list">
{% for post in recent_posts %}
{% assign cat = post.categories | first %}
{% assign cat_key = cat | downcase %}
<li><a class="row row--recent is-{{ cat_key }}" href="{{ post.url | relative_url }}">
<span class="row__date">{{ post.date | date: "%Y.%m.%d" }}</span>
<span class="row__main"><span class="row__title">{{ post.title }}</span><span class="row__sub">{{ post.excerpt | strip_html | strip_newlines | truncate: 90 }}</span></span>
<span class="row__aside"><span class="cat-label">{{ cat | default: "글" }}</span></span>
</a></li>
{% endfor %}
</ul>
{% else %}
<p class="empty-note">아직 작성된 글이 없습니다.</p>
{% endif %}

<div class="section-head"><h2>GitHub 잔디</h2><span class="section-head__aside"><a href="https://github.com/paaaraaaeaaa" target="_blank" rel="noopener">github.com/paaaraaaeaaa</a></span></div>

<section class="panel gh-panel">
<div class="gh-panel__item gh-panel__streak">
<h3>streak</h3>
<img src="https://streak-stats.demolab.com?user=paaaraaaeaaa&hide_border=true&background=00000000&stroke=30363D&ring=7EE787&fire=E3B341&currStreakNum=E6EDF3&sideNums=E6EDF3&currStreakLabel=7EE787&sideLabels=A9B4BF&dates=7D8590" data-src-light="https://streak-stats.demolab.com?user=paaaraaaeaaa&hide_border=true&background=00000000&stroke=D8DEE4&ring=1A7F37&fire=9A6700&currStreakNum=1F2328&sideNums=1F2328&currStreakLabel=1A7F37&sideLabels=464E57&dates=656D76" alt="GitHub 연속 기여 통계" loading="lazy" />
</div>
<div class="gh-panel__item">
<h3>contributions</h3>
<div class="gh-panel__chart"><img src="https://ghchart.rshah.org/1A7F37/paaaraaaeaaa" alt="GitHub 기여 캘린더" loading="lazy" /></div>
</div>
</section>


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
  document.addEventListener('DOMContentLoaded', function () {
    var goal = document.querySelector('.info-tile--goal');
    if (!goal) return;
    var wrap = document.createElement('div');
    wrap.className = 'quote-section';
    var p = document.createElement('p');
    p.className = 'daily-quote';
    var en = document.createElement('span');
    en.className = 'daily-quote__en';
    en.textContent = '"' + pick.en + '" — ' + pick.author;
    var ko = document.createElement('span');
    ko.className = 'daily-quote__ko';
    ko.textContent = pick.ko;
    p.appendChild(en); p.appendChild(ko); wrap.appendChild(p); goal.appendChild(wrap);
  });
})();
</script>
