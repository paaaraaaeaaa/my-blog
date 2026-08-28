---
title: "Projects"
layout: single
permalink: /projects/
---

<table>
  <thead>
    <tr>
      <th style="text-align:center; width:25%;">작성일</th>
      <th style="text-align:center;">제목</th>
    </tr>
  </thead>
  <tbody>
    {% for post in site.categories.Projects %}
    <tr>
      <td style="text-align:center;">{{ post.date | date: "%Y-%m-%d" }}</td>
      <td><a href="{{ post.url | relative_url }}">{{ post.title }}</a></td>
    </tr>
    {% endfor %}
  </tbody>
</table>