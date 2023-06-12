package com.example.oopstudy.ooptest.model;

import java.sql.*;

public class DBMain {
    public static void main(String[] args) {
        initPerson();
    }

    private static void initPerson() {
        String sql = "select code, name from country order by code, name";
        // 하나의 논리적 데이터베이스 트랜잭션을 나타내는 클래스
        Connection conn = null;
        PreparedStatement psmt = null;
        // 데이터베이스 질의를 통해서 얻어진 데이터 결과값의 행을 나타내는 객체
        ResultSet rs = null;
        try {
            // 드라이버 리스트를 저장하고 요청된 URL에 해당하는 드라이버를 애플리케이션에 제공(world.db에 연결)
            conn = DriverManager.getConnection("jdbc:sqlite:world.db");
            // sql 구문의 실행을 담당
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("code") + " ");
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (psmt != null) psmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
