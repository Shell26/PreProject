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

@WebServlet("/admin/delete")
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession(false);
        final String login = (String) session.getAttribute("login");
        final String password = (String) session.getAttribute("password");

        if (UserService.getInstance().isAdmin(login, password)) {
            Long id = Long.parseLong(req.getParameter("id"));
            UserService.getInstance().deleteUser(id);
            resp.sendRedirect(req.getContextPath() + "/admin/main");
        }else{
//            req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }
}
