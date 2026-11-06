package com.wanted.securitysession.domain.auth.handler;

import com.wanted.securitysession.domain.user.model.dao.LoginLogRepository;
import com.wanted.securitysession.domain.user.model.entity.LoginLog;
import com.wanted.securitysession.domain.user.model.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;

/* comment.
*   [로그인 ⑥-2] 로그인 실패 핸들러
*   - [로그인 ③] ~ [로그인 ⑤] 중 어디서든 문제가 생기면 호출된다.
*   - 실패 원인은 매개변수 exception 의 "타입" 으로 구분할 수 있다.
*   패키지 구조
*   AuthenticationFailureHandler(interface) -> SimpleUrlAuthenticationFailureHandler(class) -> AuthFailHandler
*   - 인터페이스를 직접 구현하는 대신, 이미 구현되어 있는 SimpleUrlAuthenticationFailureHandler 를 상속받아
*     응답 메시지와 이동할 페이지 경로만 재정의한다.
*   - 이동할 페이지 경로는 setDefaultFailureUrl("경로") 메서드로 설정한다.
*   추가 기능
*   1. 로그인 실패 횟수 증가 (5회 이상이면 계정 잠금)
*   2. 로그인 실패 로그 저장
*  */
@Component
@RequiredArgsConstructor
public class AuthFailHandler extends SimpleUrlAuthenticationFailureHandler {

    private final MemberService memberService;
    private final LoginLogRepository loginLogRepository;

    /* comment.
    *   @param request   사용자 요청 객체
    *   @param response  서버 응답 객체
    *   @param exception 발생한 오류를 담는 객체
    *  */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        // TODO 0. 로그인에 실패했으므로 인증 객체가 없다. -> 사용자가 form 에 입력한 user 값을 request 에서 꺼낸다.
        String errorMessage; // 실패 시 전달할 변수 선언.
        // 인증 객체가 없기 때문에 request 에서 id 꺼내오기
        String username = request.getParameter("user");

        /* 1. 실패 원인 별 메시지 만들기 (+ 비밀번호 오류 시 실패 횟수 증가) */
        // TODO exception 의 타입(instanceof) 별로 errorMessage 를 다르게 만든다.
        //  - BadCredentialsException                    : 아이디가 없거나 비밀번호 불일치 -> 실패 횟수 증가(memberService)
        //  - LockedException                            : 계정 잠금 (AuthDetails.isAccountNonLocked() 가 false)
        //  - InternalAuthenticationServiceException     : 서버에서 사용자 정보 검증 중 오류
        //  - UsernameNotFoundException                  : 존재하지 않는 아이디 (AuthService 에서 던진 예외)
        //  - AuthenticationCredentialsNotFoundException : 인증 정보 없이 보안 리소스에 접근
        //  - 그 외                                       : 알 수 없는 오류

        /* 1. 실패 원인 별 메시지 만들기 (+ 비밀번호 오류 시 실패 횟수 증가) */
        if (exception instanceof BadCredentialsException) {
            // BadCredentialsException 오류는 사용자의 아이디가 DB 에 존재하지 않는 경우,
            // 또는 비밀번호가 맞지 않는 경우 발생한다.
            errorMessage = "아이디가 존재하지 않거나 비밀번호가 일치하지 않습니다.";

            // 로그인 실패 횟수 증가
            if (username != null && !username.isEmpty()) {
                memberService.incrementLoginFailCount(username);
                errorMessage += " (실패 횟수: 누적)";
            }
        } else if (exception instanceof LockedException) {
            // 계정이 잠긴 상태 (AuthDetails.isAccountNonLocked() 가 false -> 5회 이상 로그인 실패)
            errorMessage = "계정이 잠겨있습니다. 관리자에게 문의하세요.";
        } else if (exception instanceof InternalAuthenticationServiceException) {
            // 서버에서 사용자 정보를 검증하는 과정에서 발생하는 에러이다.
            errorMessage = "서버에서 오류가 발생되었습니다.";
        } else if (exception instanceof UsernameNotFoundException) {
            // DB 에 사용자의 정보가 없는 경우 발생하는 오류이다. (AuthService 에서 던진 예외)
            errorMessage = "존재하지 않는 아이디입니다.";
        } else if (exception instanceof AuthenticationCredentialsNotFoundException) {
            // 보안 컨텍스트에 인증 객체가 존재하지 않거나 인증 정보가 없는 상태에서
            // 보안처리된 리소스에 접근하는 경우 발생한다.
            errorMessage = "인증 요청이 거부되었습니다.";
        } else {
            errorMessage = "알 수 없는 오류로 로그인 요청을 처리할 수 없습니다.";
        }

        /* 2. 로그인 실패 로그 저장 */
        // TODO LoginLog.create(아이디, 성공여부, IP 주소) 로 만들어서 저장
        loginLogRepository.save(LoginLog.create(username, false, request.getRemoteAddr()));

        /* 3. 실패 안내 페이지로 이동 -> AuthController.loginFail() */
        // TODO URL 에 한글을 그대로 넣을 수 없으므로 URLEncoder.encode(errorMessage, "UTF-8") 로 인코딩
        // TODO setDefaultFailureUrl("/auth/fail?message=" + 인코딩된 메시지)
        // TODO 부모의 onAuthenticationFailure() 를 호출하여 위에서 설정한 경로로 이동시킨다.
        errorMessage = URLEncoder.encode(errorMessage, "UTF-8");

        setDefaultFailureUrl("/auth/fail?message=" + errorMessage);

        // 부모의 메서드를 호출하여 위에서 설정한 경로로 이동시킨다.
        super.onAuthenticationFailure(request, response, exception);
    }
}
