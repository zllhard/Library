package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Druid连接池工具类
 */
public class JdbcUtils {

    private static DataSource ds;

    static {
        try {
            //加载配置文件
            Properties properties = new Properties();
            properties.load(JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            //获取DataSource
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * 关闭两个资源
     * @param statement
     * @param connection
     */
    public static void close(Statement statement, Connection connection) {
        close(null, statement, connection);
    }

    /**
     * 关闭三个资源
     * @param resultSet
     * @param statement
     * @param connection
     */
    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    /**
     * 获取数据库连接池
     * @return
     */
    public static DataSource getDataSource() {
        return ds;
    }
}
