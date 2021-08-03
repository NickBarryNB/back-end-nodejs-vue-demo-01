package com.example.backendnodejsvuedemo01.dao;

import com.example.backendnodejsvuedemo01.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Nick
 * @Classname UserDao   Data Access Object（数据访问对象，DAO）
 * @Date 2021/8/3 19:59
 * @Description 用来操作数据库的对象  这个对象可以是我们自己开发的，也可以是框架提供的。
 * 这里我们通过继承 JpaRepository 的方式构建 DAO
 */
// 这里关键的地方在于方法的名字。由于使用了 JPA，无需手动构建 SQL 语句，
// 而只需要按照规范提供方法的名字即可实现对数据库的增删改查。
public interface UserDao extends JpaRepository<User,Integer> {
    User findByUsername(String username);
    User getByUsernameAndPassword(String username,String password);
}
