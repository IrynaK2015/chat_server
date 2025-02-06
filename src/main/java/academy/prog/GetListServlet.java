package academy.prog;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import jakarta.servlet.http.*;

/*

	0 - m /get?from=0
	1 - m
	2 - m
	....
	100 - m / from=101
	....


 */

public class GetListServlet extends HttpServlet {
	
	private final MessageList msgList = MessageList.getInstance();

    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String fromStr = req.getParameter("from");
		int from = 0;
		String user = req.getParameter("login");
		try {
			from = Integer.parseInt(fromStr);
			if (from < 0) from = 0;

			if (user == null || user.isEmpty()) {
				throw new Exception("Anonymous access is forbidden");
			}
			System.out.println(user);
		} catch (Exception ex) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
		}

		resp.setContentType("application/json");

		String json = msgList.toJSON(user, from);
		if (json != null) {
			OutputStream os = resp.getOutputStream();
            byte[] buf = json.getBytes(StandardCharsets.UTF_8);
			os.write(buf);

			//PrintWriter pw = resp.getWriter();
			//pw.print(json);
		}
	}
}
