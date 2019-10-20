package servlet;

import dao.UserDAO;
import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserService.getInstance().createTable();

        req.setAttribute("users", UserService.getInstance().getAllUsers());
        req.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF8");

        String name = req.getParameter("name");
        String secondName = req.getParameter("second");
        Long age = Long.parseLong(req.getParameter("age"));

        User user = new User(age, name, "user", secondName);

        UserService.getInstance().addUser(user);

//        System.out.println(UserService.getInstance().isAdmin(name, secondName));

        doGet(req, resp);
    }
}
