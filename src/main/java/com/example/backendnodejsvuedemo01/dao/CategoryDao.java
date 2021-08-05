package com.example.backendnodejsvuedemo01.dao;

import com.example.backendnodejsvuedemo01.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Nick
 * @Classname CategoryDao
 * @Date 2021/8/5 15:37
 * @Description 这个 DAO 不需要额外构造的方法，JPA 提供的默认方法就够用了
 */
public interface CategoryDao extends JpaRepository<Category,Integer> {
}
