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
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/doCreateDivision"})
public class DoCreateDivisionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DoCreateDivisionServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String errorString = null;
        String name = (String) request.getParameter("name");
        int active_filial = Integer.parseInt(request.getParameter("active_filial").toString());

        User user = (User) request.getSession().getAttribute("loginedUser");
        if (user.getRole().getId() > 0) {
            request.setAttribute("errorString", "Не достаточно прав для доступа!");
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/home");
            dispatcher.forward(request, response);
        } else {
            Connection conn = MyUtils.getStoredConnection(request);

            Division division = new Division();
            division.setName(name);
            division.setFilial_id(active_filial);

            try {
                DBUtils.insertDivision(conn, division);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
        }
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/createFilialView.jsp");
            dispatcher.forward(request, response);
        }

        // If everything nice.
        // Redirect to the product listing page.
        else {
            response.sendRedirect(request.getContextPath() + "/divisionList?active_filial=" + active_filial);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}