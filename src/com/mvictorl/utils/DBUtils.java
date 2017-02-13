package com.mvictorl.utils;

import com.mvictorl.beans.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {
    /*~~~~~ USER database tools ~~~~*/
    public static User findUser(Connection conn, String userName, String password) throws SQLException {

        String sql = "SELECT a.User_Name, a.Password, a.Gender FROM User_Account a "
                + " WHERE a.User_Name = ? AND a.password= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            String gender = rs.getString("Gender");
            User user = new User();
            user.setName(userName);
            user.setPassword(password);
            return user;
        }
        return null;
    }

    public static User findUser(Connection conn, String userName) throws SQLException {

        String sql = "SELECT a.User_Name, a.Password, a.Gender FROM User_Account a " + " WHERE a.User_Name = ? ";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            String password = rs.getString("Password");
            String gender = rs.getString("Gender");
            User user = new User();
            user.setName(userName);
            user.setPassword(password);
            return user;
        }
        return null;
    }
}
