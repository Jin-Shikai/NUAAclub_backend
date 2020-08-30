package domain;

import java.util.ArrayList;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class reply
{
    //回复ID:每条reply的唯一标识
    private String replyID;
    //创建时间的字符串:
    private String createDate;
    //创建者ID:
    private String userID;
    //创建者匿名ID:
    private String creator;
    //文本内容
    private String text;
    //楼号
    private int floor;

    public int getFloor()
    {
        return floor;
    }

    public void setFloor(int floor)
    {
        this.floor = floor;
    }

    public String getReplyID()
    {
        return replyID;
    }

    public void setReplyID(String replyID)
    {
        this.replyID = replyID;
    }

    public String getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }

    public String getUserID()
    {
        return userID;
    }

    public void setUserID(String userID)
    {
        this.userID = userID;
    }

    public String getCreator()
    {
        return creator;
    }

    public void setCreator(String creator)
    {
        this.creator = creator;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

}
