package com.jimin.spring.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller //HandlerMapping이 관리, 이걸 작성해주면 밑에있는 어노테이션들이 컨트롤러의 역할을 하게 된다.
@RequestMapping("/board")
public class BoardController {

    @Autowired
    // @Autowired 는 클래스에서 사용 할 수 있게끔 객체화 해주는것 (bean등록 된 애를)
    // bean 등록해야 autowired (객체화) 가능하게 되는 것
    // 객체화된 애의 주소 주세요
    private BoardService service;

    @RequestMapping("/list")
    public String list(Model model) {
        List<BoardDomain> list = service.selBoardList();
        model.addAttribute("list", list);
        return "board/list";
    }

    @RequestMapping("/detail")
    public String detail(BoardDTO param, Model model) {
        System.out.println("iboard : " + param.getIboard());
        BoardDomain vo = service.selBoard(param);
        model.addAttribute("vo", vo);
        return "board/detail"; //request.getRequestDispatcher("jsp 파일 경로").forward(request, response);
    }

    @ResponseBody
    //이걸 달면 목적이 달라진다. jsp를 여는게 아니라 result를 json으로 바꾸려고 한다. (data주는게 목적)
    //@responsebody는 js의 printwriter와 같다. 안해주면
    @RequestMapping(value="/cmtInsSel", method = RequestMethod.POST)
    public Map<String, Integer> cmtInsSel(@RequestBody BoardCmtEntity param){
        System.out.println("param = " + param);
        Map<String, Integer> result = new HashMap<String, Integer>(); // HashMap<>();이나 HashMap(); 해도 됨
        result.put("result",1);
        result.put("age",11);
       //map : collection(자료를 여러개를 담을 수 있다.)
        /* sequence 개념? for each돌릴 수 있냐 없냐차이
         * Arraylist는 순서가 있어서 forEach 돌릴 수 있고 순서에 의미가 있음
         * map은 순서가 없어서 forEach문 못 돌린다.
         */
        return result;

    }
}