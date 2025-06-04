package com.torombolinebookstore.authentication.dbconnection;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBConnectionProviderInterface {

    public Connection getConnection () throws SQLException;

}
