package com.jimin.spring.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {
    private int iboard;
    private int iuser;
    private int selType; // 0:기본 리스트, 1:좋아요 리스트 ..
    private int page = 1;
    private int startIdx;
    private int recordCnt = 5;
    private int searchType;
    private String searchText;
}
