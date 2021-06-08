package com.jimin.spring.board;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardCmtMapper {
    int insBoardCmt(BoardCmtEntity param); //select빼고는 int주면 된다.
    List<BoardCmtDomain> selBoardCmtList(BoardCmtEntity param); //list적으면 batis가 while문 돌려줌
    int updBoardCmt(BoardCmtEntity param);
    int delBoardCmt(BoardCmtEntity param);
}
//interface는 생략해도 public abstract를 자동으로 넣어준다.

