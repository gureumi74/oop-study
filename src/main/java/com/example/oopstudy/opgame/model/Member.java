package com.example.oopstudy.opgame.model;

public class Member {
    // Member의 id와 로그인 여부 저장
    String id;
    boolean isLogined;

    public Member(String id, boolean isLogined) {
        this.id = id;
        this.isLogined = isLogined;
    }

    public String getId() {
        return id;
    }

    public boolean isLogined() {
        return isLogined;
    }
}
