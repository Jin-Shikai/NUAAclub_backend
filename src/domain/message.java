package domain;

import java.util.ArrayList;
import java.util.List;

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

    public String getMessageID()
    {
        return messageID;
    }

    public void setMessageID(String messageID)
    {
        this.messageID = messageID;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
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
    private String messageID;
    private int status;
    //每条reply有自己的回复列表
    private List<BaseReply> replyList;

    public message(String fromID, String toID, String fromCreator, String toCreator, String text, String messageID)
    {
        this.fromID = fromID;
        this.toID = toID;
        this.fromCreator = fromCreator;
        this.toCreator = toCreator;
        this.text = text;
        this.messageID = messageID;
        this.status = 2;
        replyList = new ArrayList<BaseReply>();
    }
}
