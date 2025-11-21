package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {

    // THAY YOUR_SERVER_NAME THÀNH TÊN SQL SERVER CỦA BẠN (ví dụ: localhost)
    private final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=Login;encrypt=true;trustServerCertificate=true;";
    private final String USER = "vuthibac1106"; 
    private final String PASS = "200539Huy"; 

    public Connection getConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.err.println("Lỗi kết nối CSDL: " + e.getMessage());
            throw new SQLException("Không thể kết nối CSDL.");
        }
        return conn;
    }
}