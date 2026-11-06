package com.wanted.securitysession.domain.user.model.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
/* comment.
*   전체 필드 생성자를 private 으로 막아두었다.
*   외부에서는 아래의 정적 팩토리 메서드 User.create(...) 로만 User 를 만들 수 있다.
*  */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
@Entity
@Table(
    name = "TBL_USER",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_user_id", columnNames = "USER_ID")
    }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_NO")
    private Integer userCode;

    @Column(name = "USER_ID", nullable = false, length = 30)
    private String userId;

    @Column(name = "USER_NAME", length = 30)
    private String userName;

    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password;

    @Column(name = "USER_ROLE", nullable = false, length = 50)
    private String userRole;

    @Column(name = "LOGIN_FAIL_COUNT", nullable = false)
    private int loginFailCount;

    @Column(name = "IS_ACCOUNT_LOCKED", nullable = false)
    private boolean isAccountLocked;

    /* comment.
    *   [회원가입 ③] 신규 회원 생성용 정적 팩토리 메서드
    *   - password 는 반드시 "암호화 된 값" 을 전달 받는다. (MemberService 에서 PasswordEncoder 로 암호화)
    *   - userCode 는 DB 가 만들어주는 값(IDENTITY)이므로 받지 않는다.
    *   - 신규 회원은 실패 횟수 0, 잠금 해제 상태로 시작한다.
    *  */
    public static User create(String userId, String userName, String encodedPassword, String userRole) {
        return new User(null, userId, userName, encodedPassword, userRole, 0, false);
    }

    /* comment.
    *   [로그인 ⑥-2] 로그인 실패 시 호출되는 도메인 메서드
    *   - 실패 횟수를 1 증가시키고, 5회 이상이면 계정을 잠근다.
    *   - 영속 상태의 Entity 값만 바꾸면 트랜잭션 커밋 시
    *     변경 감지(dirty checking)로 UPDATE 가 실행된다. (save 호출 불필요)
    *  */
    public void increaseLoginFailCount() {
        this.loginFailCount++;

        if (this.loginFailCount >= 5) {
            this.isAccountLocked = true;
        }
    }

    // [로그인 ⑥-1] 로그인 성공 시 실패 횟수 초기화
    public void resetLoginFailCount() {
        this.loginFailCount = 0;
    }
}
