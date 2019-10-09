package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String name = req.getParameter("name");
//        String secondName = req.getParameter("second");
//        Long age = Long.parseLong(req.getParameter("age"));
        Long id = Long.parseLong(req.getParameter("id"));
        Long id2 = id;
//        User user = new User(name, secondName, age);

        new UserService().deleteUser(id);

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
