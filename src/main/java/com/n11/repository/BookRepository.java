package com.n11.repository;

import com.n11.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;


/**
 * Created by mozen on 1/18/2015.
 */
@Repository
public interface BookRepository extends CrudRepository<Book,String>,MongoRepository<Book,String>{
}
