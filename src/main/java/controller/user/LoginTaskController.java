package controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import data.User;

@WebServlet("/user/login-task")
public class LoginTaskController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");
		
		SqlSessionFactory factory= 
				(SqlSessionFactory)req.getServletContext().getAttribute("sqlSessionFactory");
		SqlSession sqlSession = factory.openSession(true);
		
		User user = sqlSession.selectOne("users.findByUser", id);
		sqlSession.close();
		if(user != null) {
			
			if(id.equals(user.getId()) && pass.equals(user.getPass())) {
				req.getSession().setAttribute("logon", true);
				
				HttpSession session = req.getSession();
				session.setAttribute("logonUser", user);
				
				resp.sendRedirect("/");
			}else {
				resp.sendRedirect("/user/login?error=r");
//				req.getRequestDispatcher("/WEB-INF/views/user/login.jsp?error=r").forward(req, resp);
			}
			
		}else {
			resp.sendRedirect("/user/login?error=r");
		}
	
	}
}
