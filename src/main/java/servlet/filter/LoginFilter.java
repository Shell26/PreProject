package servlet.filter;

import model.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse resp = (HttpServletResponse) response;
        final HttpSession session = req.getSession(false);

        if(session != null){
            //читаю из формы
            final String login = req.getParameter("authLogin");
            final String password = req.getParameter("authPass");
            if (session.getAttribute("login") == null || session.getAttribute("password") ==null){
                //записываю в сессию
                session.setAttribute("password", password);
                session.setAttribute("login", login);
            }
        }

        if(session == null || session.getAttribute("login") == null || session.getAttribute("password") ==null) {
            request.getServletContext().getRequestDispatcher("/").forward(request, response);
            //кидает опять на этот же фильтр
//            resp.sendRedirect(req.getContextPath() + "/");
        }else{
            filterChain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}
