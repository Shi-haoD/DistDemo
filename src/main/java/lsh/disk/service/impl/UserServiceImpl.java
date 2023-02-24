package lsh.disk.service.impl;

import lsh.disk.mapper.UserMapper;
import lsh.disk.pojo.User;
import lsh.disk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User login(String name, String pwd) throws Exception {
        return userMapper.getUser(name,pwd);
    }

    public Integer add(String name, String pwd) throws Exception {
        return userMapper.add(name,pwd);
    }
}
