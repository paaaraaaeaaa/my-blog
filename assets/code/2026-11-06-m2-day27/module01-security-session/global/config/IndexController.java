package com.wanted.securitysession.global.config;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    // 메인 페이지 (permitAll : 로그인 없이 접근 가능)
    @GetMapping("/")
    public String root() {
        return "index";
    }

    /* comment.
    *   [방법 1] URL 단위 권한 검사 (SecurityConfig 의 authorizeHttpRequests)
    *   - SecurityConfig 의 규칙이 컨트롤러보다 "먼저" 검사된다.
    *   - 권한이 없으면 이 메서드는 실행조차 되지 않는다.
    *     (미로그인 -> 로그인 페이지로 이동 / 권한 부족 -> 403)
    *  */
    @GetMapping("/admin/page")      // "/admin/*" 규칙 -> ADMIN 만 접근 가능
    public String admin() {
        return "admin/admin";
    }

    @GetMapping("/user/page")       // "/user/*" 규칙 -> USER, ADMIN 접근 가능
    public String user() {
        return "user/user";
    }

    /* comment.
    *   [방법 2] 메서드 단위 권한 검사 (@PreAuthorize)
    *   - "/method/*" 는 SecurityConfig 에 따로 규칙이 없다. -> anyRequest().authenticated() 로 로그인만 하면 통과
    *   - 하지만 핸들러 메서드에 붙은 @PreAuthorize 가 메서드 실행 "직전" 에 권한을 한 번 더 검사한다.
    *   - SecurityConfig 에 @EnableMethodSecurity 가 있어야 동작한다. (SecurityConfig 의 TODO [메서드 권한] 참고)
    *   확인 방법
    *   - USER 로 로그인 후 /method/admin 요청 -> 403 (메서드가 실행되지 않는다.)
    *   - ADMIN 으로 로그인 후 /method/admin 요청 -> 아래 문자열이 화면에 출력된다.
    *   URL 방식 vs 메서드 방식
    *   - URL 방식    : 경로 단위로 한 곳(SecurityConfig)에서 관리할 때 편하다.
    *   - 메서드 방식 : 같은 컨트롤러 안에서도 메서드마다 권한을 다르게 줄 때 편하다.
    *  */
    @GetMapping("/method/admin")
    // TODO @PreAuthorize 로 ADMIN 만 호출 가능하도록 막아보자.
    @ResponseBody
    public String methodAdmin() {
        return "ADMIN 권한 확인 완료! (@PreAuthorize 통과)";
    }

    @GetMapping("/method/user")
    // TODO @PreAuthorize 로 USER, ADMIN 만 호출 가능하도록 막아보자.
    @ResponseBody
    public String methodUser() {
        return "USER 또는 ADMIN 권한 확인 완료! (@PreAuthorize 통과)";
    }
}
