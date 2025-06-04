package com.torombolinebookstore.authentication.dao;

import com.torombolinebookstore.authentication.dbconnection.DBConnectionProvider;
import com.torombolinebookstore.common_models.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Collection;
import java.util.List;

@Component
public class UserDAO implements UserDAOInterface {
    private static final String QUERY_USER_BY_EMAIL = "SELECT * FROM TOROMBOLINE_BOOK_STORE.USERS WHERE EMAIL = ?";

    private static final  String INSERT_USER = "INSERT INTO TOROMBOLINE_BOOK_STORE.USERS ( email, salt, password_hash, created_at)\n" +
            "VALUES \n" +
            "( ?, ?, ?, NOW())\n";
    @Autowired
    private DBConnectionProvider dbConnectionProvider;


    public synchronized User findUserByEmail(String email) throws SQLException {
        Connection con =  dbConnectionProvider.getConnection();
        try {
            PreparedStatement stm = con.prepareStatement(QUERY_USER_BY_EMAIL);

            stm.setString(1,email);
            ResultSet rs =  stm.executeQuery();
            User user = null;
            while (rs.next()){
                user = new User(rs.getBytes("user_id"),
                        rs.getNString("email"),
                        rs.getNString("salt"),
                        rs.getNString("password_hash"),
                        rs.getTimestamp("created_at"));//2038-01-19 03:14:07

                System.out.println("STEP 4 " + user.toString());
            }
            return user;
        }catch (Exception e){
            //TODO: fix this logging message
            System.out.println("This failed: "+e.getStackTrace().toString()+" "+e.getCause());
        }finally {
            con.close();
        }
        return null;
    }

    public synchronized void saveUser(User user) throws SQLException{
        Connection con =  dbConnectionProvider.getConnection();
        try{
            PreparedStatement pstm = con.prepareStatement(INSERT_USER);
            pstm.setString(1,user.getEmail());
            pstm.setString(2,user.getSalt());
            pstm.setString(3,user.getPasswordHash());
            pstm.execute();
        }catch (Exception e){
            //TODO: fix this logging message
            System.out.println("This failed: "+e.getStackTrace().toString()+" "+e.getCause()+" "+e.getMessage());
        }finally{
            con.close();
        }
    }

    public synchronized List<User> findUsersByEmail(Collection<String> emails) throws SQLException {
        Connection con =  dbConnectionProvider.getConnection();
        try{

            PreparedStatement stm = con.prepareStatement(QUERY_USER_BY_EMAIL);
        }catch (Exception e){
            //TODO: fix this logging message
            System.out.println("This failed: "+e.getStackTrace().toString()+" "+e.getCause());
        }finally {
            con.close();
        }
        return null;
    }
}
