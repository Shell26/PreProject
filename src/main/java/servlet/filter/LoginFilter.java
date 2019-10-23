package servlet.filter;

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
        final HttpSession session = req.getSession();

        final String login1 = (String) session.getAttribute("login");
        final String password1 = (String) session.getAttribute("password");

        if(login1 == null && password1 == null){
            final String login = req.getParameter("authLogin");
            final String password = req.getParameter("authPass");
            req.getSession().setAttribute("password", password);
            req.getSession().setAttribute("login", login);
        }else if(!UserService.getInstance().userIsExist(login1, password1)){
            final String login = req.getParameter("authLogin");
            final String password = req.getParameter("authPass");
            req.getSession().setAttribute("password", password);
            req.getSession().setAttribute("login", login);
        }

        final String login2 = (String) session.getAttribute("login");
        final String password2 = (String) session.getAttribute("password");

        if (UserService.getInstance().userIsExist(login2, password2)){
            filterChain.doFilter(request, response);
        }else{
            req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
