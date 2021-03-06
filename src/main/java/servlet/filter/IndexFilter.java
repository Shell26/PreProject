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

@WebFilter("/admin")
//@WebFilter(servletNames = "IndexServlet")
public class IndexFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        final HttpSession session = req.getSession(false);

        if(session.getAttribute("login") == null ||
                session.getAttribute("password") == null){
            final String login = req.getParameter("authLogin");       //читаю из формы
            final String password = req.getParameter("authPass");
            session.setAttribute("password", password);
            session.setAttribute("login", login);
        }

        final String login = (String) session.getAttribute("login");
        final String password = (String) session.getAttribute("password");

        if (UserService.getInstance().userIsExist(login, password)){
            if (UserService.getInstance().isAdmin(login, password)) {
                res.sendRedirect(req.getContextPath() + "/admin/main");
            }else{
                res.sendRedirect(req.getContextPath() + "/user");
            }
        }else{
            res.sendRedirect(req.getContextPath() + "/");
        }


//        if (login == null || password == null) {          //первое посещение
//            filterChain.doFilter(request, response);
//        } else if (UserService.getInstance().userIsExist(login, password)) {     // не заходил, существует
//            req.getSession().setAttribute("password", password);
//            req.getSession().setAttribute("login", login);
//            if (UserService.getInstance().isAdmin(login, password)) {
                // бегает по кругу фильтр-сервлет
//                res.sendRedirect(req.getContextPath() + "/admin/main");
//                request.getServletContext().getRequestDispatcher("/admin/main").forward(request, response);

//                request.getRequestDispatcher("/admin/main").forward(request, response);
//            } else {
//                res.sendRedirect(req.getContextPath() + "/user");
//                request.getServletContext().getRequestDispatcher("/user").forward(request, response);
//            }
//        }
    }


    @Override
    public void destroy() {
    }
}
