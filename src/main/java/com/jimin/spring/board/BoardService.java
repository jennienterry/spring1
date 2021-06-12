package com.jimin.spring.board;

import com.jimin.spring.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardMapper mapper; //객체화가 된 애의 주소값을 가져온다.

    @Autowired
    private BoardCmtMapper cmtMapper;

    @Autowired
    private MyUtils myUtils;
//    @Autowired
//    private HttpSession session; MyUtils에서 만들어줬음

    public int selMaxPageVal(BoardDTO param){
        return mapper.selMaxPageVal(param);
    }

    public List<BoardDomain> selBoardList(BoardDTO param){
        param.setIuser(myUtils.getLoginUserPk());
        int startIdx = (param.getPage() - 1) * param.getRecordCnt();
        param.setStartIdx(startIdx);
        return mapper.selBoardList(param);
    }


    public BoardDomain selBoard(BoardDTO param){
        return mapper.selBoard(param);
    }
    //return 값은 iboard값
    public int writeMod(BoardEntity param) { //BoardEntity아니면 object (= 부모타입은 자식객체 가리킬 수 있지만 자식은 부모객체x)
//      UserEntity loginUser = (UserEntity) session.getAttribute("loginUser");
//      param.setIuser(loginUser.getIuser());
        param.setIuser(myUtils.getLoginUserPk());
        if(param.getIboard() == 0) { //등록
            mapper.insBoard(param);
        }else { //수정
            mapper.updBoard(param);
        }
        return param.getIboard();
        //boardMapper.xml 에서 useGeneratedKeys="true" keyProperty="iboard"해줘서 이렇게 값을 가져올 수 있음
    }

    public int delBoard(BoardEntity param){
        //댓글 먼저 삭제한다.
        BoardCmtEntity cmtParam = new BoardCmtEntity();
        cmtParam.setIboard(param.getIboard());
        cmtMapper.delBoardCmt(cmtParam);

        param.setIuser(myUtils.getLoginUserPk());
        return mapper.delBoard(param);
    }

    public int insBoardCmt(BoardCmtEntity param) {
        param.setIuser(myUtils.getLoginUserPk());
        return cmtMapper.insBoardCmt(param);}

    public List<BoardCmtDomain> selBoardCmtList(BoardCmtEntity param){
        return cmtMapper.selBoardCmtList(param);
    }

    public int updBoardCmt(BoardCmtEntity param) {
        param.setIuser(myUtils.getLoginUserPk());
        return cmtMapper.updBoardCmt(param);
    }
    public int delBoardCmt(BoardCmtEntity param){
        param.setIuser(myUtils.getLoginUserPk());
        return cmtMapper.delBoardCmt(param);
    }
}