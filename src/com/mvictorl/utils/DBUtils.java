package com.mvictorl.utils;

import com.mvictorl.beans.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {
    /*~~~~~ USER database tools ~~~~*/
    public static User findUser(Connection conn, String userName, String password) throws SQLException {
        String sql = "SELECT u.idUsers, r.idRole, r.nameRole, e.idEmployees, e.surnameEmployee, e.nameEmployee, " +
                "e.patronymicEmployee, d.idDivision, d.nameDivision, d.filial_id " +
                "FROM users u " +
                "LEFT OUTER JOIN roles r ON u.role_id = r.idRole " +
                "LEFT OUTER JOIN employees e ON u.woker = e.idEmployees " +
                "LEFT OUTER JOIN division d ON e.division_id = d.idDivision " +
                "LEFT OUTER JOIN filials f ON d.filial_id = f.idFilial " +
                "WHERE u.nameUser = ? AND u.userPassword = ?";
/*
        String sql = "SELECT u.idUsers, u.role_id, u.woker, r.idRole, r.nameRole " +
                    "FROM users u LEFT OUTER JOIN roles r " +
                    "ON u.role_id = r.idRole " +
                    "WHERE u.nameUser = ? AND u.userPassword = ?";
*/

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            Role role = new Role();
            int idRole = rs.getInt("idRole");
            String nameRole = rs.getString("nameRole");
            role.setId(idRole);
            role.setName(nameRole);

            Division division = new Division();
            int idDivision = rs.getInt("idDivision");
            String nameDivision = rs.getString("nameDivision");
            int idFilial = rs.getInt("filial_id");
            division.setId(idDivision);
            division.setName(nameDivision);
            division.setFilial_id(idFilial);

            Worker worker = new Worker();
            int idWorker = rs.getInt("idEmployees");
            String surnameWorker = rs.getString("surnameEmployee");
            String nameWorker = rs.getString("nameEmployee");
            String patronymicWorker = rs.getString("patronymicEmployee");
            worker.setId(idWorker);
            worker.setSurname(surnameWorker);
            worker.setName(nameWorker);
            worker.setPatronymic(patronymicWorker);
            worker.setDivision(division);

            User user = new User();
            int idUsers = rs.getInt("idUsers");
            user.setId(idUsers);
            user.setName(userName);
            user.setPassword(password);
            user.setRole(role);
            user.setWorker(worker);
            return user;
        }
        return null;
    }

    public static User findUser(Connection conn, String userName) throws SQLException {
        String sql = "SELECT u.idUsers, u.userPassword, r.idRole, r.nameRole, e.idEmployees, e.surnameEmployee, " +
                "e.nameEmployee, e.patronymicEmployee, d.idDivision, d.nameDivision, d.filial_id " +
                "FROM users u " +
                "LEFT OUTER JOIN roles r ON u.role_id = r.idRole " +
                "LEFT OUTER JOIN employees e ON u.woker = e.idEmployees " +
                "LEFT OUTER JOIN division d ON e.division_id = d.idDivision " +
                "LEFT OUTER JOIN filials f ON d.filial_id = f.idFilial " +
                "WHERE u.nameUser = ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            Role role = new Role();
            int idRole = rs.getInt("idRole");
            String nameRole = rs.getString("nameRole");
            role.setId(idRole);
            role.setName(nameRole);

            Division division = new Division();
            int idDivision = rs.getInt("idDivision");
            String nameDivision = rs.getString("nameDivision");
            int idFilial = rs.getInt("filial_id");
            division.setId(idDivision);
            division.setName(nameDivision);
            division.setFilial_id(idFilial);

            Worker worker = new Worker();
            int idWorker = rs.getInt("idEmployees");
            String surnameWorker = rs.getString("surnameEmployee");
            String nameWorker = rs.getString("nameEmployee");
            String patronymicWorker = rs.getString("patronymicEmployee");
            worker.setId(idWorker);
            worker.setSurname(surnameWorker);
            worker.setName(nameWorker);
            worker.setPatronymic(patronymicWorker);
            worker.setDivision(division);

            User user = new User();
            int idUsers = rs.getInt("idUsers");
            String userPassword = rs.getString("userPassword");
            user.setId(idUsers);
            user.setName(userName);
            user.setPassword(userPassword);
            user.setRole(role);
            user.setWorker(worker);
            return user;
        }
        return null;
    }

    public static int getUserIDByWorker(Connection conn, int idWorker) throws SQLException {
        String sql = "SELECT u.idUser FROM users u " +
                "LEFT OUTER JOIN employees e ON u.woker = e.idEmployees " +
                "WHERE e.idEmployees = ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idWorker);
        ResultSet rs = pstm.executeQuery();
        int id = -1;

        while (rs.next()) {
            id = rs.getInt("idUser");
        }
        return id;
    }

    public static List<User> queryUsers(Connection conn, int lvl) throws SQLException {
        String sql = "SELECT u.idUsers, u.nameUser, u.userPassword, r.idRole, r.nameRole, e.idEmployees, " +
                "e.surnameEmployee, e.nameEmployee, e.patronymicEmployee, d.idDivision, d.nameDivision, d.filial_id " +
                "FROM users u " +
                "LEFT OUTER JOIN roles r ON u.role_id = r.idRole " +
                "LEFT OUTER JOIN employees e ON u.woker = e.idEmployees " +
                "LEFT OUTER JOIN division d ON e.division_id = d.idDivision " +
                "LEFT OUTER JOIN filials f ON d.filial_id = f.idFilial " +
                "WHERE r.idRole >= ? " +
                "ORDER BY r.idRole";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, lvl);

        ResultSet rs = pstm.executeQuery();
        List<User> list = new ArrayList<>();
        while (rs.next()) {
            Role role = new Role();
            int idRole = rs.getInt("idRole");
            String nameRole = rs.getString("nameRole");
            role.setId(idRole);
            role.setName(nameRole);

            Division division = new Division();
            int idDivision = rs.getInt("idDivision");
            String nameDivision = rs.getString("nameDivision");
            int idFilial = rs.getInt("filial_id");
            division.setId(idDivision);
            division.setName(nameDivision);
            division.setFilial_id(idFilial);

            Worker worker = new Worker();
            int idWorker = rs.getInt("idEmployees");
            String surnameWorker = rs.getString("surnameEmployee");
            String nameWorker = rs.getString("nameEmployee");
            String patronymicWorker = rs.getString("patronymicEmployee");
            worker.setId(idWorker);
            worker.setSurname(surnameWorker);
            worker.setName(nameWorker);
            worker.setPatronymic(patronymicWorker);
            worker.setDivision(division);

            User user = new User();
            int idUsers = rs.getInt("idUsers");
            String userName = rs.getString("nameUser");
            String userPassword = rs.getString("userPassword");
            user.setId(idUsers);
            user.setName(userName);
            user.setPassword(userPassword);
            user.setRole(role);
            user.setWorker(worker);
            list.add(user);
        }
        return list;
    }

    public static void setUserPassword(Connection conn, int user_id, String new_pass) throws SQLException {
        String sql = "UPDATE users SET userPassword=? WHERE idUsers=? ";
        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, new_pass);
        pstm.setInt(2, user_id);
        pstm.executeUpdate();
    }

    public static void updateUser(Connection conn, int idUser, String pass, int role, int worker) throws SQLException {
        String sql = "UPDATE users SET userPassword=?, role_id=?, woker=? WHERE idUsers=? ";
        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, pass);
        pstm.setInt(2, role);
        pstm.setInt(3, worker);
        pstm.setInt(4, idUser);
        pstm.executeUpdate();
    }

    public static void insertUser(Connection conn, User user) throws SQLException {
        /*String sql = "INSERT INTO Product(Code, Name,Price) VALUES (?,?,?)";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, product.getCode());
        pstm.setString(2, product.getName());
        pstm.setFloat(3, product.getPrice());

        pstm.executeUpdate();*/
    }

    public static void deleteUser(Connection conn, int idUser) throws SQLException {
        /*String sql = "DELETE FROM Product WHERE Code = ? ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, code);

        pstm.executeUpdate();*/
    }

    /*~~~~~ ROLE database tools ~~~~*/
    public static List<Role> queryRoles(Connection conn, int lvl) throws SQLException {
        String sql = "SELECT idRole, nameRole FROM roles WHERE idRole >= ? ORDER BY idRole";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, lvl);

        ResultSet rs = pstm.executeQuery();
        List<Role> list = new ArrayList<>();
        while (rs.next()) {
            Role role = new Role();
            int idRole = rs.getInt("idRole");
            String nameRole = rs.getString("nameRole");
            role.setId(idRole);
            role.setName(nameRole);
            list.add(role);
        }
        return list;
    }

    /*~~~~~ EMPLOYEE (WORKER) database tools ~~~~*/
    public static Worker findWoker(Connection conn, int id) throws SQLException {
        String sql = "SELECT e.surnameEmployee, e.nameEmployee, e.patronymicEmployee, " +
                "e.user_exist, d.idDivision, d.nameDivision, d.filial_id " +
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
            int idDivision = rs.getInt("idDivision");
            boolean user_exist = (rs.getString("user_exist") == "Y");
            String nameDivision = rs.getString("nameDivision");
            int filial_id = rs.getInt("filial_id");

            Division division = new Division();
            division.setId(idDivision);
            division.setName(nameDivision);
            division.setFilial_id(filial_id);

            Worker worker = new Worker();
            worker.setId(id);
            worker.setSurname(surname);
            worker.setName(name);
            worker.setPatronymic(patronymic);
            worker.setDivision(division);

            return worker;
        }
        return null;
    }

    public static List<Worker> queryWorkers(Connection conn) throws SQLException {
        String sql = "SELECT e.idEmployees, e.surnameEmployee, e.nameEmployee, e.patronymicEmployee, " +
                "d.idDivision, d.nameDivision, d.filial_id, d.chif, d.mediator " +
                "FROM employees e " +
                "LEFT OUTER JOIN division d ON e.division_id = d.idDivision " +
                "ORDER BY e.surnameEmployee ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();
        List<Worker> list = new ArrayList<>();

        while (rs.next()) {
            Division division = new Division();
            int idDivision = rs.getInt("idDivision");
            String nameDivision = rs.getString("nameDivision");
            int idFilial = rs.getInt("filial_id");
            int chif = rs.getInt("chif");
            int mediator = rs.getInt("mediator");
            division.setId(idDivision);
            division.setName(nameDivision);
            division.setFilial_id(idFilial);
            division.setChif(chif);
            division.setMediator(mediator);

            Worker worker = new Worker();
            int idWorker = rs.getInt("idEmployees");
            String surnameWorker = rs.getString("surnameEmployee");
            String nameWorker = rs.getString("nameEmployee");
            String patronymicWorker = rs.getString("patronymicEmployee");

            worker.setId(idWorker);
            worker.setSurname(surnameWorker);
            worker.setName(nameWorker);
            worker.setPatronymic(patronymicWorker);
            worker.setDivision(division);
            //worker.setUser_id(user_id);
            list.add(worker);
        }
        return list;
    }

    public static List<Worker> queryWorkerByFilial(Connection conn, int filial) throws SQLException {
        String sql = "SELECT e.idEmployees, e.surnameEmployee, e.nameEmployee, e.patronymicEmployee, " +
                "d.idDivision, d.nameDivision, d.filial_id, d.chif, d.mediator " +
                "FROM employees e " +
                "LEFT OUTER JOIN division d ON e.division_id = d.idDivision " +
                "WHERE d.filial_id=? " +
                "ORDER BY e.surnameEmployee ";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, filial);
        ResultSet rs = pstm.executeQuery();
        List<Worker> list = new ArrayList<>();

        while (rs.next()) {
            Division division = new Division();
            int idDivision = rs.getInt("idDivision");
            String nameDivision = rs.getString("nameDivision");
            int idFilial = rs.getInt("filial_id");
            int chif = rs.getInt("chif");
            int mediator = rs.getInt("mediator");

            division.setId(idDivision);
            division.setName(nameDivision);
            division.setFilial_id(idFilial);
            division.setChif(chif);
            division.setMediator(mediator);

            Worker worker = new Worker();
            int idWorker = rs.getInt("idEmployees");
            String surnameWorker = rs.getString("surnameEmployee");
            String nameWorker = rs.getString("nameEmployee");
            String patronymicWorker = rs.getString("patronymicEmployee");

            worker.setId(idWorker);
            worker.setSurname(surnameWorker);
            worker.setName(nameWorker);
            worker.setPatronymic(patronymicWorker);
            worker.setDivision(division);
            //worker.setUser_id(user_id);
            list.add(worker);
        }
        return list;
    }

    public static List<Worker> queryWorkerByDivision(Connection conn, int division) throws SQLException {
        return null;
    }

    /*~~~~~ FILIAL database tools ~~~~*/
    public static List<Filial> queryFilial(Connection conn) throws SQLException {
        String sql = "SELECT f.idFilial, f.nameFilial, f.cutnameFilial FROM filials f ORDER BY f.idFilial ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();
        List<Filial> list = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("idFilial");
            String name = rs.getString("nameFilial");
            String sh_name = rs.getString("cutnameFilial");
            Filial filial = new Filial();
            filial.setId(id);
            filial.setName(name);
            filial.setSh_name(sh_name);
            list.add(filial);
        }
        return list;
    }

    public static List<Filial> queryAvailableFilials(Connection conn, int worker) throws SQLException {
        String sql = "SELECT f.idFilial, f.nameFilial, f.cutnameFilial " +
                "FROM filials f " +
                "LEFT OUTER JOIN division d ON f.idFilial = d.filial_id " +
                "LEFT OUTER JOIN employees e ON d.mediator = e.idEmployees " +
                "WHERE e.idEmployees=?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, Integer.toString(worker));
        ResultSet rs = pstm.executeQuery();

        List<Filial> list = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("idFilial");
            String nameF = rs.getString("nameFilial");
            String cutname = rs.getString("cutnameFilial");
            Filial fil = new Filial();
            fil.setId(id);
            fil.setName(nameF);
            fil.setSh_name(cutname);
            list.add(fil);
        }
        return list;
    }

    public static Filial findFilial(Connection conn, int id) throws SQLException {
        String sql = "SELECT a.idFilial, a.nameFilial, a.cutnameFilial FROM filials a WHERE a.idFilial=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);

        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            String name = rs.getString("nameFilial");
            String sh_name = rs.getString("cutnameFilial");
            return new Filial(id, name, sh_name);
        }
        return null;
    }

    public static void updateFilial(Connection conn, Filial filial) throws SQLException {
        String sql = "UPDATE filials SET nameFilial=?, cutnameFilial=? WHERE idFilial=? ";
        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, filial.getName());
        pstm.setString(2, filial.getSh_name());
        pstm.setInt(3, filial.getId());
        pstm.executeUpdate();
    }

    public static void insertFilial(Connection conn, Filial filial) throws SQLException {
        String sql = "INSERT INTO filials(idFilial,nameFilial,cutnameFilial) VALUES (?,?,?)";
        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setInt(1, filial.getId());
        pstm.setString(2, filial.getName());
        pstm.setString(3, filial.getSh_name());

        pstm.executeUpdate();
    }

    public static void deleteFilial(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM filials WHERE idFilial=? ";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);

        pstm.executeUpdate();
    }

    /*~~~~~ DIVISION database tools ~~~~*/
    public static List<Division> queryDivisions(Connection conn, int filial) throws SQLException {
        String sql = "SELECT d.idDivision, d.nameDivision, d.chif, d.mediator FROM division d " +
                "WHERE d.filial_id=? ";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, filial);

        ResultSet rs = pstm.executeQuery();
        List<Division> list = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("idDivision");
            String name = rs.getString("nameDivision");
            int chif = rs.getInt("chif");
            int mediator = rs.getInt("mediator");
            Division division = new Division();
            division.setId(id);
            division.setName(name);
            division.setFilial_id(filial);
            division.setChif(chif);
            division.setMediator(mediator);
            list.add(division);
        }
        return list;
    }

    public static List<Division> queryDivisions(Connection conn, int filial, int mediator) throws SQLException {
        String sql = "SELECT d.idDivision, d.nameDivision, d.chif FROM division d " +
                "WHERE d.filial_id=? AND d.mediator=?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, filial);
        pstm.setInt(2, mediator);

        ResultSet rs = pstm.executeQuery();
        List<Division> list = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("idDivision");
            String name = rs.getString("nameDivision");
            int chif = rs.getInt("chif");
            Division division = new Division();
            division.setId(id);
            division.setName(name);
            division.setFilial_id(filial);
            division.setChif(chif);
            division.setMediator(mediator);
            list.add(division);
        }
        return list;
    }

    public static Division getDivision(Connection conn, int id) throws SQLException {
        String sql = "SELECT d.nameDivision, d.filial_id, d.chif, d.mediator " +
                "FROM division d " +
                "WHERE d.idDivision=?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            Division division = new Division();
            int idDivision = id;
            String nameDivision = rs.getString("nameDivision");
            int filial_id = rs.getInt("filial_id");
            int chif = rs.getInt("chif");
            int mediator = rs.getInt("mediator");
            division.setId(idDivision);
            division.setName(nameDivision);
            division.setFilial_id(filial_id);
            if (rs.getObject("chif") != null) {
                division.setChif(rs.getInt("chif"));
            }
            if (rs.getObject("mediator") != null) {
                division.setMediator(rs.getInt("mediator"));
            }
            return division;
        }
        return null;
    }

    public static void insertDivision(Connection conn, Division division) throws SQLException {
        String sql = "INSERT INTO division(nameDivision, filial_id) VALUES (?,?)";
        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, division.getName());
        pstm.setInt(2, division.getFilial_id());

        pstm.executeUpdate();
    }

    public static void deleteDivision(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM division WHERE idDivision=? ";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);

        pstm.executeUpdate();
    }

    /*~~~~~ DIVISION database tools ~~~~*/
/*
    public static boolean hasRolesByJsp(Connection conn, String jsp, int role) throws SQLException {
        String sql = "SELECT a.name, a.access_role FROM access a " +
                "WHERE a.name=? AND a.access_role=? ";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, jsp);

        ResultSet rs = pstm.executeQuery();

        if (rs.next())
            return true;
        else
            return false;
    }
*/

    public static List<Access> queryAccess(Connection conn) throws SQLException {
        String sql = "SELECT name, access_mask, editable FROM access ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();
        List<Access> list = new ArrayList<>();

        while (rs.next()) {
            int role = rs.getInt("access_mask");
            String name = rs.getString("name");
            int editable = rs.getInt("editable");
            Access acs = new Access();
            acs.setUrl(name);
            acs.setRole(role);
            acs.setEditable(editable);
            list.add(acs);
        }
        return list;
    }

    public static void insertAccess(Connection conn, String url, int level) throws SQLException {
        String sql = "INSERT INTO access(name, access_mask, editable) VALUES (?,?,?)";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, url);
        pstm.setInt(2, level);
        pstm.setInt(3, 1);

        pstm.executeUpdate();
    }
}