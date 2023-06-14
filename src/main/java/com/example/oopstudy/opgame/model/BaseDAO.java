package com.example.oopstudy.opgame.model;
import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;

public class BaseDAO {
    // 하나의 논리적 데이터베이스 트랜잭션을 나타내는 클래스
    protected Connection conn = null;
    // sql 구문의 실행을 담당
    protected Statement smt = null;
    // 미리 준비된 SQL 문을 실행하기 위한 PreparedStatement 객체를 생성
    protected PreparedStatement psmt = null;
    // 데이터베이스 질의를 통해서 얻어진 데이터 결과값의 행을 나타내는 객체
    protected ResultSet rs = null;

    protected void getConn() {
        // 호스트 정보를 변수로 설정

//        Map<String, String> env = getenv();
//        String dbHost = env.get("DB_HOST");
        String url = "jdbc:sqlite:world.db";
        String user = "";
        String pw = "";

        // 드라이버 리스트를 저장하고 요청된 URL에 해당하는 드라이버를 애플리케이션에 제공(world.db에 연결)
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
