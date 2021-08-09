package com.example.backendnodejsvuedemo01.controller;

import com.example.backendnodejsvuedemo01.pojo.Book;
import com.example.backendnodejsvuedemo01.service.BookService;
import com.example.backendnodejsvuedemo01.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

    @GetMapping("/api/search")
    public List<Book> searchResult(@RequestParam("keywords") String keywords){
        //keywords为空是查询出所有的书籍
        if("".equals(keywords)){
            return bookService.list();
        }else {
            return bookService.Search(keywords);
        }
    }

//    这里涉及到对文件的操作，对接收到的文件重命名，但保留原始的格式。
//    可以进一步做一下压缩，或者校验前端传来的数据是否为指定格式
    @PostMapping("/api/covers")
    public String coversUpload(MultipartFile file)throws Exception{
        String folder = "d:/WorkSpace/nodejs/img";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, StringUtils.getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        // 解决路径不存在问题
        if(!f.getParentFile().exists()){
            f.getParentFile().mkdirs();
        }
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:8443/api/file/" + f.getName();
            return imgURL;
        }catch (IOException e){
            e.printStackTrace();
            return "";
        }
    }
}
