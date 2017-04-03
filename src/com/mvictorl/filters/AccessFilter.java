package com.mvictorl.filters;

import com.mvictorl.beans.Access;
import com.mvictorl.beans.User;
import com.mvictorl.utils.MyUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "AccessFilter")
public class AccessFilter implements Filter {
    //List<Access> acs = new ArrayList<>();

    public void init(FilterConfig config) throws ServletException {
        /*Connection conn = null;
        String errorString = null;

        try {
            conn = MySQLConnectionUtils.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        } catch (SQLException e) {
            e.printStackTrace();
            errorString += e.getMessage();
        }
        try {
            acs = DBUtils.queryAccess(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString += e.getMessage();
        }
        MySQLConnectionUtils.closeQuietly(conn);
        if (errorString != null) {
            destroy();
        }
        config.getServletContext().setAttribute("access", acs);*/
    }

    public void destroy() { }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        User user = MyUtils.getLoginedUser(session);
        byte role = 0;

        if (user != null) {
            role = (byte) user.getRole().getId();
        }

        List<Access> acs = (ArrayList<Access>) request.getServletContext().getAttribute("access");
        String cntx = request.getServletPath();
        Connection conn = MyUtils.getStoredConnection(request);

        for (Access obj : acs) {
            if (obj.getUrl().equals(cntx)) {
                byte b = (byte) obj.getRole();
                if ((b & role) == role) {
                    chain.doFilter(req, resp);
                    return;
                }
            }
        }
        System.out.println("Out of rights for: " + user.getRole().getName() + " (" + cntx + ")");
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/home");
        dispatcher.forward(request, response);
        return;
    }
}