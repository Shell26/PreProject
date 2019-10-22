package servlet.filter;

import model.User;
import service.UserService;
import servlet.IndexServlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

//@WebFilter("/")
@WebFilter(servletNames = "IndexServlet")
public class IndexFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        System.out.printf("Filter working");

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        final HttpSession session = req.getSession();

        if (login == null || password == null) {          //первое посещение
            filterChain.doFilter(request, response);
        } else if (UserService.getInstance().userIsExist(login, password)) {     // не заходил, существует
            req.getSession().setAttribute("password", password);
            req.getSession().setAttribute("login", login);

            if (UserService.getInstance().isAdmin(login, password)) {
                res.sendRedirect("main");
            } else {
                res.sendRedirect("user");
            }
        } else if (nonNull(session.getAttribute("login")) &&          // уже заходил
                nonNull(session.getAttribute("password"))) {
            if (UserService.getInstance().isAdmin(login, password)) {
                res.sendRedirect("main");
            } else {
                res.sendRedirect("user");
            }
        }
    }

    @Override
    public void destroy() {
    }
}
