package com.example.oopstudy.opgame.model;

import javax.xml.namespace.QName;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBMain extends BaseDAO {
    public static void main(String[] args) {
        DBMain dbMain = new DBMain();
        dbMain.initPerson();
        System.out.println(dbMain.insertPerson("leo"));
        System.out.println(dbMain.insertPerson("yui"));
        List<Person> personList = dbMain.findAllPerson();
        System.out.println(personList.toString());
    }
    private List<Person> findAllPerson() {
        List<Person> result = new ArrayList<>();
        String sql = "select id, name from person";

        try {
            getConn();
            // SQL문을 실행하기 위한 statement 객체를 생성
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();
            while (rs.next()) {
                int id = (rs.getInt("id"));
                String name = rs.getString("name");
                result.add(new Person(id, name));
            }
            // 에러 처리 코드
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            close();
        }
        return result;
    }
    private int insertPerson(String name) {
        int cnt = 0;
        String sql = "insert into person(name) values(?)";

        try {
            getConn();
            // SQL문을 실행하기 위한 statement 객체를 생성
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, name);
            cnt = psmt.executeUpdate();
            // 에러 처리 코드
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            close();
        }
        return cnt;
    }

    private void initPerson() {
        String sql =
                "create table person (id integer primary key autoincrement, name string)";
        try {
            getConn();
            // SQL문을 실행하기 위한 statement 객체를 생성
            smt = conn.createStatement();
            // insert, update, delete 등의 sql문을 실행하고 영향을 받은 행의 수를 반환
            // world.db에 차례대로 쿼리를 보낸다.
            // world.db에 'person'이라는 Table이 존재하면 일단 지우고
            smt.executeUpdate("drop  table if exists person");
            // id와 name column을 가지고 있는 'person' table 생성
            smt.executeUpdate(sql);

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
