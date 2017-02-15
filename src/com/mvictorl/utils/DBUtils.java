package com.mvictorl.utils;

import com.mvictorl.beans.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {
    /*~~~~~ USER database tools ~~~~*/
    public static User findUser(Connection conn, String userName, String password) throws SQLException {

        String sql = "SELECT u.idUsers, u.role_id, u.woker, r.idRole, r.nameRole " +
                    "FROM users u LEFT OUTER JOIN roles r " +
                    "ON u.role_id = r.idRole " +
                    "WHERE u.nameUser = ? AND u.userPassword = ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            int idUsers = rs.getInt("idUsers");
            int role_id = rs.getInt("role_id");
            int woker =rs.getInt("woker");
            int idRole = rs.getInt("idRole");
            String nameRole = rs.getString("nameRole");

            Role role = new Role();
            role.setId(idRole);
            role.setName(nameRole);

            User user = new User();
            user.setId(idUsers);
            user.setName(userName);
            user.setPassword(password);
            user.setRole(role);
            user.setWoker(woker);
            return user;
        }
        return null;
    }

    public static User findUser(Connection conn, String userName) throws SQLException {

        String sql = "SELECT u.idUsers, u.userPassword, u.role_id, u.woker, r.idRole, r.nameRole " +
                "FROM users u LEFT OUTER JOIN roles r " +
                "ON u.role_id = r.idRole " +
                "WHERE u.nameUser = ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            int idUsers = rs.getInt("idUsers");
            String userPassword = rs.getString("userPassword");
            int role_id = rs.getInt("role_id");
            int woker = rs.getInt("woker");
            int idRole = rs.getInt("idRole");
            String nameRole = rs.getString("nameRole");

            Role role = new Role();
            role.setId(idRole);
            role.setName(nameRole);

            User user = new User();
            user.setId(idUsers);
            user.setPassword("userPassword");
            user.setName(userName);
            user.setRole(role);
            user.setWoker(woker);
            return user;
        }
        return null;
    }

    /*~~~~~ Employee (Woker) database tools ~~~~*/
    public static Woker findWoker(Connection conn, int id) throws SQLException {
        String sql = "SELECT e.surnameEmployee, e.nameEmployee, e.patronymicEmployee, " +
                "e.parent_id, e.user_exist, d.idDivision, d.nameDivision, d.filial_id " +
                "FROM employees e LEFT OUTER JOIN division d " +
                "ON e.division_id = d.idDivision " +
                "WHERE e.idEmployees = ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, Integer.toString(id));
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            String surname = rs.getString("surnameEmployee");
            String name = rs.getString("nameEmployee");
            String patronymic = rs.getString("patronymicEmployee");
            int parent = rs.getInt("parent_id");
            int idDivision = rs.getInt("idDivision");
            boolean user_exist = rs.getString("user_exist") == "Y";
            String nameDivision = rs.getString("nameDivision");
            int filial_id = rs.getInt("filial_id");

            Division division = new Division();
            division.setId(idDivision);
            division.setName(nameDivision);
            division.setFilial_id(filial_id);

            Woker woker = new Woker();
            woker.setId(id);
            woker.setSurname(surname);
            woker.setName(name);
            woker.setPatronymic(patronymic);
            woker.setParent(parent);
            woker.setDivision(division);

            return woker;
        }
        return null;
    }
}