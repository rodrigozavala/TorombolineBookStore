package com.torombolinebookstore.db_api_gtw.model;

import lombok.Data;
import lombok.NonNull;

import java.sql.Timestamp;

@Data
public class Author {
    @NonNull
    private long author_id;// INT AUTO_INCREMENT,
    @NonNull
    private String first_name;// VARCHAR(100),
    @NonNull
    private String last_name;// VARCHAR(100),
    @NonNull
    private String biography;// TEXT,
    private Timestamp created_at;// TIMESTAMP,
    private Timestamp updated_at;// TIMESTAMP,
}
