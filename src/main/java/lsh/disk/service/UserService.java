package lsh.disk.service;


import lsh.disk.pojo.User;

public interface UserService {
    User login(String name, String pwd)throws  Exception;

    Integer add(String name, String pwd)throws  Exception;
}
