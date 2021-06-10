package com.jimin.spring;

import com.jimin.spring.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component //빈등록
public class MyUtils {
//@bean : 개발자가 직접 제어 불가능한 외부 라이브러리등을 만들 때 사용
//@component : 개발자가 직접 작성한 class를 bean으로 등록하기 위해 사용
//             (java에서 new로 생성하듯이 생성, java class를 스프링 bean이라고 표시하는 역할)
        @Autowired
        private HttpSession session;
        //bean등록한 객체들 중에 이 타입이 가리킬 수 있는 딱 하나의 값을 가져온다.
        //지금 객체가 하나라는 의미

        public int getLoginUserPk(){
            UserEntity loginUser = getLoginUser();
            return loginUser == null ? 0 : loginUser.getIuser();
        }

        public UserEntity getLoginUser(){
            return (UserEntity) session.getAttribute("loginUser");
        }
    }