package com.n11.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created by mozen on 1/18/2015.
 */
@Data
@Document
public class Book implements Serializable {

    @Id
    private String id;

    private String name;

    private String author;
}
