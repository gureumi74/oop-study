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
        dao.initMember();
        Optional<Member> user = dao.login(id, pw);
        if(user.isEmpty()) {
            dao.join(id, pw);
            user = dao.login(id, pw);
        }

        assertEquals(id, user.get().getId());
        assertTrue(user.get().isLogined());

        user = dao.logout(id);
        assertFalse(user.get().isLogined());
    }
}