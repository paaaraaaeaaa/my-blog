package com.wanted.securitysession.domain.auth.config;

import com.wanted.securitysession.domain.auth.handler.AuthFailHandler;
import com.wanted.securitysession.domain.auth.handler.AuthSuccessHandler;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

/* comment.
*   [ 전체 흐름 지도 ] - 각 클래스 주석의 [회원가입 ①], [로그인 ③] 번호를 따라가며 읽어보자.
*   --------
*   ■ 회원가입
*     ① GET  /user/signup  -> UserController.signupPage()   : 회원가입 화면 보여주기
*     ② POST /user/signup  -> UserController.signup()       : 입력값(SignupDTO) 받기
*     ③ MemberService.regist()                             : 비밀번호 암호화(PasswordEncoder) 후 User.create()
*     ④ UserRepository.save()                              : DB 저장
*   ---------
*   ■ 로그인 (POST /auth/login 을 처리하는 컨트롤러는 우리가 만들지 않는다! Security 필터가 가로챈다.)
*     ① GET  /auth/login   -> AuthController.login()        : 로그인 화면 보여주기
*     ② POST /auth/login   -> Security 필터                  : 아래 formLogin 설정대로 user / pass 값을 꺼낸다.
*     ③ AuthService.loadUserByUsername()                   : 아이디로 회원 조회 (MemberService -> UserRepository)
*     ④ AuthDetails 반환                                    : 조회한 회원 정보를 Security 가 이해하는 타입으로 감싼다.
*     ⑤ Security 가 직접 검사                               : 계정 잠금 여부(isAccountNonLocked), 비밀번호 일치(PasswordEncoder.matches)
*     ⑥-1 성공 -> 세션에 인증 정보 저장 -> AuthSuccessHandler : 실패 횟수 초기화 + 성공 로그 저장 -> 페이지 이동
*     ⑥-2 실패 -> AuthFailHandler                           : 실패 횟수 증가(5회 시 잠금) + 실패 로그 저장 -> /auth/fail
*   ----------
*   ■ 로그인 이후 요청 (예: /admin/logs)
*     요청 -> 세션(JSESSIONID)으로 사용자 확인 -> authorizeHttpRequests 권한 검사 -> 통과 시 컨트롤러 실행
*     (+ 핸들러 메서드에 @PreAuthorize 가 있으면 메서드 실행 직전에 한 번 더 권한 검사 -> IndexController 예시)
*   -----------
*   ■ 로그아웃
*     POST /auth/logout -> 세션 무효화 + 쿠키 삭제 -> "/" 로 이동 (이것도 컨트롤러 없이 Security 가 처리)
*  */
@Configuration
@EnableWebSecurity
// TODO [메서드 권한] @EnableMethodSecurity 를 추가해서 핸들러 메서드의 @PreAuthorize 가 동작하도록 해보자.
public class SecurityConfig {

    /* comment.
    *   비밀번호를 인코딩하기 위한 Bean  ([회원가입 ③] 암호화, [로그인 ⑤] 비교 에서 사용)
    *   Bcrypt 는 비밀번호 해싱에 가장 많이 사용되는 알고리즘 중 하나이다.
    *   사용 이유
    *   1. 보안성 : 해시 함수에 무작위 솔트를 적용하여 생성한다.
    *   2. 비용 증가 : 매개변수에 값을 주면 암호 생성 시간을 조절할 수 있어 무차별 공격을 어렵게 한다.
    *   3. 호환성 : 높은 보안 수준 및 데이터베이스에 저장하기 쉬운 특징
    *   4. 알고리즘 신뢰성 : 보안에 대한 검증과 평가를 거친 알고리즘으로 문제없이 계속 사용 중
    *  */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /* comment.
    *   - 세션이 생성될 때 : HttpSessionEventPublisher 가 SessionRegistry 에 세션을 등록
    *   - 세션이 소멸될 때 : HttpSessionEventPublisher 가 SessionRegistry 에서 세션을 제거
    *   이를 통해 SessionRegistry 는 현재 활성화된 세션 목록을 정확하게 유지할 수 있다.
    *   (AdminController 에서 활성 사용자 수를 계산할 때 사용)
    *  */
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    // 정적 리소스(static 하위 css, js, image 등)에 대한 요청은 Security 검사에서 제외한다.
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    /* comment. ⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐
    *   SecurityFilterChain : 모든 요청이 컨트롤러에 도착하기 "전에" 거치는 보안 규칙 모음
    *   - AuthSuccessHandler, AuthFailHandler 는 @Component 로 등록된 Bean 이므로
    *     매개변수로 선언만 하면 Spring 이 주입해준다.
    *  */
    @Bean
    public SecurityFilterChain configure(HttpSecurity http,
                                         // 로그인 실패 시 동작하는 메서드의 집합 클래스
                                         AuthFailHandler authFailHandler,
                                         // 로그인 성공 시 동작하는 메서드의 집합 클래스
                                         AuthSuccessHandler authSuccessHandler,
                                         SessionRegistry sessionRegistry) throws Exception {

        /* 1. 요청 별 접근 권한 설정 (위에서부터 순서대로 검사한다) */
        // TODO http.authorizeHttpRequests(auth -> { ... })
        //  - "/auth/login", "/user/signup", "/auth/fail", "/" : 누구나 접근 가능 (permitAll)
        //  - "/admin/*" : ADMIN 만 (hasAnyAuthority)
        //  - "/user/*"  : USER, ADMIN
        //  - 나머지 요청 : 로그인만 하면 가능 (anyRequest().authenticated())
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/auth/login", "/user/signup", "/auth/fail", "/").permitAll(); // 누구나 접근 가능
            auth.requestMatchers("/admin/*").hasAnyAuthority("ADMIN");                         // ADMIN 만
            auth.requestMatchers("/user/*").hasAnyAuthority("USER", "ADMIN");                  // USER, ADMIN
            // authenticated() -> 이미 로그인(인증) 이 된 사람만 가능
            auth.anyRequest().authenticated();                                                 // 나머지는 로그인만 하면 가능
        })

        /* 2. 로그인 설정 ([로그인 ②] ~ [로그인 ⑥]) */
        // TODO .formLogin(login -> { ... })
        //  - 로그인 페이지 경로 : "/auth/login"
        //  - 아이디 / 비밀번호 파라미터 이름 : login.html 의 input name 확인 ("user", "pass")
        //  - 성공 핸들러 : authSuccessHandler / 실패 핸들러 : authFailHandler
        .formLogin(login -> {
            login.loginPage("/auth/login");         // 로그인 페이지 GET 요청 경로 (AuthController 에 핸들러 메서드가 있어야 한다.)
            login.usernameParameter("user");          // 사용자 id 입력 필드 (input 의 name 과 일치) - index.html
            login.passwordParameter("pass");          // 사용자 pass 입력 필드 (input 의 name 과 일치) - index.html
            login.successHandler(authSuccessHandler); // [로그인 ⑥-1] 성공 시 실행
            login.failureHandler(authFailHandler);    // [로그인 ⑥-2] 실패 시 실행
        })

        /* 3. Remember Me 설정 (브라우저를 껐다 켜도 로그인 유지) */
        // TODO .rememberMe(rememberMe -> { ... })
        //  - 파라미터 이름 : "remember-me" (login.html 체크박스 name)
        //  - 유효 기간 : 24시간 (초 단위) / 토큰 생성 key : 아무 문자열
        .rememberMe(rememberMe -> {
            rememberMe.rememberMeParameter("remember-me"); // 체크박스 name 속성과 일치
            rememberMe.tokenValiditySeconds(86400);        // 토큰 유효 기간 : 24시간 (초 단위)
            rememberMe.key("remember-me-secret-key");      // Remember Me 토큰 생성에 사용되는 Key
        })

        /* 4. 로그아웃 설정 */
        // TODO .logout(logout -> { ... })
        //  - 로그아웃 URL : "/auth/logout"
        //  - 삭제할 쿠키 : "JSESSIONID", "remember-me"
        //  - 세션 소멸 허용 / 로그아웃 후 이동할 페이지 : "/"
        .logout(logout -> {
            logout.logoutUrl("/auth/logout");      // 로그아웃 처리 URL -> 직접 작성! security 제공 아님
            logout.deleteCookies("JSESSIONID");    // 세션 쿠키 삭제
            logout.deleteCookies("remember-me");   // Remember Me 쿠키 삭제
            logout.invalidateHttpSession(true);    // 세션 소멸
            logout.logoutSuccessUrl("/");          // 로그아웃 후 이동할 페이지
        })

        /* 5. 세션 설정 */
        // TODO .sessionManagement(session -> { ... })
        //  - 한 계정 당 동시 접속 세션 1개로 제한 (+ 매개변수로 받은 sessionRegistry 연결)
        //  - 세션 만료 시 이동할 페이지 : "/"
        .sessionManagement(session -> {
            session.maximumSessions(1).sessionRegistry(sessionRegistry); // 한 계정 당 동시 접속 세션 1개로 제한
            session.invalidSessionUrl("/");                              // 세션 만료 시 이동할 페이지
        })

        /* comment.
        *   6. CSRF(Cross-Site Request Forgery) 보호 설정
        *   CSRF 란?
        *   - 공격자가 사용자의 권한을 이용하여 악의적인 요청을 전송하는 공격
        *   - 예 : 사용자가 로그인한 상태에서 공격자의 악성 사이트 방문 시,
        *          자동으로 은행 송금 요청이 실행되는 경우
        *   이번 수업에서는 학습 목적으로 비활성화한다. (프로덕션 환경에서는 반드시 활성화!)
        *   - 세션 기반 인증 사용 시 -> CSRF 보호 필수
        *   - JWT 토큰 기반 인증 시 -> CSRF 보호 불필요 (Stateless)
        *  */
         .csrf(csrf -> csrf.disable());

        return http.build();
    }
}
