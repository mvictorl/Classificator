package com.mvictorl.servlets;

import com.mvictorl.beans.Access;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/editFilial"})
public class EditFilialServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditFilialServlet() {
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
            return;
        }

        String cntx = request.getServletPath();
        List<Access> acs = (ArrayList<Access>) request.getServletContext().getAttribute("access");
        byte role = (byte) loginedUser.getRole().getId();
        for (Access obj : acs) {
            if (obj.getUrl().equals(cntx)) {
                byte b = (byte) obj.getRole();
                if ((b & role) == role) {
                    Connection conn = MyUtils.getStoredConnection(request);

                    int id = Integer.parseInt(request.getParameter("id"));

                    Filial filial = null;
                    String errorString = null;

                    try {
                        filial = DBUtils.findFilial(conn, id);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        errorString = e.getMessage();
                    }

                    // If no error.
                    // The filial does not exist to edit.
                    // Redirect to productList page.
                    if (errorString != null && filial == null) {
                        response.sendRedirect(request.getServletPath() + "/filialList");
                        return;
                    }

                    // Store errorString in request attribute, before forward to views.
                    request.setAttribute("errorString", errorString);
                    request.setAttribute("filial", filial);

                    RequestDispatcher dispatcher = request.getServletContext()
                            .getRequestDispatcher("/WEB-INF/views/editFilialView.jsp");
                    dispatcher.forward(request, response);
                    return;
                }
            }
        }
        // Redirect to denied-error page
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/Denied.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}