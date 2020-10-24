package web.Servlet;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

@WebServlet("/deleteBaseReply")
public class deleteBaseReplyServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        request.setCharacterEncoding("UTF-8");

        //1.获取网络请求的参数map
        Map m = request.getParameterMap();
        //2.初始化essay对象所需数据
        String essayID =((String[])m.get("essayID"))[0].toString();
        String floor =((String[])m.get("floor"))[0].toString();
        String baseFloor =((String[])m.get("baseFloor"))[0].toString();
        System.out.println(essayID);
        System.out.println(floor);


        //按帖子ID找到帖子文件,得到JSON对象
        String path= getServletContext().getRealPath("/essay/");
        File file = new File(path,essayID+".json");

        //设置参数
        String content= FileUtils.readFileToString(file,"UTF-8");
        //得到文件的json对象essayJson
        try
        {
            JSONObject essayJson = new JSONObject(content);
            essayJson.getJSONArray("replyList").getJSONObject(Integer.valueOf(floor)).getJSONArray("BaseReplyList")
                    .getJSONObject(Integer.valueOf(baseFloor)-1).put("status","0");
            //将回复数置--
            int old = Integer.valueOf(essayJson.getJSONArray("replyList").getJSONObject(Integer.valueOf(floor)).get("baseReplyCnt").toString());
            essayJson.getJSONArray("replyList").getJSONObject(Integer.valueOf(floor)).put("baseReplyCnt",old-1);

            Writer write;
            //定义输出流
            write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            //将essayJson写回文件中
            String str= essayJson.toString();
            write.write(str);
            write.flush();
            write.close();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
