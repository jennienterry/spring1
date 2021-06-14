package com.jimin.spring;

import com.jimin.spring.user.UserEntity;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class AuthCheckInterceptor implements HandlerInterceptor {
    private final String[] needLoginUriArr = {"/board/writeMod", "/board/favList", "/user/profile"};
    // 이 경로를 dispatcherServlet.xml에 직접 적어주는 것이 더 좋다.


    //controller로 보내기 전에 처리
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true; //false하면 controller로 안 넘어감
    }

    //controller의 handler가 끝나면 처리
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        String uri = request.getRequestURI();
        System.out.println("uri : " + uri);

        if(Arrays.asList(needLoginUriArr).contains(uri)){
            UserEntity loginUser = (UserEntity) request.getSession().getAttribute("loginUser");
            if(loginUser == null) {
//                modelAndView.addObject("",""); 담아놓은 값도 바꿀 수 있고
                System.out.println("viewName : " + modelAndView.getViewName());
                modelAndView.setViewName("/user/needLogin"); //인터셉트
            }
        }
    }

    //view 까지 처리가 끝난 후에 처리
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
