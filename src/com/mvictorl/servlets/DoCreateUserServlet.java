package com.mvictorl.servlets;

import com.mvictorl.beans.Filial;
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

@WebServlet(urlPatterns = { "/doCreateUser" })
public class DoCreateUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DoCreateUserServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String errorString = null;

        User user = (User) request.getSession().getAttribute("loginedUser");
        if (user.getRole().getId() > 0) {
            request.setAttribute("errorString", "Не достаточно прав для доступа!");
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/home");
            dispatcher.forward(request, response);
        }
        else {
            Connection conn = MyUtils.getStoredConnection(request);

            int id = Integer.parseInt(request.getParameter("id"));
            String sh_name = (String) request.getParameter("sh_name");
            String name = (String) request.getParameter("name");

            Filial filial = new Filial(id, name, sh_name);

            // Check new filial correction record
            Filial tmp = null;
            try {
                tmp = DBUtils.findFilial(conn, id);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
            if (tmp == null) {
                if (errorString == null) {
                    try {
                        DBUtils.insertFilial(conn, filial);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        errorString = e.getMessage();
                    }
                }
            }
            else {
                errorString = "Недопустимые параметры филиала!";
            }

            // Store infomation to request attribute, before forward to views.
            request.setAttribute("errorString", errorString);
            request.setAttribute("filial", filial);

            // If error, forward to Edit page.
            if (errorString != null) {
                RequestDispatcher dispatcher = request.getServletContext()
                        .getRequestDispatcher("/WEB-INF/views/createFilialView.jsp");
                dispatcher.forward(request, response);
            }

            // If everything nice.
            // Redirect to the product listing page.
            else {
                response.sendRedirect(request.getContextPath() + "/filialList");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}