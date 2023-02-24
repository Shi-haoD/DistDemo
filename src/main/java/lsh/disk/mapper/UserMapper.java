package lsh.disk.mapper;

import lsh.disk.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User getUser(@Param("name") String name, @Param("pwd") String pwd);
    Integer add(@Param("name") String name, @Param("pwd") String pwd);
}
