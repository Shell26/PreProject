package servlet;

import dao.UserDAO;
import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin/main")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession(false);
        final String login = (String) session.getAttribute("login");
        final String password = (String) session.getAttribute("password");

        if (UserService.getInstance().isAdmin(login, password)) {

            UserService.getInstance().createTable();

            req.setAttribute("users", UserService.getInstance().getAllUsers());
            req.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(req, resp);
        }else{
            req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF8");

        String name = req.getParameter("name");
        String password = req.getParameter("password");
        Long age = Long.parseLong(req.getParameter("age"));

        User user = new User(age, name, password, "user");

        UserService.getInstance().addUser(user);

        doGet(req, resp);
    }
}
