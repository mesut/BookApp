package com.n11;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.n11.domain.Book;
import com.n11.repository.BookRepository;
import com.n11.service.impl.BookServiceImpl;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections.CollectionUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by mozen on 1/22/2015.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/webapp-config.xml")
@Setter
@Getter
public class BookControllerMockTest {

    private MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Autowired
    protected BookRepository bookRepository;

    @Before
    public void init() {
        this.mockMvc = webAppContextSetup(this.webApplicationContext).build();
        Book book = new Book();
        book.setName("Mesut");
        book.setAuthor("Özen");

        this.bookRepository.save(book);

    }

    @Test
    public void getBookListTest() throws Exception {
        mockMvc.perform(get("/books/booklist.json")).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists());
    }

    @Test
    @DirtiesContext
    public void saveBookTest() throws Exception {
        Book book = new Book();
        book.setName("Test insert name");
        book.setAuthor("Test insert author");

        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(book);
        mockMvc.perform(post("/books/createBook").content(content).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    public void updateBookTest() throws Exception {
        List<Book> bookList = this.bookRepository.findAll();
        if (CollectionUtils.isNotEmpty(bookList)) {
            Book book = bookList.get(0);
            book.setName("Test Updated Book");
            ObjectMapper objectMapper = new ObjectMapper();
            String content = objectMapper.writeValueAsString(book);
            mockMvc.perform(put("/books/updateBook").content(content)
                    .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        }
    }

    @After
    public void deleteAllBook() throws Exception {
        mockMvc.perform(delete("/books/deleteAllBook")).andExpect(status().isOk());

    }


}
