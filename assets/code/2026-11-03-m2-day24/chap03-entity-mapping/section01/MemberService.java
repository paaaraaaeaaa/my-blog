package com.wanted.entitymapping.section01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /* comment. Service
    *   1. 비즈니스 로직 작성 (시스템 정책)
    *   2. DAO 데이터베이스 접근 객체의 메서드를 호출한다.
    *   3. 트랜젝션 관리
    *   - 여러 시스템 비즈니스 로직이 섞이게 되며 1개라도 실패 시 rollback 작업이 매우 중요하다!!!
    * */

    // 메서드 실행 시 begin()
    // 메서드 종료 시 - 정상 종료 : commit() , 예외 발생 : rollback();
    // 블로그 작성 키워드 (중요!)
    // 1. Checked 예외 / UnChecked 예외의 Transactional 동작 방식
    // 2. Transactional 의 전파
    @Transactional
    public int registMember(MemberRegistDTO dto) {

        // 1. DTO -> Entity
        Member member = new Member(
                dto.getMemberId(),
                dto.getMemberPwd(),
                dto.getMemberName(),
                dto.getPhone(),
                dto.getAddress(),
                dto.getEnrollDate(),
                dto.getMemberRole(),
                dto.getStatus()
        );
        // 2. 변환된 Entity 를 JPA 를 사용하는 DAO 계층에 전달한다.
        memberRepository.save(member);

        return member.getMemberNo();
    }

    /* comment.
    *   읽기 전용 트랜젝션은 불필요한 변경 감지를 하지 않는다.
    * */
    @Transactional(readOnly = true)
    public MemberRegistDTO findByMemberNo(int memberNo) {

        // 1. 영속성 컨텍스트에서 PK 엔티티 find(PK);
        Member foundMember = memberRepository.findByMemberNo(memberNo);

        // 2. Entity 객체를 DTO 변환
        MemberRegistDTO findMember = new MemberRegistDTO(
                foundMember.getMemberId(),
                foundMember.getMemberPwd(),
                foundMember.getMemberName(),
                foundMember.getPhone()
        );

        return findMember;

    }
}
