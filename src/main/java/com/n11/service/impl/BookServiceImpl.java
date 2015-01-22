package com.n11.service.impl;

import com.n11.domain.Book;
import com.n11.repository.BookRepository;
import com.n11.service.BookService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Created by mozen on 1/18/2015.
 */
@Service("bookService")
@Slf4j
@Setter
@Getter
public class BookServiceImpl implements BookService, Serializable {

    @Inject
    private BookRepository bookRepository;

    @Override
    public Book getBook(String id) {
        log.debug("getBook({})", id);
        return bookRepository.findOne(id);
    }

    @Override
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @Override
    public void delete(String id) {
        Book foundBook = getBook(id);
        if (foundBook != null) {
            bookRepository.delete(foundBook);
        }

    }

    @Override
    public void delete(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void update(Book book) {
        delete(book);
        save(book);
    }

    @Override
    public void deleteAll() {
        bookRepository.deleteAll();
    }


}
