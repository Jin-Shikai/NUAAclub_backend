package web.Servlet;

import com.google.gson.Gson;
import dao.essayDao;
import domain.essay;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/submitEssay")
public class submitEssay extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //1. 获取essay库资源目录
        String res = getServletContext().getRealPath("/essay/");

        request.setCharacterEncoding("UTF-8");

        //1.获取网络请求的参数map
        Map m = request.getParameterMap();
        //2.初始化essay对象所需数据
        String text =((String[])m.get("text"))[0].toString();
        String createDate =((String[])m.get("createDate_New"))[0].toString();
        String latestDate =((String[])m.get("latestDate_New"))[0].toString();
        String userID =((String[])m.get("userID"))[0].toString();
        String creator =((String[])m.get("creator"))[0].toString();
        String essayID =((String[])m.get("essayID"))[0].toString();
        String status =((String[])m.get("status"))[0].toString();

        //3.装填需要写入数据库的map
        Map<String,String> essay = new HashMap<String,String>();
        //4.装填需要写入文件的map
        essay essForFile = new essay(
                                    creator,
                            "0",
                                    text,
                            essayID,
                            createDate,
                userID);
        essay.put("text",text);

        essay.put("createDate",createDate);

        essay.put("latestDate",latestDate);

        essay.put("userID",userID);

        essay.put("creator",creator);

        essay.put("essayID",essayID);

        essay.put("status",status);

        essay.put("replyCount","0");

        domain.essay ess = new essay();
        try
        {
            BeanUtils.populate(ess,essay);
            ess.setCreateDate_New(essForFile.getCreateDate_New());
            ess.setLatestDate_New(essForFile.getLatestDate_New());
            essayDao ed =new essayDao();
            ed.saveEssay(ess);
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        } catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
        //将文章必要信息以文件形式存储
        createFile(essForFile);
    }
    public void createFile(essay ess) throws IOException
    {
        String path= getServletContext().getRealPath("/essay/");
        File file = new File(path,ess.getEssayID()+".json");
        //1. 创建文件
        file.createNewFile();

        Writer write = null;
        //定义输出流
        write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");

        Gson gson = new Gson();
        String essayJson=gson.toJson(ess);
        write.write(essayJson);

        write.flush();

        write.close();
    }
}
