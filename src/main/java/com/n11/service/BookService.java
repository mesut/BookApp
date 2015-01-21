package com.n11.service;

import com.n11.domain.Book;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by mozen on 1/18/2015.
 */
public interface BookService {
    
    public Book getBook(String id);

    public List<Book> getAllBook();

    public void delete(String id);

    public void save(Book book);

    public void update(Book book);
    
    public void deleteAll();

}
