package com.torombolinebookstore.db_api_gtw.dao;

import com.torombolinebookstore.db_api_gtw.dbconnection.DBConnectionProvider;
import com.torombolinebookstore.db_api_gtw.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;

@Component
public class AuthorDAO implements AuthorDAOInterface{
    private static String QUERY_AUTHOR_BY_ID = "SELECT * FROM TOROMBOLINE_BOOK_STORE.AUTHOR_PROFILES WHERE AUTHOR_ID = ?";

    @Autowired
    private DBConnectionProvider dbConnectionProvider;
    public Author getAuthorById(long id) {
        try {
            Connection con =  dbConnectionProvider.getConnection();
            PreparedStatement stm = con.prepareStatement(QUERY_AUTHOR_BY_ID);
            stm.setLong(1,id);
            ResultSet rs =  stm.executeQuery();
            Author author = null;
            while (rs.next()){
                author = new Author(id,
                        rs.getNString("first_name"),
                        rs.getNString("last_name"),
                        rs.getNString("biography"));

                // TODO fix this conversion

                //author.setCreated_at(Timestamp.valueOf(rs.getNString("created_at")));
                //author.setUpdated_at(Timestamp.valueOf(rs.getNString("updated_at")));
                System.out.println("STEP 4 ");
            }
            return author;
        }catch (Exception e){
            //TODO: fix this logging message
            System.out.println("This failed: "+e.getStackTrace().toString()+" "+e.getCause());
        }
        return null;
    }
    public List<Author> getAllAuthorsById(long id){

        return null;
    }
}
