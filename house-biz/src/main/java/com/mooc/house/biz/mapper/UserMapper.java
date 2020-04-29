package com.mooc.house.biz.mapper;

import com.mooc.house.common.models.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Gordon Gan(Gordon.gan@alo7.com)
 */
@Mapper
public interface UserMapper {
    public List<User> getAllUser();

    void insert(User account);

    List<User> selectUsersByQuery(User user);

    void delete(String email);

    void update(User user);
}
