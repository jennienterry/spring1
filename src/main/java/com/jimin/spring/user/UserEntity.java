package com.jimin.spring.user;

import lombok.*;

@Getter
@Setter
@ToString
//@Alias 사용가능
public class UserEntity {
    private int iuser;
    private String uid;
    private String upw;
    private String unm;
    private int gender;
    private String regdt;
    private String profileImg;

}
