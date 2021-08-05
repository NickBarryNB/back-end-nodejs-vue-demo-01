package com.example.backendnodejsvuedemo01.service;

import com.example.backendnodejsvuedemo01.dao.CategoryDao;
import com.example.backendnodejsvuedemo01.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Nick
 * @Classname CategoryService
 * @Date 2021/8/5 15:42
 * @Description
 */
@Service
public class CategoryService {
    @Autowired
    CategoryDao categoryDao;

    public List<Category> list(){
        //通過id降序排序
        Sort id = Sort.by(Sort.Direction.DESC,"id");
//        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "id");
//        Sort id = Sort.by(order);
//        Sort id = new Sort.by(Sort.Direction.DESC, "id");
        return categoryDao.findAll(id);
    }

    //条件判断
    public Category get(int id){
        return categoryDao.findById(id).orElse(null);
    }
}
