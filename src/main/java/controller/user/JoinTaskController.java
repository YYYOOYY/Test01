package controller.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/user/join-task")
public class JoinTaskController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");
		String nick = req.getParameter("nick");

		SqlSessionFactory factory= 
				(SqlSessionFactory)req.getServletContext().getAttribute("sqlSessionFactory");
		SqlSession sqlSession = factory.openSession(true);
		
		String bcPass = BCrypt.hashpw(pass, BCrypt.gensalt());
		
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		params.put("pass", bcPass);
		params.put("nick", nick);
		

		int r = sqlSession.insert("users.create",params);
		
		if(r == 1) {
			resp.sendRedirect("/user/login");
		}else {
			req.getRequestDispatcher("/WEB-INF/views/user/join.jsp?error=r").forward(req, resp);
		}
	}
}
