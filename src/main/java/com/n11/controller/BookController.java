package com.n11.controller;

import com.n11.domain.Book;
import com.n11.service.BookService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * Created by mozen on 1/18/2015.
 */

@Controller
@RequestMapping("/books")
@Setter
public class BookController implements Serializable {
    
    @Autowired
    private BookService bookService;
    
    @RequestMapping("booklist.json")
    public @ResponseBody List<Book> getBookList(){
        return bookService.getAllBook();
    }
    @RequestMapping(value = "/createBook",method = RequestMethod.POST)
    public @ResponseBody void createBook(@RequestBody Book book){
            bookService.save(book);        
    }
    @RequestMapping(value = "/updateBook",method = RequestMethod.PUT)
    public @ResponseBody  void updateBook(@RequestBody Book book){
        bookService.update(book);
    }
    @RequestMapping(value = "/deleteBook/{id}",method = RequestMethod.DELETE)
    public @ResponseBody void deleteBook(@PathVariable("id")String id){
        bookService.delete(id);
    }
    @RequestMapping(value = "/deleteAllBook",method = RequestMethod.DELETE)
    public @ResponseBody void deleteAllBook(){
        bookService.deleteAll();
    }
    
}
