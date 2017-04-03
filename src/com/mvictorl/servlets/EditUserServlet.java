package com.mvictorl.servlets;

import com.mvictorl.beans.Access;
import com.mvictorl.beans.Role;
import com.mvictorl.beans.User;
import com.mvictorl.beans.Worker;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/editUser"})
public class EditUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditUserServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
/*
        Connection conn = MyUtils.getStoredConnection(request);
        HttpSession session = request.getSession();

        String userName = request.getParameter("userName");
        User current_user = (User) session.getAttribute("loginedUser");

        if (current_user == null) {
            response.sendRedirect(request.getServletPath() + "/login");
            return;
        }
*/
        HttpSession session = request.getSession();
        String userName = request.getParameter("userName");
        // Check User has logged on
        User current_user = MyUtils.getLoginedUser(session);

        // Not logged in
        if (current_user == null) {
            // Redirect to login page.
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/login");
            dispatcher.forward(request, response);
            return;
        }
        else {
            String cntx = request.getServletPath();
            List<Access> acs = (ArrayList<Access>) request.getServletContext().getAttribute("access");
            byte role = (byte) current_user.getRole().getId();
            for (Access obj : acs) {
                if (obj.getUrl().equals(cntx)) {
                    byte b = (byte) obj.getRole();
                    if ((b & role) == role) {
                        Connection conn = MyUtils.getStoredConnection(request);
                        User user = null;
                        List<Role> roles = null;
                        List<Worker> workers = null;
                        String errorString = null;

                        try {
                            user = DBUtils.findUser(conn, userName);
                            roles = DBUtils.queryRoles(conn, current_user.getRole().getId());
                            workers = DBUtils.queryWorkers(conn);
                        } catch (SQLException e) {
                            e.printStackTrace();
                            errorString = e.getMessage();
                        }
                        // If no error.
                        // The user does not exist to edit.
                        // Redirect to productList page.
                        if (errorString != null && user == null) {
                            response.sendRedirect(request.getServletPath() + "/userList");
                            return;
                        }
                        // Store errorString in request attribute, before forward to views.
                        request.setAttribute("errorString", errorString);
                        request.setAttribute("user", user);
                        request.setAttribute("roles", roles);
                        request.setAttribute("workers", workers);

                        RequestDispatcher dispatcher = request.getServletContext()
                                .getRequestDispatcher("/WEB-INF/views/editUserView.jsp");
                        dispatcher.forward(request, response);
                        return;
                    }
                }
            }
        }
        // Redirect to denied-error page
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/Denied.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}