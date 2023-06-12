package com.example.oopstudy.opgame.model;

import java.sql.SQLException;

public class CountryDAO extends BaseDAO {
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
