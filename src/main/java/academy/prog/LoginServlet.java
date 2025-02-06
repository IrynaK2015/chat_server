package academy.prog;

import java.io.IOException;
import jakarta.servlet.http.*;

public class LoginServlet extends HttpServlet {

    private final UserList userList = UserList.getInstance();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String user = req.getParameter("login");
        userList.addUser(user);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
