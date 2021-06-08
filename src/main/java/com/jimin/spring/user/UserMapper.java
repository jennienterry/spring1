package com.jimin.spring.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper //MyBatis에서 DAO만들어주는 애
//얘랑 xml이랑 세트
//java랑 xml이랑 mapping 맞춰야함
public interface UserMapper {
    int insUser(UserEntity param);
    UserEntity selUser(UserEntity param);
    int updUser(UserEntity param);
}
