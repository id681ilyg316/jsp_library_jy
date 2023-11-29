package moweifeng.servlet;

import moweifeng.entities.BookAdmin;
import moweifeng.entities.Reader;
import moweifeng.service.AccountService;
import moweifeng.service.impl.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * 用户登录功能类 
 */
public class AccountServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getParameter("method");
		String type = req.getParameter("type");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String repassword = req.getParameter("repassword");
		String randImage1 = req.getParameter("randImage1");
		String randImage2 = req.getParameter("randImage2");
		if(randImage1 == null) {
			randImage1 = "";
		}
		if(randImage2 == null) {
			randImage2 = "";
		}
		String yzm = null;
		try {
			yzm = req.getSession().getAttribute("yzm").toString();
			if(yzm == null) {
				yzm = "";
			}
		} catch (Exception e) {
			 
		}
		AccountService accountService = new AccountServiceImpl();
		HttpSession session = req.getSession();
 			switch (method){
			case "login":
			 
				
			
				
 					switch (type){
					case "reader":
						Object object = accountService.login(username,password,type);
						if(randImage1.equals(yzm)) {
							Reader reader = (Reader) object;
							session.setAttribute("reader",reader);
							resp.sendRedirect(req.getContextPath()+"/reader/reader.jsp");
							break;
						}else {
							session.invalidate();
							req.setAttribute("msg", "<script>alert('验证码错误！')</script>");
 							req.getRequestDispatcher("login.jsp").forward(req, resp);
 							break;
						}

					case "bookadmin":
						object = accountService.login(username,password,type);
						if(randImage2.equals(yzm)) {
							BookAdmin bookAdmin = (BookAdmin) object;
							session.setAttribute("bookAdmin",bookAdmin);
							resp.sendRedirect(req.getContextPath()+"/admin.jsp");
							break;
						}else {
							try {
								session.invalidate();
							} catch (Exception e) {
								// TODO Auto-generated catch block
 							}
							req.setAttribute("msg", "<script>alert('验证码错误！')</script>");
							req.getRequestDispatcher("login.jsp").forward(req, resp);
						}

					}
			 
				break;

			case "register":
				if(!repassword.equals(password)) {
					req.setAttribute("msg", "<script>alert('2次密码不一样！')</script>");
					req.getRequestDispatcher("register.jsp").forward(req, resp);
					break;
				}
				int a = accountService.register(username,password);
				if (a == 1){
					req.setAttribute("msg", "<script>alert('注册成功！')</script>");
					req.getRequestDispatcher("login.jsp").forward(req, resp);
				} else {
					req.setAttribute("msg", "<script>alert('注册失败！')</script>");
					req.getRequestDispatcher("register.jsp").forward(req, resp);
				}
				break;
			case "logout":
				session.invalidate();
				resp.sendRedirect("login.jsp");
				break;

			}
	 
		
	}
}
