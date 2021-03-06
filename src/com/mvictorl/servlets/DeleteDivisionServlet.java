package com.mvictorl.servlets;

import com.mvictorl.beans.Division;
import com.mvictorl.beans.User;
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

@WebServlet(urlPatterns = { "/deleteDivision" })
public class DeleteDivisionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteDivisionServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Check User has logged on
        User loginedUser = MyUtils.getLoginedUser(session);

        // Not logged in
        if (loginedUser == null) {
            // Redirect to login page.
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/login");
            dispatcher.forward(request, response);
        }

        // Have not required roleID (root = 0)
        if (loginedUser.getRole().getId() != 0) {
            // Redirect to denied-error page
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/Denied.jsp");
            dispatcher.forward(request, response);
        }

        Connection conn = MyUtils.getStoredConnection(request);

        int id = Integer.parseInt(request.getParameter("id"));

        String errorString = null;
        Division tmp = new Division();

        try {
            tmp = DBUtils.getDivision(conn, id);
            DBUtils.deleteDivision(conn, id);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        // If an error redirected to an error page.
        if (errorString != null) {

            // Store the information in the request attribute, before forward to views.
            request.setAttribute("errorString", errorString);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("divisionList");
            dispatcher.forward(request, response);
        }

        // If everything nice.
        // Redirect to the product listing page.
        else {
            response.sendRedirect(request.getContextPath() + "/divisionList?active_filial=" + tmp.getFilial_id());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}