package web.Servlet;

import dao.userDao;
import domain.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getUserOfLogin")
public class getUserOfLogin extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("utf-8");

        //获取headers中的参数
        String token=request.getHeader("token");
        userDao userDao=new userDao();
        user user=userDao.getUserFromToken(token);
        request.setAttribute("user",user);
    }
}
