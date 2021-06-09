package com.jimin.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") //괄호안에 들어가는 주소값[파일명,파일경로]과 return 해야하는 파일명이 같으면 void로 가능
        public String main(){
        return "main/home";
    }
}
