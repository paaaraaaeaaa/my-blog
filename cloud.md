---
title: "Cloud"
layout: single
permalink: /cloud/
classes: section-page
---

{%- comment -%}
  Cloud 섹션. 스타일: assets/css/site.css "9. 공용 컴포넌트" (page-intro, tabs, group, row).
  모듈 이름은 _data/modules.yml 에서 온다.
{%- endcomment -%}

{% assign empty_array = "" | split: "," %}
{% assign cat_posts = site.categories.Cloud | default: empty_array %}
{% assign daily_posts = cat_posts | where_exp: "post", "post.type != 'practice'" %}
{% assign practice_posts = cat_posts | where_exp: "post", "post.type == 'practice'" %}

<header class="page-intro is-cloud">
<span class="cat-label">Cloud</span>
<h1 class="page-intro__title">매일 배운 것을 코드로 남기기</h1>
<p class="page-intro__desc">부트캠프에서 하루하루 부딪힌 문제와 풀어낸 방법을 기록합니다. 일차별 학습노트와 자율 실습 문제풀이로 나눠 정리했습니다.</p>
<p class="page-intro__stats"><span>학습노트 <b>{{ daily_posts.size }}</b>편</span><span>문제풀이 <b>{{ practice_posts.size }}</b>편</span></p>
</header>

{% if cat_posts.size == 0 %}
<p class="empty-note">아직 작성된 글이 없습니다.</p>
{% else %}

<div class="tabs is-cloud" role="tablist">
<button type="button" class="tab is-active" data-tab="daily" role="tab" aria-selected="true" aria-controls="cloud-panel-daily">일차별 학습노트 <span class="tab__count">{{ daily_posts.size }}</span></button>
<button type="button" class="tab" data-tab="practice" role="tab" aria-selected="false" aria-controls="cloud-panel-practice">문제풀이 <span class="tab__count">{{ practice_posts.size }}</span></button>
</div>

<div class="tab-panel" id="cloud-panel-daily" data-panel="daily" role="tabpanel">
{% if daily_posts.size == 0 %}
<p class="empty-note">아직 작성된 글이 없습니다.</p>
{% else %}
{% assign modules = daily_posts | group_by: 'module' | sort: 'name' %}
{% for mod in modules %}
{% assign mod_name = site.data.modules[mod.name] %}
<section class="group is-cloud">
<div class="group__head">
<span class="group__key">{{ mod.name | default: "–" }}</span>
<span class="group__title">{% if mod_name %}{{ mod_name }}{% else %}모듈 {{ mod.name }}{% endif %}</span>
<span class="group__count">{{ mod.items.size }}편</span>
</div>
<ul class="row-list">
{% assign mod_posts = mod.items | sort: 'date' | reverse %}
{% for post in mod_posts %}
<li><a class="row" href="{{ post.url | relative_url }}">
<span class="row__date">{{ post.date | date: "%Y.%m.%d" }}</span>
<span class="row__main"><span class="row__title">{{ post.title }}</span>{% if post.excerpt %}<span class="row__sub">{{ post.excerpt | strip_html | strip_newlines | truncate: 100 }}</span>{% endif %}</span>
<span class="row__aside"></span>
</a></li>
{% endfor %}
</ul>
</section>
{% endfor %}
{% endif %}
</div>

<div class="tab-panel" id="cloud-panel-practice" data-panel="practice" role="tabpanel" hidden>
{% if practice_posts.size == 0 %}
<p class="empty-note">아직 작성된 글이 없습니다.</p>
{% else %}
{% assign practice_posts_by_date = practice_posts | sort: 'date' %}
{% assign topic_names_ordered = practice_posts_by_date | map: 'topic' | uniq %}
{% for name in topic_names_ordered %}
{% assign topic_posts = practice_posts | where_exp: "post", "post.topic == name" | sort: 'date' | sort: 'level_order' %}
{% if topic_posts.size > 0 %}
<section class="group is-cloud">
<div class="group__head">
<span class="group__key">{{ forloop.index }}</span>
<span class="group__title">{% if name %}{{ name }}{% else %}미분류{% endif %}</span>
<span class="group__count">{{ topic_posts.size }}편</span>
</div>
<ul class="row-list">
{% for post in topic_posts %}
<li><a class="row" href="{{ post.url | relative_url }}">
<span class="row__date">{{ post.date | date: "%Y.%m.%d" }}</span>
<span class="row__main"><span class="row__title">{{ post.title }}</span>{% if post.subtitle %}<span class="row__sub">{{ post.subtitle }}</span>{% endif %}</span>
<span class="row__aside"></span>
</a></li>
{% endfor %}
</ul>
</section>
{% endif %}
{% endfor %}
{% endif %}
</div>

<script>
(function () {
  var tabs = document.querySelectorAll('.tabs .tab');
  var panels = document.querySelectorAll('.tab-panel');
  tabs.forEach(function (tab) {
    tab.addEventListener('click', function () {
      var target = tab.getAttribute('data-tab');
      tabs.forEach(function (t) {
        var on = t === tab;
        t.classList.toggle('is-active', on);
        t.setAttribute('aria-selected', on ? 'true' : 'false');
      });
      panels.forEach(function (p) { p.hidden = p.getAttribute('data-panel') !== target; });
    });
  });
})();
</script>

{% endif %}
