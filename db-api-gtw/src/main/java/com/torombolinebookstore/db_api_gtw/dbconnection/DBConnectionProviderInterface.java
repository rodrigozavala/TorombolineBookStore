package com.torombolinebookstore.db_api_gtw.dbconnection;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBConnectionProviderInterface {

    public Connection getConnection () throws SQLException;

}
