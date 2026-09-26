/* =====================================================================
   [my-blog] 검색 엔진 (테마의 lunr 검색을 대체)
   - 한글은 단어 중간 일치가 중요해서 lunr 대신 부분 문자열 검색을 쓴다
   - 결과를 전체 / Cloud / Database / Projects 탭으로 나눈다
   - data-search="검색어" 속성이 있는 버튼을 누르면 검색창이 열리며 바로 검색된다
   데이터: assets/js/lunr/lunr-store.js · 스타일: site.css "15. 검색"
   ===================================================================== */
(function () {
  var TABS = [
    { key: 'all', label: '전체' },
    { key: 'cloud', label: 'Cloud' },
    { key: 'database', label: 'Database' },
    { key: 'projects', label: 'Projects' }
  ];
  var PREVIEW = 3;               // 전체 탭에서 섹션별로 먼저 보여줄 개수
  var state = { q: '', tab: 'all', results: [] };

  function norm(s) { return (s || '').toString().toLowerCase(); }
  function esc(s) {
    return (s || '').toString().replace(/[&<>"']/g, function (c) {
      return { '&': '&amp;', '<': '&lt;', '>': '&gt;', '"': '&quot;', "'": '&#39;' }[c];
    });
  }
  function reEsc(s) { return s.replace(/[.*+?^${}()|[\]\\]/g, '\\$&'); }

  (window.store || []).forEach(function (d) {
    d._t = norm(d.t); d._e = norm(d.e); d._b = norm(d.b); d._g = norm((d.g || []).join(' '));
  });

  function search(q) {
    var terms = norm(q).split(/\s+/).filter(Boolean);
    if (!terms.length) return [];
    var out = [];
    (window.store || []).forEach(function (d) {
      var score = 0;
      for (var i = 0; i < terms.length; i++) {
        var t = terms[i], s = 0;
        if (d._t.indexOf(t) > -1) s += 12;
        if (d._g.indexOf(t) > -1) s += 7;
        if (d._e.indexOf(t) > -1) s += 4;
        if (d._b.indexOf(t) > -1) s += 1 + Math.min(d._b.split(t).length - 2, 4) * 0.5;
        if (!s) return;                       // 모든 단어가 들어 있어야 결과로 인정 (AND)
        score += s;
      }
      out.push({ d: d, score: score, terms: terms });
    });
    out.sort(function (a, b) { return b.score - a.score || (b.d.d > a.d.d ? 1 : -1); });
    return out;
  }

  function highlight(text, terms) {
    var html = esc(text);
    terms.forEach(function (t) {
      html = html.replace(new RegExp('(' + reEsc(esc(t)) + ')', 'gi'), '<mark>$1</mark>');
    });
    return html;
  }

  function snippet(d, terms) {
    var src = d.b || d.e || '';
    var low = norm(src), pos = -1;
    for (var i = 0; i < terms.length && pos < 0; i++) pos = low.indexOf(terms[i]);
    if (pos < 0) return highlight((d.e || src).slice(0, 110), terms);
    var start = Math.max(0, pos - 40);
    var text = (start > 0 ? '…' : '') + src.slice(start, pos + 90) + (pos + 90 < src.length ? '…' : '');
    return highlight(text, terms);
  }

  function item(r) {
    var d = r.d;
    var meta = [];
    if (d.d) meta.push('<span>' + esc(d.d) + '</span>');
    (d.g || []).slice(0, 3).forEach(function (g) { meta.push('<span class="sr-item__tag">#' + esc(g) + '</span>'); });
    return '<a class="sr-item is-' + d.s + '" href="' + esc(d.u) + '"' + (d.x ? ' target="_blank" rel="noopener"' : '') + '>' +
      '<span class="sr-item__kind">' + esc(d.k) + '</span>' +
      '<span class="sr-item__main">' +
        '<span class="sr-item__title">' + highlight(d.t, r.terms) + (d.x ? ' <span class="sr-item__ext">새 탭</span>' : '') + '</span>' +
        '<span class="sr-item__snip">' + snippet(d, r.terms) + '</span>' +
        (meta.length ? '<span class="sr-item__meta">' + meta.join('') + '</span>' : '') +
      '</span></a>';
  }

  function counts(results) {
    var c = { all: results.length, cloud: 0, database: 0, projects: 0 };
    results.forEach(function (r) { if (c[r.d.s] !== undefined) c[r.d.s]++; });
    return c;
  }

  function popularTags() {
    var n = {};
    (window.store || []).forEach(function (d) { if (d.x) return; (d.g || []).forEach(function (g) { n[g] = (n[g] || 0) + 1; }); });
    return Object.keys(n).sort(function (a, b) { return n[b] - n[a]; }).slice(0, 12);
  }

  function render() {
    var box = document.getElementById('results');
    if (!box) return;
    if (!state.q.trim()) {
      box.innerHTML =
        '<div class="sr-empty">' +
          '<p class="sr-empty__title">무엇을 찾고 있나요?</p>' +
          '<p class="sr-empty__desc">제목, 태그, 본문에서 한 번에 찾아요. 여러 단어를 띄어 쓰면 모두 들어간 글만 보여줘요.</p>' +
          '<div class="sr-empty__tags">' + popularTags().map(function (t) {
            return '<button type="button" class="chip chip--btn" data-q="' + esc(t) + '">' + esc(t) + '</button>';
          }).join('') + '</div>' +
        '</div>';
      return;
    }
    var c = counts(state.results);
    var tabs = '<div class="sr-tabs" role="tablist">' + TABS.map(function (t) {
      return '<button type="button" role="tab" class="sr-tab is-' + t.key + (state.tab === t.key ? ' is-active' : '') +
        '" data-tab="' + t.key + '" aria-selected="' + (state.tab === t.key) + '"' + (c[t.key] ? '' : ' disabled') + '>' +
        t.label + '<b>' + c[t.key] + '</b></button>';
    }).join('') + '</div>';

    var body = '';
    if (!state.results.length) {
      body = '<div class="sr-none"><p><b>“' + esc(state.q) + '”</b>에 맞는 결과가 없어요.</p><p>단어를 줄이거나 다른 표현으로 찾아보세요.</p></div>';
    } else if (state.tab === 'all') {
      TABS.slice(1).forEach(function (t) {
        var list = state.results.filter(function (r) { return r.d.s === t.key; });
        if (!list.length) return;
        body += '<section class="sr-group is-' + t.key + '">' +
          '<div class="sr-group__head"><span class="cat-label">' + t.label + '</span><span class="sr-group__count">' + list.length + '개</span>' +
          (list.length > PREVIEW ? '<button type="button" class="sr-more" data-tab="' + t.key + '">' + t.label + ' 결과 모두 보기</button>' : '') +
          '</div>' + list.slice(0, PREVIEW).map(item).join('') + '</section>';
      });
    } else {
      var list = state.results.filter(function (r) { return r.d.s === state.tab; });
      body = '<section class="sr-group is-' + state.tab + '">' + list.map(item).join('') + '</section>';
    }
    box.innerHTML = tabs + '<div class="sr-body">' + body + '</div>';
  }

  function run(q) {
    state.q = q;
    state.results = search(q);
    var c = counts(state.results);
    if (state.tab !== 'all' && !c[state.tab]) state.tab = 'all';
    render();
  }

  document.addEventListener('DOMContentLoaded', function () {
    var input = document.getElementById('search');
    var box = document.getElementById('results');
    if (!input || !box) return;
    input.setAttribute('placeholder', '검색어를 입력하세요');
    input.setAttribute('autocomplete', 'off');
    var timer;
    input.addEventListener('input', function () {
      clearTimeout(timer);
      timer = setTimeout(function () { run(input.value); }, 80);
    });
    input.addEventListener('keydown', function (e) {
      if (e.key === 'Enter') {
        var first = box.querySelector('.sr-item');
        if (first) first.click();
      }
    });
    box.addEventListener('click', function (e) {
      var tab = e.target.closest('[data-tab]');
      if (tab) { state.tab = tab.getAttribute('data-tab'); render(); input.focus(); return; }
      var q = e.target.closest('[data-q]');
      if (q) { input.value = q.getAttribute('data-q'); run(input.value); input.focus(); }
    });
    // 페이지 어디서든 data-search 버튼 → 검색창 열고 바로 검색
    document.addEventListener('click', function (e) {
      var trigger = e.target.closest('[data-search]');
      if (!trigger) return;
      var overlay = document.querySelector('.search-content');
      if (overlay && !overlay.classList.contains('is--visible')) {
        var toggle = document.querySelector('.search__toggle');
        if (toggle) toggle.click();
      }
      input.value = trigger.getAttribute('data-search');
      state.tab = 'all';
      run(input.value);
      window.scrollTo(0, 0);
    });
    render();
  });
})();
