package com.wanted.securitysession.domain.user.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
@Entity
@Table(name = "TBL_LOGIN_LOG")
public class LoginLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @Column(name = "LOGIN_TIME", nullable = false)
    private LocalDateTime loginTime;

    @Column(name = "SUCCESS", nullable = false)
    private boolean success;

    @Column(name = "IP_ADDRESS")
    private String ipAddress;

    /* comment.
    *   [로그인 ⑥] 로그인 로그 생성용 정적 팩토리 메서드
    *   - 성공/실패 핸들러 양쪽에서 사용한다.
    *   - 로그인 시간은 "지금" 이므로 외부에서 받지 않고 여기서 채운다.
    *  */
    public static LoginLog create(String userId, boolean success, String ipAddress) {
        return new LoginLog(null, userId, LocalDateTime.now(), success, ipAddress);
    }
}
