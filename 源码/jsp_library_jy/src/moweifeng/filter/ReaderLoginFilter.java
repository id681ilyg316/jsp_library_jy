package moweifeng.filter;

import moweifeng.entities.BookAdmin;
import moweifeng.entities.Reader;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 *登陆过滤器
 */
public class ReaderLoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String url = request.getRequestURL().toString();
        Reader reader = (Reader) session.getAttribute("reader");
        BookAdmin bookAdmin = (BookAdmin) session.getAttribute("bookAdmin");
        if (reader != null || bookAdmin != null || url.endsWith("login.jsp")|| url.endsWith("image.jsp") || url.endsWith("register.jsp")){
            filterChain.doFilter(servletRequest,servletResponse);
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
