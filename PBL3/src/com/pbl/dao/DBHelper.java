/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pbl.dao;

import com.mysql.cj.xdevapi.PreparableStatement;
import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class DBHelper {

    private static DBHelper instance;
    private Connection con;
//
//    private static final String URL = "jdbc:mysql://127.0.0.1:3306/schedulestudyui?useSSL=false&serverTimezone=Asia/Ho_Chi_Minh&allowPublicKeyRetrieval=true";
//    private static final String USER = "daiviet";
//    private static final String PASSWORD = "123";
    private static final String URL = "jdbc:mysql://10.10.180.60:3306/schedulestudyui?useSSL=false&serverTimezone=Asia/Ho_Chi_Minh&allowPublicKeyRetrieval=true";
    private static final String USER = "vietngaover2";
    private static final String PASSWORD = "123";	
    private DBHelper() {
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DBHelper getInstance() {
        if (instance == null) {
            synchronized (DBHelper.class) {
                if (instance == null) {
                    instance = new DBHelper();
                }
            }
        }
        return instance;
    }

    public ResultSet getRecords(String sql, Object... params) throws SQLException {
        PreparedStatement stmt = con.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
        return stmt.executeQuery();
    }

    public int executeUpdate(String sql, Object... params) throws SQLException {
        PreparedStatement stmt = con.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
        return stmt.executeUpdate();
    }

    public Connection getConnection() {
        return con;
    }

    public void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
