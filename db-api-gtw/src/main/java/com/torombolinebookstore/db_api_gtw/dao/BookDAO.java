package com.torombolinebookstore.db_api_gtw.dao;

import com.torombolinebookstore.db_api_gtw.dbconnection.DBConnectionProvider;
import com.torombolinebookstore.db_api_gtw.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component
public class BookDAO implements BookDAOInterface {
    public String QUERY_BOOK_BY_ID = "SELECT * FROM TOROMBOLINE_BOOK_STORE.BOOKS WHERE BOOK_ID = ?";

    @Autowired
    private DBConnectionProvider dbConnectionProvider;

    public Book getBookById(long id){
        try {
            Connection con =  dbConnectionProvider.getConnection();
            PreparedStatement stm = con.prepareStatement(QUERY_BOOK_BY_ID);
            stm.setLong(1,id);
            ResultSet rs =  stm.executeQuery();
        }catch (Exception e){
            //TODO  fix this logging message
            System.out.println("This failed");
        }
        return null;
    }
    public void addBook(Book bookInfo){

    }
    public Book removeBookById(long id){
        return null;

    }
}
