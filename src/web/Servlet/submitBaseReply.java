package web.Servlet;


import dao.essayDao;
import domain.BaseReply;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/submitBaseReplyServlet")
public class submitBaseReply extends HttpServlet
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
        String text =((String[])m.get("text"))[0].toString();
        String createDate =((String[])m.get("createDate"))[0].toString();
        String userID =((String[])m.get("userID"))[0].toString();
        String creator =((String[])m.get("creator"))[0].toString();
        String essayID =((String[])m.get("essayID"))[0].toString();
        int floor =Integer.parseInt(((String[])m.get("floor"))[0].toString());

        //按帖子ID找到帖子文件,得到JSON对象
        String path= getServletContext().getRealPath("/essay/");
        File file = new File(path,essayID+".json");

        //设置参数
        String content= FileUtils.readFileToString(file,"UTF-8");
        try
        {
            //得到文件的json对象essayJson
            JSONObject essayJson=new JSONObject(content);
            //需要添加的回复的信息用map初始化一个json对象
            JSONObject thisBaseReply = new JSONObject();
            thisBaseReply.put("status","1");
            thisBaseReply.put("createDate",createDate);
            thisBaseReply.put("userID",userID);
            thisBaseReply.put("creator",creator);
            thisBaseReply.put("text",text);
            System.out.println(floor);
            ((JSONObject)  essayJson.getJSONArray("replyList").get(floor-1)).accumulate("BaseReplyList", thisBaseReply);

//            //将thisReply累积到回复数组中
//            essayJson.accumulate("replyList",thisBaseReplay);
//
//            //将回复数置++
//            essayJson.put("replyCount",Integer.parseInt(essayJson.get("replyCount").toString())+1);
//
//
            Writer write;
            //定义输出流
            write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            //将essayJson写回文件中
            String str= essayJson.toString();
            write.write(str);
            write.flush();
            write.close();
            essayDao ed = new essayDao();
            ed.refreshLatestDate(essayID,createDate);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
