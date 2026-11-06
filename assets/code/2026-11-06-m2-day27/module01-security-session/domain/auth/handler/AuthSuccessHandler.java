package com.wanted.securitysession.domain.auth.handler;

import com.wanted.securitysession.domain.user.model.dao.LoginLogRepository;
import com.wanted.securitysession.domain.user.model.entity.LoginLog;
import com.wanted.securitysession.domain.user.model.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/* comment.
*   [로그인 ⑥-1] 로그인 성공 핸들러
*   - Security 가 아이디/비밀번호 검사를 통과시킨 "직후" 에 호출된다.
*   - 이 시점에는 이미 세션에 로그인 정보(Authentication)가 저장되어 있다.
*   - 기본 동작(페이지 이동)은 부모 클래스(SimpleUrlAuthenticationSuccessHandler)에 맡기고
*     우리가 필요한 기능만 추가한다.
*   추가 기능
*   1. 로그인 실패 횟수 초기화 (예 : 4번 실패 후 성공하면 0 으로 리셋)
*   2. 로그인 성공 로그 저장
*  */
@Component
@RequiredArgsConstructor
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final MemberService memberService;
    private final LoginLogRepository loginLogRepository;

    /* comment.
    *   @param request        사용자 요청 객체
    *   @param response       서버 응답 객체
    *   @param authentication 인증이 완료된 사용자 정보
    *  */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        // TODO 0. 인증된 사용자의 아이디(username) 가져오기 (authentication.getName())
        String username = authentication.getName(); //ID!!! (+ unique 처리 무조건 해야함)

        /* 1. 로그인 실패 횟수 초기화 */
        // TODO memberService 의 실패 횟수 초기화 메서드 호출
        memberService.resetLoginFailCount(username);

        /* 2. 로그인 성공 로그 저장 */
        // TODO LoginLog.create(아이디, 성공여부, IP 주소) 로 만들어서 저장 (IP 주소 : request.getRemoteAddr())
        LoginLog log = LoginLog.create(
            // RemoteAddr (접속 컴퓨터 IP)
           username, true, request.getRemoteAddr()
        ) ;
        loginLogRepository.save(log);

        /* 3. 페이지 이동 */
        // TODO 부모의 onAuthenticationSuccess() 호출
        //  -> 로그인 전에 가려던 페이지가 있으면 그 페이지로, 없으면 "/" 로 redirect 한다.
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
