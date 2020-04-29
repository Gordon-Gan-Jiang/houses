package com.mooc.house.biz.service;

import com.mooc.house.common.models.User;

import java.util.List;

/**
 * @author Gordon Gan(Gordon.gan@alo7.com)
 */
public interface UserService {
    public List<User> getAllUsers();

    boolean addAccount(User account);

    boolean enable(String key);

    User auth(String userName, String password);

    void updateUser(User updateUser, String email);

    List<User> getUserByQuery(User query);
}
