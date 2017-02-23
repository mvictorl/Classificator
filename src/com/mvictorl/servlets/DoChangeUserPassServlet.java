package com.mvictorl.servlets;

import com.mvictorl.beans.User;
import com.mvictorl.utils.DBUtils;
import com.mvictorl.utils.MyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "DoChangeUserPassServlet", urlPatterns = {"/doChangeUserPass"})
public class DoChangeUserPassServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);

        String user_name = (String) request.getParameter("user_name");
        String pass = (String) request.getParameter("pass");
        String new_pass_1 = (String) request.getParameter("new_pass_1");
        String new_pass_2 = (String) request.getParameter("new_pass_2");

        User user = null;
        String errorString = null;

        try {
            user = DBUtils.findUser(conn, user_name);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        if(!user.getPassword().equals(pass)) {
            errorString = "Неверный текущий пароль!";
        }
        else {
            if (!new_pass_1.equals(new_pass_2)) {
                errorString += "Не совпадают введенные значения нового пароля!";
            }
        }

        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);

        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/changeUserPass");
            dispatcher.forward(request, response);
        }

        // If everything nice.
        // Redirect to the product listing page.
        else {
            try {
                DBUtils.setUserPassword(conn, user.getId(), new_pass_1);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
            if (errorString != null) {
                RequestDispatcher dispatcher = request.getServletContext()
                        .getRequestDispatcher("/changeUserPass");
                dispatcher.forward(request, response);
            }
            else {
                response.sendRedirect(request.getContextPath() + "/userInfo");
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}