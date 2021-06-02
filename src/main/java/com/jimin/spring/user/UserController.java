package com.jimin.spring.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller //컨트롤러, 서비스, 컴포넌트 등등 (빈 등록하는 애들)
//빈 : 스프링에서 관리하는 객체
//컨트롤러 애노테이션 : 서블릿으로 연결을 시켜주는 애

@RequestMapping("/user") //class위쪽에 1차 주소값을 주면 그 뒤에는 2차 주소값만 적어도 된다.
public class UserController {

    @Autowired
    //빈등록이 안되어있으면 spring이 관리를 못하므로 null이 됨
    //해당하는 타입의 객체를 스프링이 들고 있다면 (@로 bean등록이 되어 있다면)
    //service(변수)에다가 그 객체의 인스턴스 주소를 저장
    //@Qualifier("아이디값") 해당하는 타입으로 만들 수 있는 객체가 2개 이상일 경우(자식) 특정할 수 있는 어노테이션
    private UserService service;


    @RequestMapping(value="/login", method= RequestMethod.GET) //원래 이렇게 적어줘야하지만 get은 기본이라 안적어도 됨
    public String login(){
        return "user/login";} //기본 dispatcher
    // return "redirect:user/login"; 리다이렉트 방식
    // 세팅해준 디스패쳐서블릿이 주소창에 쓰인 주소값으로 이 메소드(login())를 찾아 실행 후,
    // prefix 값 + return 해준 String 값+ suffix 값 해서 request.getDispatch.forward(request,response) 해준다.

    @RequestMapping("/join")
    public String join(){
        return "user/join";
}

    @RequestMapping(value="/join", method=RequestMethod.POST)
    public String join(UserEntity param){
    //날릴 때 get param할 필요없이 멤버필드명과 맞춰주기만 하면 알아서 UserEntity 객체가 넣어서 보내줌 (dispatcher가)
    System.out.println("uid : " + param); //sout 사용하기
    service.join(param);
    return "redirect:/user/login"; //기존에 response.sendRedirect()와 같은 역할 (서블릿을 호출)
}
    }