package com.torombolinebookstore.authentication.dao;

import com.torombolinebookstore.authentication.model.User;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface UserDAOInterface {

    public User findUserByEmail(String email) throws SQLException;
    public void saveUser(User user) throws SQLException;
    public List<User> findUsersByEmail(Collection<String> emails) throws SQLException;

}
