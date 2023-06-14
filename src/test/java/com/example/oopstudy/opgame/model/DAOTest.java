package com.example.oopstudy.opgame.model;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DAOTest {

    @Test
    public void testLoginAndLogout() {
        String id = "tester";
        String pw = "1234";

        MemberDAO dao = new MemberDAO();
        dao.initMember(); // DB 초기화
        Optional<Member> user = dao.login(id, pw); // 로그인 되면 user에 저장
        if(user.isEmpty()) { // user가 null일 경우 가입을 요청
            dao.join(id, pw);
            user = dao.login(id, pw);
        }

        assertEquals(id, user.get().getId());
        assertTrue(user.get().isLogined()); // 유저 로그인 확인

        user = dao.logout(id); // 유저 로그아웃 처리
        assertFalse(user.get().isLogined()); // 유저 로그아웃 확인
    }
}