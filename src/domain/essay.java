package domain;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class essay
{

    //*1.创建时间的字符串:
    private String createDate;

    //*2.最近修改时间的字符串:
    private String latestDate;
    //*3.创建者ID:
    private String userID;
    //*4.创建者匿名ID:
    private String creator;
    //*5.状态码: 1:置顶  2:普通  3:删除
    private String status;
    //*6.文本内容
    private String text;
    //*7.文章ID
    private String essayID;
    //8.回复数:
    private String replyCount;
    //9.回复列表
    private List<reply> replyList;

    //10.创建时间(datetime)
    private java.sql.Timestamp createDate_New;
    //11.最后更新时间(datetime)
    private java.sql.Timestamp latestDate_New;

    public Timestamp getLatestDate_New()
    {
        return latestDate_New;
    }

    public void setLatestDate_New(Timestamp latestDate_New)
    {
        this.latestDate_New = latestDate_New;
    }

    public essay(String creator,
                 String replycount,
                 String text,
                 String essayid,
                 String createdate)
    {
        this.createDate = createdate;
        this.creator = creator;
        this.replyCount = replycount;
        this.text = text;
        this.essayID = essayid;
        this.replyList = new ArrayList<reply>();
        this.createDate_New = new java.sql.Timestamp(0);

        try{

            SimpleDateFormat bartDateFormat =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date date_temp = bartDateFormat.parse(createDate);
            java.sql.Timestamp createDate_New = new java.sql.Timestamp(date_temp.getTime());
            this.createDate_New = createDate_New;
            this.latestDate_New = createDate_New;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public essay()
    {

    }

    public List<reply> getReplyList()
    {
        return replyList;
    }

    public void setReplyList(List<reply> replyList)
    {
        this.replyList = replyList;
    }

    public String getReplyCount()
    {
        return replyCount;
    }

    public void setReplyCount(String replyCount)
    {
        this.replyCount = replyCount;
    }

    public String getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }

    public String getLatestDate()
    {
        return latestDate;
    }

    public void setLatestDate(String latestDate)
    {
        this.latestDate = latestDate;
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

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getEssayID()
    {
        return essayID;
    }

    public void setEssayID(String essayID)
    {
        this.essayID = essayID;
    }


    public java.sql.Timestamp getCreateDate_New()
    {
        return createDate_New;
    }

    public void setCreateDate_New(java.sql.Timestamp CreateDate_New)
    {
        this.createDate_New = CreateDate_New;
    }

}
