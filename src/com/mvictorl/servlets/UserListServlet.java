package com.mvictorl.servlets;

import com.mvictorl.beans.Filial;
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
import java.util.List;

@WebServlet(urlPatterns = { "/filialList" })
public class UserListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserListServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);

        String errorString = null;
        List<Filial> list = null;
        try {
            list = DBUtils.queryFilial(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        // Store info in request attribute, before forward to views
        request.setAttribute("errorString", errorString);
        request.setAttribute("filialList", list);

        // Forward to /WEB-INF/views/productListView.jsp
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/filialListView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}