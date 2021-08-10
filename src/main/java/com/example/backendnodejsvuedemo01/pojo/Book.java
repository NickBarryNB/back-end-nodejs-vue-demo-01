package com.example.backendnodejsvuedemo01.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * @Author Nick
 * @Classname Book
 * @Date 2021/8/5 15:30
 * @Description
 */
@Data
@Entity
@Table(name = "book")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    //    findAllByCategory() 之所以能实现，是因为在 Book 类中有以下注解
    //    实际上是把 category 对象的 id 属性作为 cid 进行了查询
    @ManyToOne
    @JoinColumn(name = "cid")
    private Category category;

    String cover;
    String title;
    String author;
    String date;
    String press;
    String abs;
}
