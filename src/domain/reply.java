package domain;

import java.util.ArrayList;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

public class reply extends BaseReply
{
    //每条reply有自己的回复列表
    private List<BaseReply> replyList;
    private int baseReplyCnt;
}
