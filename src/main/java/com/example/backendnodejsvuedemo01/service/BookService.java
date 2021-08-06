package com.example.backendnodejsvuedemo01.service;

import com.example.backendnodejsvuedemo01.dao.BookDao;
import com.example.backendnodejsvuedemo01.dao.CategoryDao;
import com.example.backendnodejsvuedemo01.pojo.Book;
import com.example.backendnodejsvuedemo01.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Nick
 * @Classname BookService
 * @Date 2021/8/5 15:42
 * @Description
 */

@Service
public class BookService {
    @Autowired
    BookDao bookDao;

    @Autowired
    CategoryService categoryService;

    //查出所有书籍，id降序排序
    public List<Book> list(){
        //通過id降序排序
        Sort id = Sort.by(Sort.Direction.DESC,"id");
//        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "id");
//        Sort id = Sort.by(order);
        return bookDao.findAll(id);
    }

    //增加或者更新书籍
    public void addOrUpdate(Book book){
//        save() 方法的作用是，当主键存在时更新数据，当主键不存在时插入数据
        bookDao.save(book);
    }

    //删除书籍
    public void deleteById(int id){
        bookDao.deleteById(id);
    }

    //通过分类查出书籍
    public List<Book> listByCategory(int cid){
        Category category = categoryService.get(cid);
        return bookDao.findAllByCategory(category);
    }

//    根据标题或作者进行模糊查询
//    因为 DAO 里是两个参数，所以在 Service 里把同一个参数写了两遍。用户在搜索时无论输入的是作者还是书名，都会对两个字段进行匹配
    public List<Book> Search(String keywords){
        return bookDao.findALlByTitleLikeOrAuthorLike('%' + keywords + '%', '%' + keywords + '%');
    }
}
