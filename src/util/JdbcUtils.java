package util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * JDBC工具类, 使用Druid连接池
 */
public class JdbcUtils
{
    public static DataSource ds;

    static
    {
        Properties pro = new Properties();
        try
        {
            pro.load(JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    public static DataSource getDataSource()
    {
        return ds;
    }

    public static Connection getConnection() throws SQLException
    {
        return ds.getConnection();
    }
}