package com.example.oopstudy.opgame.model;
import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;

public class BaseDAO {
    protected Connection conn = null;
    protected Statement smt = null;
    protected PreparedStatement psmt = null;
    protected ResultSet rs = null;

    protected void getConn() {
        // 호스트 정보를 변수로 설정
        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String url = dbHost;
        String user = "";
        String pw = "";

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void close() {
        try {
            if(rs != null) rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if(smt != null) smt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if(psmt != null) psmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if(conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
