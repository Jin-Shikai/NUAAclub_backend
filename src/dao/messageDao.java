package dao;

import domain.message;
import domain.user;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JdbcUtils;

public class messageDao
{
    private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());

    public void insertMessage(message m)
    {
        try
        {
            String sql= "INSERT INTO message(fromID, toID, filename) VALUES(?,?,?)";
            Object[] obj = new Object[]{m.getFromID(), m.getToID(), m.getMessageID()};
            template.update(sql,obj);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return;
    }
}
