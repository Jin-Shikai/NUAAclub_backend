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

@WebServlet("/updatePassword")
public class updatePasswordServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        this.doGet(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        //设置字符编码
        request.setCharacterEncoding("UTF-8");

        //获取网络请求的参数map
        Map m = request.getParameterMap();

        //获取文本内容  创建时间  userID  匿名ID  帖子ID
        String ID =((String[])m.get("ID"))[0].toString();
        String newPassword =((String[])m.get("newPassword"))[0].toString();

        //用login方法检测:
        userDao userDao= new userDao();
        userDao.updatePassword(ID,newPassword);

    }
}
