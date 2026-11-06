package com.wanted.securitysession.domain.auth.model.service;

import com.wanted.securitysession.domain.auth.model.dto.AuthDetails;
import com.wanted.securitysession.domain.user.model.dto.LoginUserDTO;
import com.wanted.securitysession.domain.user.model.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/* comment. ⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐
*   [로그인 ③] UserDetailsService
*   - Security 가 로그인 시 "이 아이디의 회원 정보를 가져와줘" 라고 호출하는 인터페이스이다.
*   - loadUserByUsername 을 필수로 구현해야 한다.
*   - 우리는 회원을 "조회" 만 한다. 비밀번호 비교는 Security 가 직접 한다. ([로그인 ⑤])
*  */
@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final MemberService memberService;

    /* comment.
    *   해당 메서드는 SecurityFilter 가 직접 실행하는 메서드이다.
    *   사용자의 ID, PWD 를 전달받아 로그인 작업을 진행한다.
    *   @param username : 로그인 form 의 user 필드에 입력한 아이디
    *   @return UserDetails 구현체(AuthDetails) -> [로그인 ④]
    *   Username -> 입력한 ID
    *  */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // TODO 1. memberService.findByUsername() 으로 회원 조회 (회원이 없으면 null 이 반환된다.)
        LoginUserDTO loginUser = memberService.findByUsername(username);

        // TODO 2. 회원이 없으면 UsernameNotFoundException 을 던진다. -> 로그인 실패 처리로 넘어간다. ([로그인 ⑥-2])
        if (Objects.isNull(loginUser)) {
            throw new UsernameNotFoundException("회원정보가 존재하지 않습니다!");
        }

        // TODO 3. [로그인 ④] 조회한 회원 정보를 AuthDetails 로 감싸서 반환한다.
        /* comment.
        *   return 되는 객체가 곧 Session 에 저장되는 객체!
        * */
        return new AuthDetails(loginUser);
    }
}
