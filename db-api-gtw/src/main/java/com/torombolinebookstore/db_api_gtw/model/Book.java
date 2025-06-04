package com.torombolinebookstore.db_api_gtw.model;

import lombok.Data;
import lombok.NonNull;

import java.sql.Timestamp;
import java.time.LocalDate;
@Data
public class Book {
    // to convert these to
    // java https://dev.mysql.com/doc/connector-j/en/connector-j-reference-type-conversions.html
    @NonNull
    private long book_id;
    @NonNull
    private String title; //VARCHAR(255)
    @NonNull
    private long author_id; //INT
    @NonNull
    private long category_id ; //INT
    @NonNull
    private float price;// DECIMAL(10, 2),
    @NonNull
    private String description; //VARCHAR(255),
    @NonNull
    private String isbn;//VARCHAR(13),
    @NonNull
    private LocalDate publication_date;// DATE,
    @NonNull
    private String cover_image_url;// VARCHAR(255),
    @NonNull
    private long stock_quantity;// INT
    private Timestamp created_at;// TIMESTAMP,
    private Timestamp updated_at;// TIMESTAMP,
}
