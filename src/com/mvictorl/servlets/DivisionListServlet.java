package com.mvictorl.servlets;

import com.mvictorl.beans.Division;
import com.mvictorl.beans.Filial;
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

@WebServlet(urlPatterns = {"/divisionList"})
public class DivisionListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DivisionListServlet() {
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
        // Have not required roleID (not user = 9)
        if (loginedUser.getRole().getId() > 8) {
            // Redirect to denied-error page
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/Denied.jsp");
            dispatcher.forward(request, response);
        }
        else {
            String errorString = null;
            Connection conn = MyUtils.getStoredConnection(request);
            List<Filial> fls = new ArrayList<>();

            if (loginedUser.getRole().getId() < 3) {
                try {
                    fls = DBUtils.queryFilial(conn);
                } catch (SQLException e) {
                    e.printStackTrace();
                    errorString = e.getMessage();
                }
            }
            else {
                Worker active = MyUtils.getActiveWorker(session);
                if (active != null) {
                    try {
                        fls = DBUtils.queryAvailableFilials(conn, active.getId());
                    } catch (SQLException e) {
                        e.printStackTrace();
                        errorString = e.getMessage();
                    }
                }
                else {
                    RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/Denied.jsp");
                    dispatcher.forward(request, response);
                }
            }

            if (fls != null) {
                request.setAttribute("filials", fls);
            }

            if (request.getParameterMap().containsKey("active_filial")) {
                int active_filial = Integer.parseInt(request.getParameter("active_filial").toString());
                List<Division> dvs = new ArrayList<>();
                try {
                    dvs = DBUtils.queryDivisions(conn, active_filial);
                } catch (SQLException e) {
                    e.printStackTrace();
                    errorString = e.getMessage();
                }
                request.setAttribute("active_filial", active_filial);
                request.setAttribute("divisions", dvs);
            }
            request.setAttribute("errorString", errorString);
            // Forward to /WEB-INF/views/productListView.jsp
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/divisionListView.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}