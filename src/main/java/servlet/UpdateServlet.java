package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        User user = UserService.getInstance().getUserById(id);
        req.setAttribute("user", user);

        req.getRequestDispatcher("update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String newName = req.getParameter("name2");
        String newSecondName = req.getParameter("second2");
        Long newAge = Long.parseLong(req.getParameter("age2"));

        UserService.getInstance().updateUser(id, newName, newSecondName, newAge);

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
