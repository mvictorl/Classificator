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

@WebServlet(urlPatterns = {"/systemList"})
public class SystemListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SystemListServlet() {
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

        String cntx = request.getServletPath();
        List<Access> acs = (ArrayList<Access>) request.getServletContext().getAttribute("access");
        byte role = (byte) loginedUser.getRole().getId();
        for (Access obj : acs) {
            if (obj.getUrl().equals(cntx)) {
                byte b = (byte) obj.getRole();
                if ((b & role) == role) {
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
                    return;
                }
            }
        }
        // Redirect to denied-error page
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/Denied.jsp");
        dispatcher.forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}