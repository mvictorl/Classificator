package com.mvictorl.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/doEditUser"})
public class DoEditUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DoEditUserServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*Connection conn = MyUtils.getStoredConnection(request);

        int user_id = Integer.parseInt(request.getParameter("id"));
        String user_name = (String) request.getParameter("name");
        String pass_1 = (String) request.getParameter("pass_1");
        String pass_2 = (String) request.getParameter("pass_2");
        int role_id = Integer.parseInt(request.getParameter("role"));
        int worker_id = Integer.parseInt(request.getParameter("worker"));

        Filial filial = new Filial(id, name, sh_name);
        Filial tmp = null;
        String errorString = null;

        try {
            DBUtils.updateFilial(conn, filial);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("filial", filial);

        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/editFilialView.jsp");
            dispatcher.forward(request, response);
        }

        // If everything nice.
        // Redirect to the product listing page.
        else {
            response.sendRedirect(request.getContextPath() + "/filialList");
        }*/
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}