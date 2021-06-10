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

    @PostMapping("/fav")
    public Map<String, Integer> insFav(@RequestBody BoardFavEntity param) {
        Map<String, Integer> result = new HashMap();
        result.put("result", service.insFav(param));
        return result;
    }

    @GetMapping("/fav")
    public Map<String, Integer> selFav(BoardFavEntity param) {
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