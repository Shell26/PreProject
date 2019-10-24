package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin/update")
public class UpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession(false);
        final String login = (String) session.getAttribute("login");
        final String password = (String) session.getAttribute("password");

        if (UserService.getInstance().isAdmin(login, password)) {
            Long id = Long.parseLong(req.getParameter("id"));
            User user = UserService.getInstance().getUserById(id);
            req.setAttribute("user", user);
            req.getRequestDispatcher("/WEB-INF/view/update.jsp").forward(req, resp);
        }else{
            req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String newName = req.getParameter("name2");
        String newpassword = req.getParameter("password2");
        Long newAge = Long.parseLong(req.getParameter("age2"));

        UserService.getInstance().updateUser(id, newAge, newName, newpassword);

        resp.sendRedirect(req.getContextPath() + "/admin/main");
    }
}
