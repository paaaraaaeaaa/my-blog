package com.wanted.fileupload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // bean 으로 등록
public class MainController {

    @GetMapping("/")
    public String main() {return "main";}



}
