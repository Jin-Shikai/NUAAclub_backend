package dao;

import domain.message;
import domain.user;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import util.JdbcUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class messageDao
{
    private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());

    public void insertMessage(message m)
    {
        try
        {
            String sql= "INSERT INTO message(fromID, toID, filename) VALUES(?,?,?)";
            Object[] obj = new Object[]{m.getFromID(), m.getToID(), m.getFileName()};
            template.update(sql,obj);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return;
    }

    //我的私信列表
    public List<String> myMsg(String userID)
    {
        String sql = "SELECT filename FROM message where fromID = "+userID+" or toID = " +userID+"";
        List<String> data = template.query(sql,
                new RowMapper<String>()
                {
                    public String mapRow(ResultSet rs, int rowNum) throws SQLException
                    {
                        return rs.getString("filename");
                    }
                },
                null);
        return data;
    }
}
