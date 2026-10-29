package com.wanted.viewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ResolverController {

    /* comment.
    *   Spring 에서 @Controller 는 View 를 반환해야 하는 책임을 가지고 있다.
    *   View 를 반환하는 방법은 3가지가 있다.
    *   1. 반환타입 void : 요청한 url 곧 view 의 경로가 된다.
    *   2. 반환타입 String : 문자열 리턴값이 곧 view 의 경로가 된다.
    *   3. 반환타입 ModelAndView : setViewName("문자열"); 문자열이 곧 view 경로가 된다.
    * */

    @GetMapping("/string")
    public String stringView(Model model) {

        model.addAttribute("forwardMessage", "문자열로 View 반환");

        /* 가정 1. 해당 메서드 내부에서 회원가입이 로직 수행 */
        // service.registMember(); insert
        System.out.println("회원가입 완료!!!");

        // ViewResolver 객체는 리턴되는 문자열에 접두사로 resources/templates 접미사로 .html 을 붙여주게 된다.
        // resources/templates/result.html << 이 형태
        return "result";
    }

    @GetMapping("/string-redirect")
    public String redirect(RedirectAttributes rttr) {

        /* comment.
        *   Spring 에서 view 리턴 시 기본 방식은 forward 방식이다.
        *   만약 redirect 가 필요하다면 문자열에 접두사로 redirect:{보낼 URL} 을 붙여주어야 한다.
        * */
        /* 가정 1. 해당 메서드 내부에서 회원가입이 로직 수행 */
        // service.registMember(); insert
        System.out.println("회원가입 완료!!!");

        /* comment.
        *   redirect 시에는 2번의 요청이 발생한다.
        *   그렇기 때문에 최초에 model 에 담아둔 값은 재요청 시 소멸이 되며 / 요청에서는 사용할 수 없게 된다.
        *   따라서 redirect 시 전달해 줄 값이 있다면 Servlet 개념인 session 과 cookie 를 사용해야 한다.
        *   Spring 에서는 RedirectAttributes 라는 객체를 통해서 redirect 시 값을 저장할 수 있는 방법을 제공해준다.
        * */
//        model.addAttribute("forwardMessage", "문자열로 View 반환");
        rttr.addFlashAttribute("flashMessage", "리다이랙트 된 페이지에서도 유지되는 값!");

        return "redirect:/";
    }

    @GetMapping("modelAndView")
    public ModelAndView modelAndView(ModelAndView mv) {
        mv.addObject("forwardMessage", "MV 활용 뷰와 데이터!");
        mv.setViewName("result");

        return mv;
    }

}
