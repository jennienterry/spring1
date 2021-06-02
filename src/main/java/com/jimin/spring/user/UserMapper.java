package com.jimin.spring.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper //MyBatis에서 DAO만들어주는 애
//얘랑 xml이랑 세트
public interface UserMapper {
    int insUser(UserEntity param);
}
