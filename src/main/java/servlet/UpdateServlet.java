package servlet;

import model.User;
import service.Service;

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
        String name = req.getParameter("name");
        String secondName = req.getParameter("second");
        Long age = Long.parseLong(req.getParameter("age"));

        User user = new User(name, secondName, age);

        req.setAttribute("user", user);

        req.getRequestDispatcher("update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String secondName = req.getParameter("second");
        Long age = Long.parseLong(req.getParameter("age"));
        String name2 = req.getParameter("name2");
        String secondName2 = req.getParameter("second2");
        Long age2 = Long.parseLong(req.getParameter("age2"));

        User user = new User(name, secondName, age);

        new Service().updateUser(user, name2, secondName2, age2);

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
