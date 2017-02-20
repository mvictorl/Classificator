package com.mvictorl.servlets;

import com.mvictorl.beans.User;
import com.mvictorl.beans.Woker;
import com.mvictorl.utils.DBUtils;
import com.mvictorl.utils.MyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "DoLoginServlet", urlPatterns = {"/doLogin"})
public class DoLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public DoLoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String userPassword = request.getParameter("userPassword");
        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMeStr);

        User user = null;
        Woker woker = null;
        boolean hasError = false;
        String errorString = null;

        if (userName == null || userPassword == null
                || userName.length() == 0 || userPassword.length() == 0) {
            hasError = true;
            errorString = "Введите имя пользователя и пароль!";
        } else {
            Connection conn = MyUtils.getStoredConnection(request);
            try {
                user = DBUtils.findUser(conn, userName, userPassword);
                if (user == null) {
                    hasError = true;
                    errorString = "Не верные имя пользователя и/или пароль!";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }
        // If error, forward to /WEB-INF/views/login.jsp
        if (hasError) {
            user = new User();
            user.setName(userName);
            user.setPassword(userPassword);

            // Store information in request attribute, before forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);

            // Forward to /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");

            dispatcher.forward(request, response);
        }
        // If no error
        // Store user information in Session
        // And redirect to userInfo page.
        else {
            if (user.getWoker() > 0) {
                Connection conn = MyUtils.getStoredConnection(request);
                try {
                    woker = DBUtils.findWoker(conn, user.getWoker());

                    if (woker == null) {
                        hasError = true;
                        errorString = "Не верные данные сотрудника!";
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    hasError = true;
                    errorString += "\n" + e.getMessage();
                }
                request.setAttribute("active_woker", woker);
            }

            HttpSession session = request.getSession();
            MyUtils.storeLoginedUser(session, user);
            MyUtils.storeActiveWoker(session, woker);

            // If user checked "Remember me".
            if (remember) {
                MyUtils.storeUserCookie(response, user);
            }
            // Else delete cookie.
            else {
                MyUtils.deleteUserCookie(response);
            }
            // Redirect to userInfo page.
            response.sendRedirect(request.getContextPath() + "/userInfo");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}