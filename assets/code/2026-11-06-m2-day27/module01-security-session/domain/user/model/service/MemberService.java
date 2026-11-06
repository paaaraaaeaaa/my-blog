package com.wanted.securitysession.domain.user.model.service;

import com.wanted.securitysession.domain.user.model.dao.UserRepository;
import com.wanted.securitysession.domain.user.model.dto.LoginUserDTO;
import com.wanted.securitysession.domain.user.model.dto.SignupDTO;
import com.wanted.securitysession.domain.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final UserRepository userRepository;
    // SecurityConfig 에 Bean 으로 등록한 BCryptPasswordEncoder 가 주입된다.
    private final PasswordEncoder encoder;

    /* comment.
    *   Entity <-> DTO 변환 방식 : 정적 팩토리 메서드 (ModelMapper 제거)
    *   - Entity -> DTO : LoginUserDTO.from(user)     (DTO 클래스에 위치)
    *   - DTO -> Entity : User.create(값, 값, ...)     (Entity 클래스에 위치)
    *  */

    /* 1. [회원가입 ③] 회원가입 */
    @Transactional
    public Integer regist(SignupDTO signupDTO) {

        // 중복 아이디 체크 -> 중복이면 null 반환
        if (userRepository.existsByUserId(signupDTO.getUserId())) {
            return null;
        }

        try {
            // 비밀번호는 절대 그대로 저장하지 않는다! 암호화 된 값으로 Entity 를 만든다.
            User user = User.create(
                    signupDTO.getUserId(),
                    signupDTO.getUserName(),
                    // encode(암호화 필요한 값) 메서드는 Bcrypt 알고리즘을 적용해준다.
                    encoder.encode(signupDTO.getUserPass()),
                    signupDTO.getRole()
            );

            // [회원가입 ④] 저장 후 생성된 사용자 코드 반환
            User savedUser = userRepository.save(user);
            return savedUser.getUserCode();

        } catch (Exception e) {
            e.printStackTrace();
            return 0; // 서버 오류를 0 으로 표시
        }
    }

    /* comment.
    *   2. [로그인 ③] 아이디로 회원 조회 (AuthService 에서 호출)
    *   - 회원이 없으면 null 을 반환한다. -> AuthService 에서 UsernameNotFoundException 발생
    *   - LoginUserDTO::from 은 user -> LoginUserDTO.from(user) 를 줄여 쓴 "메서드 참조" 이다.
    *  */
    public LoginUserDTO findByUsername(String username) {

        return userRepository.findByUserId(username)
                             .map(LoginUserDTO::from)
                             .orElse(null);
    }

    /* comment.
    *   3. [로그인 ⑥-2] 로그인 실패 횟수 증가 (AuthFailHandler 에서 호출)
    *   - ifPresent : 회원이 존재할 때만 실행한다. (없는 아이디로 로그인 시도한 경우 무시)
    *   - 값 변경은 Entity 의 도메인 메서드가 하고, UPDATE 는 변경 감지가 처리한다.
    *  */
    @Transactional
    public void incrementLoginFailCount(String username) {

        userRepository.findByUserId(username)
                      .ifPresent(User::increaseLoginFailCount);
    }

    /* 4. [로그인 ⑥-1] 로그인 실패 횟수 초기화 (AuthSuccessHandler 에서 호출) */
    @Transactional
    public void resetLoginFailCount(String username) {

        userRepository.findByUserId(username)
                      .ifPresent(User::resetLoginFailCount);
    }
}
