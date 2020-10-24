package dao;

import domain.essay;
import domain.token;
import domain.user;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import util.JdbcUtils;

/**
 * 操作数据库
 */
public class userDao
{
    //声明JDBCTemplate对象共用
    private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());
    /**
     * 用户注册方法
     * @param RegistUer
     */
    public user regist(user RegistUer)
    {
        user registUser =new user();
        registUser.setID(RegistUer.getID());
        registUser.setPassword(RegistUer.getPassword());
        try
        {
            String sql= "INSERT INTO user(ID,password,status) VALUES(?,?,?)";
            Object[] obj = new Object[]{RegistUer.getID(),"0",2};
            template.update(sql,obj);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return registUser;
    }
    /**
     * 从token获取ID方法
     * @param token,ID
     */
    public user getUserFromToken(String token)
    {
        user usertmp;
        String ID;
        try
        {
            String sql= "select * from token where tokenID = ?";
            ID=template.queryForObject(sql,
                    new BeanPropertyRowMapper<token>(token.class),
                    token).getID();
            String sql2= "select * from user where ID = ? ";
            usertmp=template.queryForObject(sql2,
                    new BeanPropertyRowMapper<user>(user.class),
                    ID);
        } catch (Exception e)
        {
            System.out.println("获取用户信息失败");
            return null;
        }
        System.out.println("查找到用户,ID"+usertmp.getID());
        return usertmp;
    }
    /**
     * 用户登录方法
     * @param LoginUser
     */
    public user login(user LoginUser)
    {
        user user=null;
        try
        {
            String sql = "select * from user where ID = ?";
            user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<user>(user.class),
                    LoginUser.getID());
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        if(user==null)//用户尚不存在
        {
            user = regist(LoginUser);
        }
        return user;
    }
    /**
     * 保存token方法
     * @param token,ID
     */
    public void saveToken(String token, String ID)
    {
        try
        {
            String sql= "INSERT INTO token(tokenID,ID) VALUES(?,?)";
            Object[] obj = new Object[]{token,ID};
            template.update(sql,obj);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void updatePassword(String ID, String newPassword)
    {
        try
        {
            //"INSERT INTO token(tokenID,ID) VALUES(?,?)";
            String sql= "UPDATE user SET password = ? WHERE ID = ?";
            Object[] obj = new Object[]{newPassword,ID};
            template.update(sql,obj);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
