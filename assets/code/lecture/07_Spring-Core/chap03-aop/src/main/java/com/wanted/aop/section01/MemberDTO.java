package com.wanted.aop.section01;

import lombok.*;
import org.springframework.stereotype.Service;

// DTO 는 행위 위주의 객체가 아닌
// 데이터 위주의 객체이다.
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberDTO {

    private String email;
    private String password;
    private String name;
    private String phone;
    private String role;

}
