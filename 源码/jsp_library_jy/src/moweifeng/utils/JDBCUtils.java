package moweifeng.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ：数据库资源工具类
 */
public class JDBCUtils {
    private static DataSource dataSource = null;
    private static Connection connection = null;
    static {
        dataSource = new ComboPooledDataSource("library-config");
    }
    /**
     * 获取数据库连接对象
     **/

    public static Connection getConnection(){
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    /**
     *  释放数据库连接资源
     **/

    public static void release(Connection connection, Statement statement, ResultSet resultSet){
        try {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
