package com.wanted.chap01requestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order/*")
public class ClassController {

    @GetMapping("/regist")
    public String registOrder(Model model) {
        model.addAttribute("message", "GET 방식의 주문 등록 핸들러 메서드 동작.");
        return "mappingResult";

    }

    /* comment.
    *   요청 경로 : /order/detail/3
    *   3 : pathVariable 이라고 불리우며 요청 url 을 타고 서버로 넘어오는 값을 의미한다.
    *  */
    @GetMapping("/detail/{orderNo}")
//    public String pathVariable(Model model, @PathVariable int orderNo) {
    public String pathVariable(Model model, @PathVariable("orderNo") int no) {
        model.addAttribute("message", no + " 번 주문 상세조회!!");
        return "mappingResult";
    }

}
