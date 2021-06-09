package com.jimin.spring.board;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDomain> selBoardList();
    BoardDomain selBoard(BoardDTO param); // 넘겨줘야할 멤버필드를 가지고 있는 클래스명을 타입으로 적어주기
//  override : 자식이 부모꺼 쓸껀데 맘에 안들어서 자기껄로 내용을 바꿔쓸 수 있다.
// overload : 한 클래스 안에서 같은 메소드를 선언해준다. (다향성을 위해서) like 생성자
// interface는 선언만 override를 강제로 해준다.
    int writeMod(BoardEntity param);

}
