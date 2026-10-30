package com.wanted.thymeleaf.controller;


import com.wanted.thymeleaf.model.dto.MemberDTO;
import com.wanted.thymeleaf.model.dto.SelectCriteria;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
 * ModelAndView
 * : 화면에 전달할 데이터(Model)와 응답할 뷰 이름(View)을 한 번에 담아 반환하는 객체
 *   - addObject("키", 값) : 템플릿에서 ${키} 로 꺼내 쓸 데이터를 담는다.
 *   - setViewName("뷰 이름") : templates 폴더 기준 경로를 지정한다.
 *     (prefix "classpath:/templates/" + 뷰 이름 + suffix ".html")
 *     뷰 이름 앞에 '/'를 붙이면 경로가 "templates//lecture/..."가 되어 jar로 배포했을 때 템플릿을 못 찾을 수 있으므로 붙이지 않는다.
 */
@Controller
@RequestMapping("/lecture")
public class LectureController {

    @GetMapping("/expression")
    public ModelAndView expression(ModelAndView mv) {

        /* 선택 변수 표현식 *{...}, 리터럴 치환 |...| 예제에서 사용 */
        mv.addObject("member", new MemberDTO("홍길동", 20, '남', "서울시 서초구"));

        /* th:text와 th:utext의 차이(HTML 태그 escape 여부)를 확인하기 위해 태그가 포함된 문자열을 전달 */
        mv.addObject("hello", "hello!<h3>Thymeleaf</h3>");

        mv.setViewName("lecture/expression");

        return mv;
    }

    @GetMapping("/conditional")
    public ModelAndView conditional(ModelAndView mv) {

        /* th:if, th:unless, th:switch 예제에서 사용 */
        mv.addObject("num", 1);
        mv.addObject("str", "바나나");

        /* th:each 예제에서 사용 */
        List<MemberDTO> memberList = new ArrayList<>();
        memberList.add(new MemberDTO("홍길동", 20, '남', "서울시 서초구"));
        memberList.add(new MemberDTO("유관순", 22, '여', "서울시 노원구"));
        memberList.add(new MemberDTO("장보고", 40, '남', "서울시 종로구"));
        memberList.add(new MemberDTO("신사임당", 30, '여', "서울시 성북구"));

        mv.addObject("memberList", memberList);

        mv.setViewName("lecture/conditional");

        return mv;
    }

    @GetMapping("/etc")
    public ModelAndView etc(ModelAndView mv) {

        SelectCriteria selectCriteria = new SelectCriteria(1, 10, 3);

        /*
         * 키 이름 없이 객체만 담으면 클래스 이름의 첫 글자를 소문자로 바꾼 이름이 키가 된다.
         * SelectCriteria -> "selectCriteria" (템플릿에서 ${ selectCriteria } 로 사용)
         */
        mv.addObject(selectCriteria);

        MemberDTO member = new MemberDTO("홍길동", 20, '남', "서울시 서초구");

        mv.addObject("member", member);

        List<MemberDTO> memberList = new ArrayList<>();
        memberList.add(new MemberDTO("홍길동", 20, '남', "서울시 서초구"));
        memberList.add(new MemberDTO("유관순", 22, '여', "서울시 노원구"));
        memberList.add(new MemberDTO("장보고", 40, '남', "서울시 종로구"));
        memberList.add(new MemberDTO("신사임당", 30, '여', "서울시 성북구"));

        mv.addObject("memberList", memberList);

        /* HashMap은 순서를 보장하지 않으므로, th:each 출력 순서를 넣은 순서대로 유지하기 위해 LinkedHashMap 사용 */
        Map<String, MemberDTO> memberMap = new LinkedHashMap<>();
        memberMap.put("m01", new MemberDTO("홍길동", 20, '남', "서울시 서초구"));
        memberMap.put("m02", new MemberDTO("유관순", 22, '여', "서울시 노원구"));
        memberMap.put("m03", new MemberDTO("장보고", 40, '남', "서울시 종로구"));
        memberMap.put("m04", new MemberDTO("신사임당", 30, '여', "서울시 성북구"));

        mv.addObject("memberMap", memberMap);

        mv.setViewName("lecture/etc");

        return mv;
    }


    @GetMapping("/fragment")
    public ModelAndView fragment(ModelAndView mv){

        /* fragment에 매개변수로 전달할 값 (common/aside.html 참고) */
        mv.addObject("test1", "values1");
        mv.addObject("test2", "values2");
        mv.setViewName("lecture/fragment");
        return mv;
    }
}
