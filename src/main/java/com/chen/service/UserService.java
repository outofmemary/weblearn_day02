package com.chen.service;

import com.chen.dao.mapper.UserMapper;
import com.chen.entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;
    public boolean isUserExisted(String username){
        return userMapper.getCountByUserName(username)>0;
    }

    @Transactional
    public boolean addUser(String username, String password) {
        User user=new User();
        user.setCreateTime(new Date());
        user.setIsdelete(0);
        user.setUsername(username);
        user.setPassword(DigestUtils.md5Hex(password));
        return userMapper.insertSelective(user)>0;
    }

    public int login(String username, String password) {
        User user=userMapper.getByUserName(username);
        if(user!=null){
            if(DigestUtils.md5Hex(password).equals(user.getPassword())){
                return user.getId();
            }
        }
        return 0;
    }
}

