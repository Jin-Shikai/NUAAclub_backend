package domain;

public class comment
{
    //评论ID:每条reply的唯一标识
    private String commentID;
    //创建时间的字符串:
    private String createDate;
    //创建者ID:
    private String userID;
    //创建者匿名ID:
    private String creator;
    //评论内容:
    private String text;

    public String getCommentID()
    {
        return commentID;
    }

    public void setCommentID(String commentID)
    {
        this.commentID = commentID;
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
