package web.Servlet;

import dao.essayDao;
import dao.messageDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@WebServlet("/requestMyMsgListServlet")
public class myMessageListServlet extends HttpServlet
{

    public String splitString(String str, String temp) {
        String result = null;
        if (str.indexOf(temp) != -1) {
            if (str.substring(str.indexOf(temp)).indexOf("&") != -1) {
                result = str.substring(str.indexOf(temp)).substring(str.substring(str.indexOf(temp)).indexOf("=")+1, str.substring(str.indexOf(temp)).indexOf("&"));
            } else {
                result = str.substring(str.indexOf(temp)).substring(str.substring(str.indexOf(temp)).indexOf("=")+1);
            }
        }
        return result;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        doPost(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("ContentType","text/html;charset=UTF-8");

        //获取ID
        //Map<String, String[]> m = req.getParameterMap();
        //String userID =((String[])m.get("ID"))[0].toString();

        //此处获取ID通过request.getInputStream()方法经转换后得到
        StringBuffer sb = new StringBuffer() ;
        InputStream is = req.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String s = "" ;
        while((s=br.readLine())!=null){
            sb.append(s) ;
        }
        String userID =sb.toString();

        //获取essayDAO操作类实体
        messageDao md = new messageDao();
        //获得ID的list
        List<String> idList=md.myMsg(userID);
        //对于每一个essayID,访问其文件,并将其写入response里
        //最终的成品JsonArray字符串,以"["开头,"]"结尾,其中每一个{}是一篇文章的信息
        StringBuffer respStr=new StringBuffer();
        respStr.append("[");
        //初始化文件路径:
        String path= getServletContext().getRealPath("/message/");
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
