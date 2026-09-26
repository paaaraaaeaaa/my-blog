---
name: resource
description: 게임, 아티팩트, 치트시트 같은 자료 링크를 Database 페이지에 추가한다. 글을 쓰지 않고 데이터 파일만 고친다. /resource <링크> 라고 하면 실행한다.
---

# Database 자료 추가하기

사용자가 링크를 준다: $ARGUMENTS
문제 링크(풀이를 쓸 것)는 이 스킬이 아니라 `/practice`로 안내한다.

## 순서

1. 링크를 열어 제목과 무엇인지 파악한다. 못 열면 제목과 한 줄 설명을 물어본다.
2. 어느 섹션인지 정한다. `_data/database_sections.yml`의 `name` 중에서 고르고, 애매하면 후보 두 개를 주고 물어본다.
3. `_data/database_links.yml`에 추가한다. 같은 `category`끼리 모이도록 **그 섹션의 마지막 항목 뒤**에 넣는다.

   ```yaml
   - category: "게임"
     title: "<자료 이름>"
     url: "https://..."
     description: "<이게 무엇이고 언제 쓰는지 한 문장. '링크를 클릭하면…' 같은 안내 문구는 쓰지 않는다>"
   ```
   - 레포에 올린 파일이면 `url` 대신 `file: /assets/files/<파일명>` (파일은 `assets/files/`에 둔다).
4. **새 섹션**이 필요하면 `_data/database_sections.yml`에도 한 줄 추가한다.

   ```yaml
   - name: "치트시트"
     emoji: "📌"
     tone: "cloud"      # cloud | database | projects | signal | success | warning | danger
     desc: "자주 찾아보는 명령어·문법 정리"
   ```
   기존 섹션과 `tone`이 겹치지 않게 고른다.
5. 바뀐 부분(diff)만 보여준다. 내가 좋다고 하기 전에는 커밋하지 마.
6. 내가 확인하면 커밋하고 push 한다. 커밋 메시지: `data: <섹션> — <자료 이름> 추가`
