package web.Servlet;

import com.google.gson.Gson;
import dao.messageDao;
import domain.essay;
import domain.message;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

@WebServlet("/submitMessageServlet")
public class submitMessageServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //设置字符编码
        request.setCharacterEncoding("UTF-8");

        Map<String, String[]> parameterMap = request.getParameterMap();
        String fromID =((String[])parameterMap.get("fromID"))[0];
        String toID =((String[])parameterMap.get("toID"))[0];
        String fromCreator =((String[])parameterMap.get("fromCreator"))[0];
        String toCreator =((String[])parameterMap.get("toCreator"))[0];
        String fileName =((String[])parameterMap.get("fileName"))[0];
        String text =((String[])parameterMap.get("text"))[0];
        String createDate = parameterMap.get("createDate")[0];
        String createDate_New = parameterMap.get("createDate_New")[0];

        message msgForFile = new message(fromID,toID,fromCreator,toCreator,text,fileName, createDate);

        //按filename找到帖子文件,得到JSON对象
        // 获取message库资源目录
        String path = getServletContext().getRealPath("/message/");
        //设置文件
        File msgfile = new File(path,fileName+".json");
        //1. 创建文件
        msgfile.createNewFile();
        Writer write = null;
        //定义输出流
        write = new OutputStreamWriter(new FileOutputStream(msgfile), "UTF-8");
        Gson gson = new Gson();
        String essayJson=gson.toJson(msgForFile);
        write.write(essayJson);
        write.flush();
        write.close();
        messageDao md = new messageDao();
        md.insertMessage(msgForFile);
    }
}
