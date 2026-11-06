package com.wanted.securitysession.domain.user.model.dto;

import lombok.*;

// [회원가입 ②] 회원가입 form 에서 넘어오는 값을 담는 DTO (input 의 name 과 필드명이 일치해야 한다.)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SignupDTO {

    private String userId;
    private String userName;
    private String userPass;
    private String role;
}
