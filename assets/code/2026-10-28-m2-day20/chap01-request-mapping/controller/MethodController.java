package com.wanted.chap01requestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/* comment.
*   Spring Boot App 은 Tomcat 이 내장되어 있다.
*   지금까지 사용자의 요청이 있으면 요청을 처리할 Servlet 을 만들었다고 한다면,
*   Servlet 은 Http 요청을 받는 즉시 @Controller 어노테이션이 달린 클래스에 요청을 위임한다.
* */
@Controller
public class MethodController {

    @RequestMapping("/menu/regist")
    public String RegistMenu(Model model) {

        /* comment. model 객체
        *   백엔드 서버에서 발생한 값을 담을 수 있는 Request 객체이다.
        *   key-value 형태로 값을 담을 수 있다.
        * */

        System.out.println("동작하니?");

        model.addAttribute("message","GET 방식의 메뉴 등록 핸들러 메서드 동작함...");

        /* comment.
        *   @Controller 계층의 마지막 임무는 사용자에게 어떤 응답을 해줄 것인지를 결정하는 것이다.
        *   메소드의 반환타입을 String 으로 한 후, 문자열을 return 하면 resources/templates 가 자동으로 접두사, .html 이 접미사로 설정된다.
        *   Servlet -> forward(req, res)
        * */
        return "mappingResult";

    }

    // @RequestMapping : Servlet -> service() : get/post/delete
    @RequestMapping(value = "/menu/regist", method = RequestMethod.POST)
    public String postRegistMenu(Model model) {
        System.out.println("동작하니?");
        model.addAttribute("message","POST 방식의 메뉴 등록 핸들러 메서드 동작함...");
        return "mappingResult";

    }

    //  @GetMapping : doget 방식!
    @GetMapping( "/menu/modify")
    public String getModifyMenu(Model model) {
        System.out.println("동작하니?");
        model.addAttribute("message","GET 방식의 메뉴 수정 핸들러 메서드 동작함...");
        return "mappingResult";
    }

    @PostMapping( "/menu/modify")
    public String postModifyMenu(Model model) {
        System.out.println("동작하니?");
        model.addAttribute("message","POST 방식의 메뉴 수정 핸들러 메서드 동작함...");
        return "mappingResult";
    }

}
