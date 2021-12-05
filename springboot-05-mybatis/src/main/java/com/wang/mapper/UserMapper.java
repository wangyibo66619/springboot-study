package com.wang.mapper;


import com.wang.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper  // 表示了这是一个mybatis的mapper类
@Repository
public interface UserMapper {

    //  int age = 18;            在接口里相当于普通类类中的  public static final int age = 18



    List<User> queryUserList();

    User queryUserById(int id);

    int addUser(User user);

    int updateUser(User user);

    int delete(int id);
}
