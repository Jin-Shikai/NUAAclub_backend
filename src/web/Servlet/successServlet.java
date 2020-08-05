package web.Servlet;

import domain.user;
import dao.userDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/successServlet")
public class successServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        user user = (user) request.getAttribute("user");
        if(user!=null)
        {
            response.setContentType("text/html;charset=utf-8");
            System.out.println("登录成功,"+user.getID()+"欢迎您");
            //生成token
            String token = UUID.randomUUID() + "";
            //保存token
            userDao ud=new userDao();
            ud.saveToken(token,user.getID());
            //返回token
            response.getWriter().write(token);
        }
    }
}
