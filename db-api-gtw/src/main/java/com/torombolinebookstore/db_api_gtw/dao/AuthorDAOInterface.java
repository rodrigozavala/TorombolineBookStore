package com.torombolinebookstore.db_api_gtw.dao;

import com.torombolinebookstore.db_api_gtw.model.Author;

import java.util.List;

public interface AuthorDAOInterface {

    public Author getAuthorById(long id);

    public List<Author> getAllAuthorsById(long id);
}
