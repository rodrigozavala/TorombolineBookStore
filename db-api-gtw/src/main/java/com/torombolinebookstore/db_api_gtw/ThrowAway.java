package com.torombolinebookstore.db_api_gtw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ThrowAway {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ThrowAway(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void performDatabaseOperation() {
        String sql = "SELECT * FROM your_table";
        jdbcTemplate.query(sql, (rs, rowNum) -> {
            //process results
            System.out.println(rs);
            return null;
        });
    }
}
