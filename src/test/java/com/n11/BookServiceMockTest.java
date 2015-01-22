package com.n11;

import com.n11.domain.Book;
import com.n11.repository.BookRepository;
import com.n11.service.impl.BookServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * Created by mozen on 1/21/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class BookServiceMockTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;


    @Before
    public void init() {

    }

    @Test
    public void saveTest() throws Exception {
        bookService.save(any(Book.class));
        verify(bookService.getBookRepository()).save(any(Book.class));
    }

    @Test
    public void getAllBookTest() throws Exception {
        bookService.getAllBook();
        verify(bookService.getBookRepository()).findAll();
    }

    @Test
    public void deleteBookTest() throws Exception {
        bookService.delete(any(Book.class));
        verify(bookService.getBookRepository()).delete(any(Book.class));
    }

    @Test
    public void updateBookTest() throws Exception {
        bookService.update(any(Book.class));
        verify(bookService.getBookRepository()).save(any(Book.class));
    }

    @Test
    public void deleteAllBookTest() throws Exception {
        bookService.deleteAll();
        verify(bookService.getBookRepository()).deleteAll();

    }

}
