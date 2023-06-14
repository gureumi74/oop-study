package com.example.oopstudy.opgame.model;

import java.sql.SQLException;
import java.util.Optional;

public class MemberDAO extends BaseDAO {
    public void initMember() {
        String sql = """
                CREATE TABLE opg_member (
                seq INTEGER PRIMARY KEY AUTOINCREMENT,\s
                id TEXT NOT NULL,
                pw TEXT NOT NULL,
                islogined INTEGER DEFAULT '0',
                created_at TEXT DEFAULT CURRENT_TIMESTAMP,
                updated_at TEXT DEFAULT CURRENT_TIMESTAMP)
                """;
        // 예외처리
        try {
            getConn();
            smt = conn.createStatement();
            // 안에 동일한 테이블이 있다면 삭제
            smt.executeUpdate("DROP TABLE IF EXISTS opg_member");
            smt.executeUpdate(sql);
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            close();
        }
    }

    public Optional<Member> login(String id, String pw) {
        // 테이블에서 id와 pw 값이 일치하는 행의 islogined 열을 1로 업데이트 하는 역할
        // 즉 해당 사용자의 로그인 상태를 나타내는 islogined 값을 1ㄹ로 설정하는 업데이트 작업
        String sql = """
                update opg_member SET islogined='1' WHERE id=? and pw=?
                """;
        try {
            getConn(); // DB 연결 설정
            psmt = conn.prepareStatement(sql); // 준비된 문장 생성
            psmt.setString(1, id); // 첫 번째 매개변수를 id로 설정
            psmt.setString(2, pw); // 두 번째 매개변수를 pw로 설정
            int cnt = psmt.executeUpdate(); // SQL 쿼리 실행하고 업데이트된 행 수를 반환
            if(cnt > 0) { // 하나라도 업데이트가 됐다면
                // 사용자 정보를 가져오는 새로운 쿼리문을 생성
                psmt = conn.prepareStatement("SELECT id, islogined FROM opg_member WHERE id=?");
                psmt.setString(1, id); // 매개변수를 id로 설정
                rs = psmt.executeQuery(); // 쿼리 실행하고 결과 집합을 받음
                if(rs.next()) { // 결과 집합에 데이터가 있다면
                    String rid = rs.getString(1); // 첫 번째 열의 값을 가져옴 (id)
                    boolean risLogined = rs.getBoolean(2); // 두 번째 열의 값을 가져옴 (islogined)
                    return Optional.of(new Member(rid, risLogined)); // 새 Member 객체를 Optional로 반환
                }
            }
        }
        // 예외 처리
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            close();
        }
        // 로그인이 실패한 경우 빈 옵셔널 반환
        return Optional.empty();
    }

    public int join(String id, String pw) {
        int cnt = 0;
        String sql = "insert into opg_member(id, pw) values(?, ?)";
        try {
            getConn();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, id);
            psmt.setString(2, pw);
            cnt = psmt.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            close();
        }
        // 쿼리를 실행하고 업데이트된 열의 수를 반환
        return cnt;
    }

    public Optional<Member> logout(String id) {
        // 테이블에서 주어진 id 값과 일치하는 행의 islogined열을 0으로 업데이트
        String sql = """
                update opg_member SET islogined='0' WHERE id=?
                """;
        try {
            getConn();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, id);
            int cnt = psmt.executeUpdate();
            if(cnt > 0) {
                psmt = conn.prepareStatement("SELECT id, islogined FROM opg_member WHERE id=?");
                psmt.setString(1, id);
                rs = psmt.executeQuery();
                if(rs.next()) {
                    String rid = rs.getString(1);
                    boolean risLogined = rs.getBoolean(2);
                    return Optional.of(new Member(rid, risLogined));
                }
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            close();
        }
        return Optional.empty();
    }
}
