package com.wanted.entitymapping.section01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    // Controller 에서 Service Bean 을 주입(의존)
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    public void registMember(MemberRegistDTO memberRegistDTO) {

        int memberNo = memberService.registMember(memberRegistDTO);

    }

    public void findMember(int memberNo) {
        MemberRegistDTO foundMember = memberService.findByMemberNo(memberNo);
        System.out.println("foundMember = " + foundMember);
    }
}
