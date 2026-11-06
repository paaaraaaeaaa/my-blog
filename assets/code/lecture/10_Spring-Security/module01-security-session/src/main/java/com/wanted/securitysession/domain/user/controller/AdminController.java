package com.wanted.securitysession.domain.user.controller;

import com.wanted.securitysession.domain.user.model.dao.LoginLogRepository;
import com.wanted.securitysession.domain.user.model.entity.LoginLog;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final LoginLogRepository loginLogRepository;
    private final SessionRegistry sessionRegistry;

    /* comment.
    *   관리자 로그인 로그 페이지
    *   - /admin/* 경로는 SecurityConfig 에서 ADMIN 권한만 허용했다.
    *     -> 이 메서드가 실행되었다면 이미 "로그인 + ADMIN 권한" 검사를 통과한 상태이다.
    *  */
    @GetMapping("/admin/logs")
    public ModelAndView getLoginLogs(ModelAndView mv) {

        /* 1. 로그인 로그 전체 조회 (최신순) */
        List<LoginLog> logs = loginLogRepository.findAllByOrderByLoginTimeDesc();

        /* comment.
        *   2. 현재 활성 사용자 수 계산
        *   - getAllPrincipals() 는 SessionRegistry 가 알고 있는 사용자 목록을 반환한다.
        *   - 단순히 size() 를 사용하면 이미 로그아웃했거나 세션이 만료된 사용자가 포함될 수 있으므로
        *     각 사용자(principal)가 실제로 만료되지 않은 세션을 가지고 있는지 확인한 뒤 카운트한다.
        *  */
        int activeUserCount = 0;

        for (Object principal : sessionRegistry.getAllPrincipals()) {

            // false 옵션은 만료된 세션을 제외한다.
            List<SessionInformation> sessions = sessionRegistry.getAllSessions(principal, false);

            // 살아있는 세션이 하나라도 있는 사용자만 활성 사용자로 간주한다.
            if (!sessions.isEmpty()) {
                activeUserCount++;
            }
        }

        /* 3. 화면에 데이터 전달 */
        mv.addObject("logs", logs);
        mv.addObject("activeUsers", activeUserCount);
        mv.setViewName("admin/admin");

        return mv;
    }
}
