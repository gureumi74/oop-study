package com.example.oopstudy.opgame.model;

import javax.xml.namespace.QName;
import java.sql.*;

public class DBMain {
    public static void main(String[] args) {
        initPerson();
    }

    private static void initPerson() {
        String sql = "select code, name from country order by code, name";
        // 하나의 논리적 데이터베이스 트랜잭션을 나타내는 클래스
        Connection conn = null;
        // sql 구문의 실행을 담당
        Statement smt = null;
        // 미리 준비된 SQL 문을 실행하기 위한 PreparedStatement 객체를 생성
        PreparedStatement psmt = null;
        // 데이터베이스 질의를 통해서 얻어진 데이터 결과값의 행을 나타내는 객체
        ResultSet rs = null;
        try {
            // 드라이버 리스트를 저장하고 요청된 URL에 해당하는 드라이버를 애플리케이션에 제공(world.db에 연결)
            conn = DriverManager.getConnection("jdbc:sqlite:world.db");
            // SQL문을 실행하기 위한 statement 객체를 생성
            smt = conn.createStatement();
            smt.setQueryTimeout(30); // set timeout to 30 sec
            // select 문을 실행하고 결과를 ResultSet 객체로 반환
//            rs = psmt.executeQuery();

            // insert, update, delete 등의 sql문을 실행하고 영향을 받은 행의 수를 반환
            // world.db에 차례대로 쿼리를 보낸다.
            // world.db에 'person'이라는 Table이 존재하면 일단 지우고
            smt.executeUpdate("drop  table if exists person");
            // id와 name column을 가지고 있는 'person' table 생성
            smt.executeUpdate("create table person (id integer, name string)");
            // id = 1, name = 'leo' 데이터 삽입
            smt.executeUpdate("insert into person values(1, 'leo')");
            // id = 2, name = 'yui' 데이터 삽입
            smt.executeUpdate("insert into person values(2, 'yui')");
            // 'person' table 조회해서 rs에 넘겨준다.
            rs = smt.executeQuery("select * from person");

            while (rs.next()) {
                // 넘겨받은 data를 출력해서 확인
                System.out.println("name = " + rs.getString("name"));
                System.out.println("id = " + rs.getString("id"));
            }

            // 에러 처리 코드
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                // 사용한 JDBC 객체 (ResultSet, Statement, Connection 등)을 닫고 관련된 리소스를 해제한다.
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
