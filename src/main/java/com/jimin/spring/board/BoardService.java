package com.jimin.spring.board;

import com.jimin.spring.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardMapper mapper;

    @Autowired
    private BoardCmtMapper cmtMapper;

    @Autowired
    private HttpSession session;

    public List<BoardDomain> selBoardList(){ return mapper.selBoardList(); }

    public BoardDomain selBoard(BoardDTO param){
        return mapper.selBoard(param);
    }

    //return 값은 iboard값
    public int writeMod(BoardEntity param) { //BoardEntity아니면 object (= 부모타입은 자식객체 가리킬 수 있지만 자식은 부모객체x)
        if(param.getIboard() == 0){
            //등록
            return 0;
    }
        //수정
        return mapper.writeMod(param);
    }

    public int insBoardCmt(BoardCmtEntity param) {
        UserEntity loginUser = (UserEntity) session.getAttribute("loginUser");
        param.setIuser(loginUser.getIuser());
        return cmtMapper.insBoardCmt(param);}

    public List<BoardCmtDomain> selBoardCmtList(BoardCmtEntity param){
        return cmtMapper.selBoardCmtList(param);
    }

    public int updBoardCmt(BoardCmtEntity param) {
        UserEntity loginUser = (UserEntity) session.getAttribute("loginUser");
        param.setIuser(loginUser.getIuser());
        return cmtMapper.updBoardCmt(param);
    }
    public int delBoardCmt(BoardCmtEntity param){
        UserEntity loginUser = (UserEntity) session.getAttribute("loginUser");
        param.setIuser(loginUser.getIuser());
        return cmtMapper.delBoardCmt(param); }
}
