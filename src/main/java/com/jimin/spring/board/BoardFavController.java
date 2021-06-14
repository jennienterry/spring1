package com.jimin.spring.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

    @RestController //Controller 메소드들의 jsp파일 return이 아니라 json을 return하는게 목적이 된다.
                    //그러면 mapper에서 responsebody 안해줘도 된다.

    @RequestMapping("/board")
    public class BoardFavController {

    @Autowired
    private BoardFavService service;

    @Autowired
    private BoardService BoardService;

    @PostMapping("/fav")
    public Map<String, Integer> insFav(@RequestBody BoardFavEntity param) {
        //@RequestParam 하면 하나하나기 때문에 필요한 것을 받으려면 따로 여러번 적어줘야하는데, RequestBody하면 한번에 다 받을 수 있다.
        Map<String, Integer> result = new HashMap();
        result.put("result", service.insFav(param));
        return result;
    }

//    public List<BoardDomain> selFavBoardList(BoardDTO param){
//        param.setSelType(1);
//        return BoardService.selBoardList(param);
//    }

        // 좋아요 리스트
        @GetMapping("/fav")
        public Map<String, Object> selFavBoardList(BoardDTO param) {
            Map<String, Object> result = new HashMap();
            param.setSelType(1);
            result.put("list", BoardService.selBoardList(param));
            result.put("maxPageVal", BoardService.selMaxPageVal(param));
            return result;
        }

    @GetMapping("/fav/{iboard}")
    public Map<String, Integer> selFav(BoardFavEntity param, @PathVariable int iboard) {
        param.setIboard(iboard);
        Map<String, Integer> result = new HashMap();
        result.put("result", service.selFav(param));
        return result;
    }

    @DeleteMapping("/fav")
    public Map<String, Integer> delFav(BoardFavEntity param) {
        Map<String, Integer> result = new HashMap();
        result.put("result", service.delFav(param));
        return result;
    }
}