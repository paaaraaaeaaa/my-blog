package com.wanted.securitysession.domain.user.model.dao;

import com.wanted.securitysession.domain.user.model.entity.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginLogRepository extends JpaRepository<LoginLog, Long> {

    // 관리자 페이지 : 로그인 로그 전체를 로그인 시간 내림차순(최신순)으로 조회
    List<LoginLog> findAllByOrderByLoginTimeDesc();
}
