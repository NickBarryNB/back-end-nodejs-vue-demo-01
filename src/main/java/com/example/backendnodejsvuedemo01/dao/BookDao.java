package com.example.backendnodejsvuedemo01.dao;

import com.example.backendnodejsvuedemo01.pojo.Book;
import com.example.backendnodejsvuedemo01.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author Nick
 * @Classname BookDao
 * @Date 2021/8/5 15:36
 * @Description
 */
public interface BookDao extends JpaRepository<Book,Integer> {
//    findAllByCategory() 之所以能实现，是因为在 Book 类中有@ManyToOne等注解
//    实际上是把 category 对象的 id 属性作为 cid 进行了查询
    List<Book> findAllByCategory(Category category);

//    “根据标题或作者进行模糊查询”，参数是两个 String，分别对应标题或作者
    List<Book> findALlByTitleLikeOrAuthorLike(String keyword1,String keyword2);
}
