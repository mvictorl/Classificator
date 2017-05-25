package com.mvictorl.utils;

import com.mvictorl.beans.Access;
import com.mvictorl.beans.User;
import com.mvictorl.beans.Worker;
import com.mvictorl.connections.MySQLConnectionUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyUtils {
    public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";
    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";

    // Store Connection in request attribute.
    // (Information stored only exist during requests)
    public static void storeConnection(ServletRequest request, Connection conn) {
        request.setAttribute(ATT_NAME_CONNECTION, conn);
    }

    // Get the Connection object has been stored in one attribute of the request.
    public static Connection getStoredConnection(ServletRequest request) {
        Connection conn = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
        return conn;
    }

    // Store user info in Session.
    public static void storeLoginedUser(HttpSession session, User loginedUser) {
        // On the JSP can access ${loginedUser}
        session.setAttribute("loginedUser", loginedUser);
    }

    // Get the user information stored in the session.
    public static User getLoginedUser(HttpSession session) {
        User loginedUser = (User) session.getAttribute("loginedUser");
        return loginedUser;
    }

    // Store Active Worker in Session.
    public static void storeActiveWorker(HttpSession session, Worker activeWorker) {
        // On the JSP can access ${activeWorker}
        session.setAttribute("activeWorker", activeWorker);
    }

    // Get the Active Worker information stored in the session.
    public static Worker getActiveWorker(HttpSession session) {
        Worker activeWorker = (Worker) session.getAttribute("activeWorker");
        return activeWorker;
    }

    // Get the Active Worker information stored in the session.
    public static void removeActiveWorker(HttpSession session) {
        if (!session.getAttribute("activeWorker").equals(null)) {
            session.removeAttribute("activeWorker");
        }
    }

    // Store info in Cookie
    public static void storeUserCookie(HttpServletResponse response, User user) {
        System.out.println("Store user cookie");
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getName());

        // 1 day (Convert to seconds)
        cookieUserName.setMaxAge(24 * 60 * 60);
        response.addCookie(cookieUserName);
    }

    public static String getUserNameInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_NAME_USER_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    // Delete cookie.
    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);

        // 0 seconds (Expires immediately)
        cookieUserName.setMaxAge(0);
        response.addCookie(cookieUserName);
    }

    public static String addAccessParameter(ServletContext servletContext) {
        List<Access> acs = new ArrayList<>();
        Connection conn = null;
        String errorString = null;

        try {
            conn = MySQLConnectionUtils.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        } catch (SQLException e) {
            e.printStackTrace();
            errorString += e.getMessage();
        }
        try {
            acs = DBUtils.queryAccess(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString += e.getMessage();
        }
        //MySQLConnectionUtils.closeQuietly(conn);
        servletContext.setAttribute("access", acs);

        return errorString;
    }
}
