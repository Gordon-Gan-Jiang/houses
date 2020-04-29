package com.mooc.house.biz.service.impl;

import java.util.List;

import com.mooc.house.biz.components.*;
import com.google.common.collect.Lists;
import com.mooc.house.biz.mapper.UserMapper;
import com.mooc.house.biz.service.UserService;

import com.mooc.house.common.models.User;
import com.mooc.house.common.utils.BeanHelper;
import com.mooc.house.common.utils.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


/**
 * @author Gordon Gan(Gordon.gan@alo7.com)
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MailComponent mailComponent;

    @Autowired
    private FileComponent fileComponent;

    @Value("${file.prefix}")
    private String imgPrefix;

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUser();
    }

    /**
     * 1.插入数据库，非激活;密码加盐md5;保存头像文件到本地 2.生成key，绑定email 3.发送邮件给用户
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addAccount(User account) {
        account.setPasswd(HashUtils.encryPassword(account.getPasswd()));
        List<String> imgList = fileComponent.getImgPaths(Lists.newArrayList(account.getAvatarFile()));
        if (!imgList.isEmpty()) {
            account.setAvatar(imgList.get(0));
        }
        BeanHelper.setDefaultProp(account, User.class);
        BeanHelper.onInsert(account);
        account.setEnable(0);
        userMapper.insert(account);
        mailComponent.registerNotify(account.getEmail());
        return true;
    }

    @Override
    public boolean enable(String key) {
        return mailComponent.enable(key);
    }

    /**
     * 用户名密码验证
     */
    @Override
    public User auth(String userName, String password) {
        User user = new User();
        user.setEmail(userName);
        user.setPasswd(HashUtils.encryPassword(password));
        user.setEnable(1);
        List<User> users = getUsersByUser(user);
        if (!CollectionUtils.isEmpty(users)) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public void updateUser(User updateUser, String email) {
        updateUser.setEmail(email);
        BeanHelper.onUpdate(updateUser);
        userMapper.update(updateUser);
    }

    @Autowired
    public List<User> getUserByQuery(User user) {

        return getUsersByUser(user);
    }

    public List<User> getUsersByUser(User user) {
        List<User> users = userMapper.selectUsersByQuery(user);
        users.forEach(u -> {
            u.setAvatar(imgPrefix + u.getAvatar());
        });
        return users;
    }
}
