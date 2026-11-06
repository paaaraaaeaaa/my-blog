package com.wanted.securitysession.domain.user.model.dto;

import com.wanted.securitysession.domain.user.model.entity.User;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* comment.
*   [로그인 ③] 로그인 시 DB 에서 조회한 회원 정보를 담는 DTO
*   -> [로그인 ④] AuthDetails 안에 담겨서 Security 에 전달된다.
*  */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LoginUserDTO {

    private int userCode;
    private String userId;
    private String userName;
    private String password;
    private String userRole;
    private int loginFailCount;
    private boolean isAccountLocked;

    /* comment.
    *   Entity -> DTO 변환용 정적 팩토리 메서드
    *   (의존 방향 : DTO -> Entity, 자세한 설명은 JPA 미니 프로젝트의 MenuDTO.from 참고)
    *  */
    public static LoginUserDTO from(User user) {
        return new LoginUserDTO(
                user.getUserCode(),
                user.getUserId(),
                user.getUserName(),
                user.getPassword(),
                user.getUserRole(),
                user.getLoginFailCount(),
                user.isAccountLocked()
        );
    }

    /* comment.
    *   권한 문자열을 List 로 변환한다. (AuthDetails.getAuthorities() 에서 사용)
    *   예 : "USER,ADMIN" -> ["USER", "ADMIN"]
    *  */
    public List<String> getRole() {
        if (this.userRole != null && this.userRole.length() > 0) {
            return Arrays.asList(this.userRole.split(","));
        }
        return new ArrayList<>();
    }
}
