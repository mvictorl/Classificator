package com.mvictorl.servlets;

import com.mvictorl.beans.Access;
import com.mvictorl.beans.User;
import com.mvictorl.utils.MyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/createDivision"})
public class CreateDivisionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateDivisionServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        int active_filial = Integer.parseInt(request.getParameter("active_filial").toString());

        // Check User has logged on
        User loginedUser = MyUtils.getLoginedUser(session);

        // Not logged in
        if (loginedUser == null) {
            // Redirect to login page.
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/login");
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
                    request.setAttribute("active_filial", active_filial);
                    RequestDispatcher dispatcher = request.getServletContext()
                            .getRequestDispatcher("/WEB-INF/views/createDivisionView.jsp");
                    dispatcher.forward(request, response);
                    return;
                }
            }
        }

        // Have not required roleID (root = 0)
        if (loginedUser.getRole().getId() != 0) {
            // Redirect to denied-error page
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/Denied.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}