package com.example.oopstudy.opgame.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonDAO extends BaseDAO {
    public Optional<Person> findByNamePerson(String pname) {
        List<Person> result = new ArrayList<>();
        String sql = "select id, name from person where name = ?";

        try {
            getConn();
            // SQL문을 실행하기 위한 statement 객체를 생성
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, pname);
            rs = psmt.executeQuery();
            while (rs.next()) {
                int id = (rs.getInt("id"));
                String name = rs.getString("name");
                return Optional.of(new Person(id, name)); // Optional.of 사용
            }
            // 에러 처리 코드
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            close();
        }
        // 여기까지 왔을 경우 데이터가 없다는 뜻이니까
        return Optional.empty();
    }
    public List<Person> findAllPerson() {
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
    public int insertPerson(String name) {
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

    public void initPerson() {
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
}
