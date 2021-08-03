package com.example.backendnodejsvuedemo01.pojo;

/**
 * @Author Nick
 * @Classname User
 * @Date 2021/8/3 17:55
 * @Description     用户实体类
 */

public class User {
    int id;
    String username;
    String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
