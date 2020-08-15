package dao;

import domain.essay;
import domain.user;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import util.JdbcUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class essayDao
{
    //声明JDBCTemplate对象共用
    private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());

    /**
     * 保存essay方法
     *
     * @param ess
     */
    public void saveEssay(essay ess)
    {
        String sql = "INSERT INTO essay(" +
                "essayID," +
                "text," +
                "userID," +
                "creator," +
                "status," +
                "createDate," +
                "latestDate," +
                "replyCount," +
                "createDate_New" +
                ")" +
                "VALUES(?,?,?,?,?,?,?,?,?)";
        Object[] obj = new Object[]{
                ess.getEssayID(),
                ess.getText(),
                ess.getUserID(),
                ess.getCreator(),
                ess.getStatus(),
                ess.getCreateDate(),
                ess.getLatestDate(),
                ess.getReplyCount(),
                ess.getCreateDate_New()
        };
        try
        {
            template.update(sql, obj);
        } catch (Exception e)
        {
            System.out.println(e);
        }
    }

    //按最新更新时间排序  获取前_条贴文
    public List<String> orderList()
    {
        String sql = "SELECT essayID FROM essay ORDER BY latestDate DESC LIMIT 0,10;";
        //List orderList=template.queryForList(sql);
        List<String> data = template.query(sql,
                new RowMapper<String>()
                {
                    public String mapRow(ResultSet rs, int rowNum) throws SQLException
                    {
                        return rs.getString("essayID");
                    }
                },
                null);
        return data;
    }

    //刷新更新时间
    public void refreshLatestDate(String essayID, String date)
    {
        String sql = "UPDATE essay SET latestDate = ? WHERE essayID = ?;";
        Object[] obj = new Object[]{date, essayID};
        try
        {
            template.update(sql, obj);
        } catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
