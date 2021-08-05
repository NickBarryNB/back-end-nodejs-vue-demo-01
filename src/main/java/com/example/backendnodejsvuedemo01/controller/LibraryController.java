package com.example.backendnodejsvuedemo01.controller;

import com.example.backendnodejsvuedemo01.pojo.Book;
import com.example.backendnodejsvuedemo01.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Nick
 * @Classname LibraryController
 * @Date 2021/8/5 16:02
 * @Description
 */
@RestController
public class LibraryController {
    @Autowired
    BookService bookService;

    @GetMapping("/api/books")
    public List<Book> list() throws Exception{
        return bookService.list();
    }

    @PostMapping("/api/books")
    public Book addOrUpdate(@RequestBody Book book) throws Exception{
        bookService.addOrUpdate(book);
        return book;
    }

    @PostMapping("/api/delete")
    public void delete(@RequestBody Book book) throws Exception{
        bookService.deleteById(book.getId());
    }

    @GetMapping("/api/categories/{cid}/books")
    public List<Book> listByCategory(@PathVariable("cid") int cid) throws Exception{
        if(cid != 0){
            return bookService.listByCategory(cid);
        }else {
            return list();
        }
    }
}
