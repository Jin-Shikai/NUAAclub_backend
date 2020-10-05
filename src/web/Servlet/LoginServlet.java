package web.Servlet;

import dao.userDao;
import domain.user;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        this.doGet(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取所有请求参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        user loginUser = new user();
        //用BeanUtils封装类
        try
        {
            BeanUtils.populate(loginUser,parameterMap);
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        } catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }

        //用login方法检测:
        userDao userDao= new userDao();
        user user = userDao.login(loginUser);
        //登录成功:先存储数据,再转发
        request.setAttribute("user",user);
        request.getSession().setAttribute("user",user);
        request.getRequestDispatcher("/successServlet").forward(request,response);
    }
}
