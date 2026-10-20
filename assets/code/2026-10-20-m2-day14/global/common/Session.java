package com.eulji.global.common;

import com.eulji.member.model.dto.Member;

/* 로그인한 사용자 정보를 담아 모든 Controller가 꺼내 쓴다 */
public class Session {

    private static Member loginMember;

    public static void login(Member member) {
        loginMember = member;
    }

    public static void logout() {
        loginMember = null;
    }

    public static Member getLoginMember() {
        return loginMember;
    }

    public static boolean isLoggedIn() {
        return loginMember != null;
    }

    public static boolean isAdmin() {
        return loginMember != null && "ADMIN".equals(loginMember.getRole());
    }
}
