package com.jimin.spring.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardCmtDomain extends BoardCmtEntity{
    private String writerNm;
    private String profileImg;
}
