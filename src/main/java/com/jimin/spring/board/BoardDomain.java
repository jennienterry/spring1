package com.jimin.spring.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDomain extends BoardEntity{
    private String writerNm;
    private String profileImg;
    private int isFav;
}
