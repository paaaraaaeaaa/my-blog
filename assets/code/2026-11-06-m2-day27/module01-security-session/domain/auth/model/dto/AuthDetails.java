package com.wanted.securitysession.domain.auth.model.dto;

import com.wanted.securitysession.domain.user.model.dto.LoginUserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/* comment. ⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐
*   [로그인 ④] UserDetails 구현체
*   - AuthService.loadUserByUsername() 이 반환하는 타입이다.
*   - 우리가 만든 LoginUserDTO 를 Security 가 이해할 수 있는 형태(UserDetails)로 감싼다.
*   - 로그인 성공 시 이 객체가 세션에 저장되어 이후 요청에서 "로그인한 사용자" 로 사용된다.
*   (약간 헤더, 게시글 작성자 등등 고정값을 구현할 때 사용함)
*   [로그인 ⑤] Security 는 아래 메서드들을 호출해서 로그인 가능 여부를 직접 판단한다.
*   - getPassword()         : 입력한 비밀번호와 비교할 DB 비밀번호
*   - isAccountNonLocked()  : 잠긴 계정인지
*   - getAuthorities()      : 권한 검사(hasAnyAuthority) 에 사용할 권한 목록
*  */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthDetails implements UserDetails {

    /* comment.
    *   해당 클래스는 Session 저장소에 저장될 사용자의 정보를 정의하는 클래스이다.
    *   해당 클래스에 정의한 정보를 토대로 화면에서 사용자 정보를 꺼내올 수 있게 된다.
    * */

    private LoginUserDTO loginUserDTO;

    /* comment.
    *   권한 정보를 반환하는 메서드이다.
    *   SecurityConfig 의 hasAnyAuthority("ADMIN") 같은 권한 검사에 사용된다.
    *  */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        // TODO loginUserDTO.getRole() 의 권한 문자열들을 GrantedAuthority 로 바꿔 Collection 에 담아 반환한다.
        //  (힌트 : GrantedAuthority 는 메서드가 1개인 인터페이스 -> 람다 () -> role 로 만들 수 있다.)
        // 해당 메서드의 반환타입이 Collection 인 이유는 다중 권한 때문이다.

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        loginUserDTO.getRole().forEach(role -> authorities.add(() -> role));

        return authorities;
    }

    // 사용자의 비밀번호를 반환한다. 사용자가 입력한 비밀번호와 비교할 때 사용된다.
    @Override
    public String getPassword() {
        // TODO loginUserDTO 의 비밀번호 반환
        return loginUserDTO.getPassword();
    }

    // 사용자의 아이디를 반환한다.
    @Override
    public String getUsername() {
        // TODO loginUserDTO 의 아이디(userId) 반환
        return loginUserDTO.getUserId();
    }

    /* comment.
    *   아래 4개의 메서드는 false 를 반환하면 해당 계정을 사용할 수 없다.
    *   - isAccountNonExpired     : 계정 만료 여부
    *   - isAccountNonLocked      : 계정 잠금 여부 (비밀번호 반복 실패, 오랜 기간 비접속 휴면 처리 등)
    *   - isCredentialsNonExpired : 비밀번호 만료 여부
    *   - isEnabled               : 계정 활성화 여부 (탈퇴, 삭제 처리 등)
    *   이번 수업에서는 "계정 잠금" 만 실제 값으로 판단하고 나머지는 true 로 둔다.
    *  */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO DB 의 잠금 여부(loginUserDTO.isAccountLocked()) 를 이용해서 반환 -> 잠긴 계정이면 false
        return !loginUserDTO.isAccountLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /* comment.
    *   equals() / hashCode() 오버라이딩
    *   - Spring Security 의 SessionRegistry 는 내부적으로 Map 형태로 사용자를 관리한다.
    *   - 같은 사용자라도 로그인할 때마다 새로운 AuthDetails 객체가 생성되므로
    *     기본 Object.equals()(주소값 비교) 를 쓰면 서로 다른 사용자로 인식된다.
    *     -> 동시 접속 제한(maximumSessions), 활성 사용자 수 계산이 틀어진다.
    *   - equals() 와 hashCode() 는 반드시 "같은 기준" 으로 함께 오버라이딩해야 한다.
    *  */
    // TODO equals(Object o) 오버라이딩 : userId(getUsername()) 가 같으면 같은 사용자
    // TODO hashCode() 오버라이딩 : userId(getUsername()) 기준으로 생성 (Objects.hash 활용)
}
