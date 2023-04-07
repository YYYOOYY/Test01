package controller.action;

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

@WebServlet("/action/modify-task")
public class ModifyTaskBoardController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		SqlSessionFactory factory= 
				(SqlSessionFactory)req.getServletContext().getAttribute("sqlSessionFactory");
		SqlSession sqlSession = factory.openSession(true);
		
		String code = req.getParameter("code");
		String body = req.getParameter("body");
		
		req.setAttribute("code", code);
		if(body.equals("")) {
			body = " ";
		}
		Map<String, Object> params = new HashMap<>();
		params.put("body", body);
		params.put("code", code);
		
		int r = sqlSession.update("boards.updateByBoardBody", params);
		sqlSession.close();
		if(r == 1) {
			resp.sendRedirect("/board/detail?code="+code);
		}else {
			resp.sendRedirect("/board/modify?code="+code+"&error=r");
		}
		
	}
}
