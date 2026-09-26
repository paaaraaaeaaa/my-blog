---
layout: none
---
{%- comment -%}
  [my-blog] 테마의 검색 데이터 파일을 덮어쓴다.
  s(섹션): cloud | database | projects — 검색 결과 탭 분류
  k(종류): 학습노트 | 풀이 | 결과물 | 연동기 | 자료 카테고리명
  문제풀이 글(Cloud, type: practice)은 Database 페이지에 모이므로 database로 분류한다.
{%- endcomment -%}
var store = [
{%- for doc in site.posts -%}
{%- assign cat = doc.categories | first -%}
{%- if cat == "Cloud" and doc.type == "practice" -%}{%- assign sec = "database" -%}{%- assign kind = "풀이" -%}
{%- elsif cat == "Cloud" -%}{%- assign sec = "cloud" -%}{%- assign kind = "학습노트" -%}
{%- elsif cat == "Projects" and doc.type == "practice" -%}{%- assign sec = "projects" -%}{%- assign kind = "연동기" -%}
{%- elsif cat == "Projects" -%}{%- assign sec = "projects" -%}{%- assign kind = "결과물" -%}
{%- else -%}{%- assign sec = "cloud" -%}{%- assign kind = "글" -%}{%- endif -%}
{"t":{{ doc.title | jsonify }},"e":{{ doc.excerpt | strip_html | strip_newlines | jsonify }},"b":{{ doc.content | replace: "</p>", " " | replace: "</li>", " " | replace: "</h2>", " " | replace: "</h3>", " " | strip_html | strip_newlines | truncate: 6000 | jsonify }},"g":{{ doc.tags | jsonify }},"u":{{ doc.url | relative_url | jsonify }},"d":{{ doc.date | date: "%Y.%m.%d" | jsonify }},"s":"{{ sec }}","k":"{{ kind }}"},
{%- endfor -%}
{%- for item in site.data.database_links -%}
{%- if item.url -%}{%- assign u = item.url -%}{%- else -%}{%- assign u = item.file | relative_url -%}{%- endif -%}
{"t":{{ item.title | jsonify }},"e":{{ item.description | default: "" | jsonify }},"b":{{ item.topics | join: " " | jsonify }},"g":[{{ item.category | jsonify }}],"u":{{ u | jsonify }},"d":"","s":"database","k":{{ item.category | jsonify }},"x":true}{%- unless forloop.last -%},{%- endunless -%}
{%- endfor -%}
];
