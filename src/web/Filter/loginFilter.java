package web.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//返回101: 未通过过滤器检查
@WebFilter(urlPatterns = {"/newEssayServlet"})
public class loginFilter implements Filter
{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        //String token=request.getHeader("token");
        //从request中获取key为"token"的参数
        String token=request.getParameter("token");
        //设置编码
        response.setContentType("text/html;charset=utf-8");
        if(token==null || token.length()<5)
        {
            System.out.println("未通过过滤器检查");
            response.getWriter().write("101");
        }
        else
        {
            System.out.println("已通过过滤器检查");
            response.getWriter().write("102");
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy()
    {

    }
}
