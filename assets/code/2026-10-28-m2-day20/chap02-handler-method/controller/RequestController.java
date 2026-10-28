package com.wanted.chap02handlermethod;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/request/*")
public class RequestController {

    /* comment.
    *   View 를 return 하는 방식
    *   1. String 타입으로 view 이름 return
    *   2. void : /request/regist 요청경로 == view 경로
    * */
    @GetMapping("regist")
    public void regist() {}

    @PostMapping("regist")
    public String registMenu(Model model, WebRequest request) {
        // WebRequest 는 Servlet 환경의 HttpServletRequest 의 역할을 한다.
        // 사용자의 요청 정보가 해당 객체에 들어가게 된다.
        String menuName = request.getParameter("name");
        int menuPrice = Integer.parseInt(request.getParameter("price"));
        int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));

        //service(menuName, menuPrice, categoryCode);
        String message = menuName + "을(를) 신규 메뉴 목록" + "" + categoryCode + "번 카테고리에" + menuPrice + " 원으로 등록했습니다!";

        model.addAttribute("message", message);

        return "request/printResult";

    }

    @GetMapping("modify")
    public void modify() {}

    /* comment.
    *   @RequestParam 은 사용자의 request 에 들어있는 값을 손쉽게 꺼낼 수 있는 어노테이션이다.
    *   단, 가장 주의해야 할 것.
    *   - View 쪽에서 input 타입의 name 속성에 작성해둔 값과 변수명을 일치시키지 않으면 400 Bad Request 가 발생한다.
    * */
    @PostMapping("modify")
    public String modifyMenu(Model model, @RequestParam("modifyName") String name, @RequestParam int modifyPrice) {
        System.out.println("modifyName = " + name);
        System.out.println("modifyPrice = " + modifyPrice);
        return "request/printResult";
    }

    @GetMapping("search")
    public void search() {}

    @PostMapping("search")
    public String searchMenu(@ModelAttribute("menu") MenuDTO menu) {
        /* comment.
        *   ModelAttribute
        *   - 클래스 자료형을 활용해서 사용자의 입력 값들을 하나의 객체로 묶을 수 있다.
        *   ---
        *   Model 객체를 활용하지 않아도 forward 되는 페이지에서 값들을 활용할 수 있다.
        *   ex) @ModelAttribute MenuDTO menu
        *   MenuDTO -> menuDTO 이름으로 사용될 수 있다.
        * */

        System.out.println("menu = " + menu);

        return "request/searchResult";
    }

}
