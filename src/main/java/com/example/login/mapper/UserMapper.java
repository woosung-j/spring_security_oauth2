package com.example.login.mapper;


import com.example.login.domain.SocialUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    SocialUser findUserByEmail(String email);
    int insertUserBySocialUser(SocialUser socialUser);
    int updateNameById(SocialUser socialUser);
}
