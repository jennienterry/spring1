package com.jimin.spring.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//servlet
@Controller //HandlerMapping이 관리, 이걸 작성해주면 밑에있는 어노테이션들이 컨트롤러의 역할을 하게 된다.
@RequestMapping("/board")
public class BoardController {

    @Autowired
    // @Autowired 는 클래스에서 사용 할 수 있게끔 객체화 해주는것 (bean등록 된 애를)
    // bean 등록해야 autowired (객체화) 가능하게 되는 것
    // 객체화된 애의 주소 주세요
    private BoardService service;

    @RequestMapping("/list") //RequestMapping만 있으면 jsp파일 열어주는 것
    public String list(BoardDTO param, Model model) {
        model.addAttribute("list", service.selBoardList(param));
        model.addAttribute("maxPageVal", service.selMaxPageVal(param));
        return "board/list";
    }

    @RequestMapping("/detail")
    public String detail(BoardDTO param, Model model) {
        System.out.println("iboard : " + param.getIboard());
        BoardDomain vo = service.selBoard(param);
        model.addAttribute("vo", vo);
        return "board/detail"; //= request.getRequestDispatcher("jsp 파일 경로").forward(request, response);
    }

    @RequestMapping("/writeMod")
    public void writeMod(BoardDTO param, Model model){
        System.out.println("param = " + param);
        if(param.getIboard()>0){
            model.addAttribute("data", service.selBoard(param));
        }
    }

    @PostMapping("/writeMod")
    public String writeModProc(BoardEntity param){
        int iboard = service.writeMod(param);
        return "redirect:detail?iboard=" + iboard;
    }

    @GetMapping("/delBoard")
    public String delBoard(BoardEntity param){
        int iboard = service.delBoard(param);
        return "redirect:/board/list";
    }

    @ResponseBody
    //이걸 달면 목적이 달라진다. jsp를 여는게 아니라 return 값을 json 으로 바꾸려고 한다. (data주는게 목적)
    //@responsebody는 js의 printwriter와 같다.
    @RequestMapping(value="/cmt", method = RequestMethod.POST)
    public Map<String, Integer> cmtIns(@RequestBody BoardCmtEntity param){
        //Map의 자료구조는 key와 값으로 구성되는 자료구조이다. <키,값>
        //@RequestBody만 하면 js로부터 날아온 json형태의 문자열을 받아서 알아서  class멤버필드에 넣어줌
        System.out.println("param = " + param);
        int result = service.insBoardCmt(param);
        Map<String, Integer> data = new HashMap<String, Integer>(); // HashMap<>();이나 HashMap(); 해도 됨
        data.put("result",result);
       //map : collection(자료를 여러개를 담을 수 있다.)
        /* sequence 개념, for each 돌릴 수 있냐 없냐차이
         * Arraylist는 순서가 있어서 forEach 돌릴 수 있고 순서에 의미가 있음
         * map은 순서가 없어서 forEach문 못 돌린다.
         */
        return data;
    }

    @ResponseBody
    @RequestMapping("/cmt/{iboard}")        //이름이 같으면 "iboard" (생략가능) int iboard
    public List<BoardCmtDomain> cmtSel(@PathVariable int iboard){
        //쿼리스트링으로 넘어와서 @RequestBody 안적어줘도 됨
        //jackson(jsp에서는 gson)이 json형태의 문자열로 바꿔준다.
        BoardCmtEntity param = new BoardCmtEntity();
        param.setIboard(iboard);
        return service.selBoardCmtList(param);
    }

    @ResponseBody
    @RequestMapping(value="/cmt",method = RequestMethod.PUT)
        public Map<String, Integer> cmtUpd(@RequestBody BoardCmtEntity param){
        int result = service.updBoardCmt(param);
        Map<String,Integer> data = new HashMap();
        data.put("result", result);
        return data;
    }

    @ResponseBody
    @RequestMapping(value = "/cmt/{icmt}", method = RequestMethod.DELETE)
    public int delBoardCmt(@PathVariable int icmt){
        BoardCmtEntity param = new BoardCmtEntity();
        param.setIcmt(icmt);
//      int가 아닌 Map으로 할 경우
//      선언부는 Map<String, Integer> delBoardcmt ...
//      Map<String, Integer> result = new HashMap<>();
//      data.put("result",result);//put=set
//      return data; 하고 js에는 data.result로 해줘야한다.
        return service.delBoardCmt(param);
    }

    @GetMapping("/favList") //화면만 뿌림 list뿌리는것은 js에서 Ajax로 / Model model 안함
        public String favList(){
            return "board/favList";
        }
    }
