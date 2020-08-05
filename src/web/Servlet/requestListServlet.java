package web.Servlet;

import dao.essayDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/requestListServlet")
public class requestListServlet extends HttpServlet
{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        doPost(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("ContentType","text/html;charset=UTF-8");
        //获取essayDAO操作类实体
        essayDao ed=new essayDao();
        //获得ID的list
        List<String> idList=ed.orderList();
        //对于每一个essayID,访问其文件,并将其写入response里
        //最终的成品JsonArray字符串,以"["开头,"]"结尾,其中每一个{}是一篇文章的信息
        StringBuffer respStr=new StringBuffer();
        respStr.append("[");
        //初始化文件路径:
        String path= getServletContext().getRealPath("/essay/");
        for(String ID:idList)
        {
            File file = new File(path,ID+".json");
            respStr.append(getContent(file));
            respStr.append(",");
        }
        respStr.append("]");
        resp.getWriter().write(respStr+"");
        System.out.println(respStr);

    }
    private String getContent(File file)
    {
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, "utf-8");
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + "utf-8");
            e.printStackTrace();
            return null;
        }
    }
}
