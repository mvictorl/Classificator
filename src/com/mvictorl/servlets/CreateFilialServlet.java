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

@WebServlet(urlPatterns = {"/createFilial"})
public class CreateFilialServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateFilialServlet() {
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
                    RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/createFilialView.jsp");
                    dispatcher.forward(request, response);
                    return;
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