package com.mvictorl.utils;

import com.mvictorl.beans.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {
    /*~~~~~ USER database tools ~~~~*/
    public static User findUser(Connection conn, String userName, String password) throws SQLException {

        String sql = "SELECT a.idUsers, a.nameUser, a.userPassword, a.role_id FROM users a "
                   + "WHERE a.nameUser = ? AND a.userPassword = ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            int idUsers = rs.getInt("idUsers");
            int role_id = rs.getInt("role_id");
            User user = new User();
            user.setId(idUsers);
            user.setName(userName);
            user.setPassword(password);
            user.setRole(role_id);
            return user;
        }
        return null;
    }

    public static User findUser(Connection conn, String userName) throws SQLException {

        String sql = "SELECT a.idUsers, a.nameUser, a.userPassword, a.role_id FROM users a "
                   + "WHERE a.nameUser = ? ";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            int idUsers = rs.getInt("idUsers");
            String password = rs.getString("userPassword");
            int role_id = rs.getInt("role_id");
            User user = new User();
            user.setId(idUsers);
            user.setName(userName);
            user.setPassword(password);
            user.setRole(role_id);
            return user;
        }
        return null;
    }
}
