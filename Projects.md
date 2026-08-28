---
title: "Projects"
layout: single
permalink: /projects/
comments: true
---

<table style="width: 100%;">
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

<!-- 2. DataTables 스크립트 추가 (검색 및 페이지 나누기 기능) -->
<link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css">
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
<script>
  $(document).ready(function () {
      $('#myTable').DataTable({
          "language": {
              "lengthMenu": "_MENU_ 개씩 보기",
              "search": "검색:",
              "paginate": {
                  "first": "처음",
                  "last": "마지막",
                  "next": "다음",
                  "previous": "이전"
              },
              "info": "총 _TOTAL_개의 글 중 _START_ - _END_ 보여줌",
              "infoEmpty": "글이 없습니다"
          },
          "order": [[ 0, "desc" ]] // 작성일 기준 최신순 정렬
      });
  });
</script>