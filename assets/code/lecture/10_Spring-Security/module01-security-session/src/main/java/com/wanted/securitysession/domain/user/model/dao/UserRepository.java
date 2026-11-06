package com.wanted.securitysession.domain.user.model.dao;

import com.wanted.securitysession.domain.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // [로그인 ③] 사용자 ID 로 사용자 찾기 (없을 수도 있으므로 Optional)
    Optional<User> findByUserId(String userId);

    // [회원가입 ③] 사용자 ID 가 이미 존재하는지 확인 (중복 ID 체크)
    boolean existsByUserId(String userId);
}
