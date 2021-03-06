package com.jimin.spring.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller //컨트롤러, 서비스, 컴포넌트 등등 (빈 등록하는 애들)
//빈 : 스프링에서 관리하는 객체
//컨트롤러 애노테이션 : 서블릿으로 연결을 시켜주는 애

@RequestMapping("/user") //class위쪽에 1차 주소값을 주면 그 뒤에는 2차 주소값만 적어도 된다.
public class UserController { // 얘는 1차 주소값
//고객의 요청이 왔을 때 연결해줄 수 있는 기능까지 갖고 있는게 controller
    @Autowired
    //빈등록이 안되어있으면 spring이 관리를 못하므로 null이 됨
    //해당하는 타입의 객체를 스프링이 들고 있다면 (@로 bean등록이 되어 있다면)
    //service(변수)에다가 그 객체의 인스턴스 주소를 저장
    //@Qualifier("아이디값") 해당하는 타입으로 만들 수 있는 객체가 2개 이상일 경우(자식) 특정할 수 있는 어노테이션
    private UserService service;


    //model이 익지 않으면 HttpServletRequest request해서 request.setAttribute 직접 적어주면 된다^^
    @RequestMapping(value="/login", method= RequestMethod.GET) //원래 이렇게 적어줘야하지만 get은 기본이라 안적어도 됨
    public String login(Model model, @RequestParam(value="err", required=false, defaultValue="0") int err){
        System.out.println("err : " + err);      //쿼리스트링 꼭 안필요하다는 의미 / 값이 없으면 0넘어오도록 설정 (이거쓰면 required 안써도됨)
        switch(err){
            case 1: //아이디 없음
                model.addAttribute("errMsg","아이디를 확인해주세요.");
                //model이 request.setAttribute알아서 해줌
                break;
            case 2: //비밀번호 틀림
                model.addAttribute("errMsg","비밀번호를 확인해주세요.");
                break;
        }
        return "user/login";} //기본 dispatcher
    // return "redirect:user/login"; 리다이렉트 방식
    // 세팅해준 디스패쳐서블릿이 주소창에 쓰인 주소값으로 이 메소드(login())를 찾아 실행 후,
    // prefix 값 + return 해준 String 값+ suffix 값 해서 request.getDispatch.forward(request,response) 해준다.

    @RequestMapping(value="/login", method= RequestMethod.POST)
    public String login(UserEntity param){
        return "redirect:" + service.login(param);
        //redirect: 안해주면 UserMapper.java에서 각자 다 붙여줘야함 (jsp파일을 열어주는애)
        //redirect: 하면 Controller를 호출
    }

    @RequestMapping("/join") //괄호안에 들어가는 주소값[파일명,파일경로]과 return(String) 해야하는 파일명이 같으면 void로 가능
    public void join(){ }

    @RequestMapping(value="/join", method=RequestMethod.POST)
    public String join(UserEntity param){
    //날릴 때 get param할 필요없이 멤버필드명과 맞춰주기만 하면 알아서 UserEntity 객체가 넣어서 보내줌 (dispatcher가)
    System.out.println("uid : " + param); //sout 사용하기
    service.join(param);
    return "redirect:/user/login"; //기존에 response.sendRedirect()와 같은 역할 (서블릿을 호출)
}

    @GetMapping("/logout") //로그인했던 페이지로 가려면 request 넣으면 되고 원하는 페이지로 보내고싶으면 request없이 하드코딩 하면 된다.
    public String logout(HttpSession hs, HttpServletRequest request){
        hs.invalidate();
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @GetMapping("/profile")
    public String profile(){
        return "user/profile";
    }

//  = @RequestMapping(value="/profile", method = RequestMethod.POST)
    @PostMapping("/profile")
    public String profile(@RequestParam("profileImg") MultipartFile profileImg){
        return "redirect:" + service.uploadProfile(profileImg); //변슈,파일명 name멍 다 풀ㅇ어ㅓㅓ해ㅑ함

    }
    }