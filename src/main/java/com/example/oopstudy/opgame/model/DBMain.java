package com.example.oopstudy.opgame.model;

import javax.xml.namespace.QName;
import java.sql.*;

public class DBMain extends BaseDAO {
    public static void main(String[] args) {
        DBMain dbMain = new DBMain();
        dbMain.initPerson();
    }

    private void initPerson() {
        String sql =
                "create table person (id integer primary key autoincrement, name string)";
        try {
            getConn();
            // SQL문을 실행하기 위한 statement 객체를 생성
            smt = conn.createStatement();
            smt.setQueryTimeout(30); // set timeout to 30 sec.
            // insert, update, delete 등의 sql문을 실행하고 영향을 받은 행의 수를 반환
            // world.db에 차례대로 쿼리를 보낸다.
            // world.db에 'person'이라는 Table이 존재하면 일단 지우고
            smt.executeUpdate("drop  table if exists person");
            // id와 name column을 가지고 있는 'person' table 생성
            smt.executeUpdate(sql);
            // id = 1, name = 'leo' 데이터 삽입
            smt.executeUpdate("insert into person(name) values('leo')");
            // id = 2, name = 'yui' 데이터 삽입
            smt.executeUpdate("insert into person(name) values('yui')");
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
            close();
        }
    }
    private void getCodeName() {
        String sql = "select code, name from country order by code, name";
        try {
            getConn();

            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("code") + " ");
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }
}
