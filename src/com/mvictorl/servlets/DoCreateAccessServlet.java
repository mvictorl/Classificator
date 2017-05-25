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
import java.util.TreeSet;

@WebServlet(urlPatterns = {"/doCreateAccess"})
public class DoCreateAccessServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DoCreateAccessServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String errorString = null;
        TreeSet<Integer> lvl = new TreeSet();
        lvl.add(1);
        lvl.add(3);
        lvl.add(7);
        lvl.add(15);

        User user = (User) request.getSession().getAttribute("loginedUser");
        if (user.getRole().getId() > 1) {
            request.setAttribute("errorString", "Не достаточно прав для доступа!");
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/home");
            dispatcher.forward(request, response);
        } else {

            Connection conn = MyUtils.getStoredConnection(request);

            int level = Integer.parseInt(request.getParameter("level"));
            String url = (String) request.getParameter("url");

            if (url != null) {
                if (lvl.contains(level)) {
                    try {
                        DBUtils.insertAccess(conn, url, level);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        errorString = e.getMessage();
                    }
                } else {
                    errorString = "Недопустимые параметры!";
                }
            } else {
                errorString = "Необходимо ввести URL!";
            }

            // If error, forward to Edit page.
            if (errorString != null) {
                request.setAttribute("errorString", errorString);
                request.setAttribute("url", url);

                RequestDispatcher dispatcher = request.getServletContext()
                        .getRequestDispatcher("/WEB-INF/views/createAccessView.jsp");
                dispatcher.forward(request, response);
                return;
            }

            // If everything nice.
            // Redirect to the product listing page.
            else {
                errorString = MyUtils.addAccessParameter(request.getServletContext());
                if (errorString != null) {
                    request.setAttribute("errorString", errorString);
                    RequestDispatcher dispatcher = request.getServletContext()
                            .getRequestDispatcher("/accessList");
                    dispatcher.forward(request, response);
                    return;
                }
                else {
                    response.sendRedirect(request.getContextPath() + "/accessList");
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}