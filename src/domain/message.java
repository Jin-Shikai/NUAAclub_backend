package domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class message
{
    public String getFromID()
    {
        return fromID;
    }

    public void setFromID(String fromID)
    {
        this.fromID = fromID;
    }

    public String getToID()
    {
        return toID;
    }

    public void setToID(String toID)
    {
        this.toID = toID;
    }

    public String getFromCreator()
    {
        return fromCreator;
    }

    public void setFromCreator(String fromCreator)
    {
        this.fromCreator = fromCreator;
    }

    public String getToCreator()
    {
        return toCreator;
    }

    public void setToCreator(String toCreator)
    {
        this.toCreator = toCreator;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }

    public List<BaseReply> getReplyList()
    {
        return replyList;
    }

    public void setReplyList(List<BaseReply> replyList)
    {
        this.replyList = replyList;
    }

    private String fromID;
    private String toID;
    private String fromCreator;
    private String toCreator;
    private String text;
    private String fileName;
    private int status;
    private String  createDate;
    private int replyCnt;
    //每条reply有自己的回复列表
    private List<BaseReply> replyList;

    public message(String fromID, String toID, String fromCreator, String toCreator, String text, String fileName, String createDateStr)
    {
        this.fromID = fromID;
        this.toID = toID;
        this.fromCreator = fromCreator;
        this.toCreator = toCreator;
        this.text = text;
        this.fileName = fileName;
        this.status = 2;
        this.createDate = createDateStr;
        this.replyCnt = 0;
        replyList = new ArrayList<BaseReply>();
    }
}
