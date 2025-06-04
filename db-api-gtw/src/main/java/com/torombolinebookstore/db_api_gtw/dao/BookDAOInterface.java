package com.torombolinebookstore.db_api_gtw.dao;

import com.torombolinebookstore.db_api_gtw.model.Book;

public interface BookDAOInterface {
    public Book getBookById(long id);
    public void addBook(Book bookInfo);
    public Book removeBookById(long id);
}
