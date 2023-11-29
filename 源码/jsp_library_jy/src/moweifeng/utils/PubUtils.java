package moweifeng.utils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;

public class PubUtils {
    //若没有最后一个参数传递0
    public static int getCount(QueryRunner queryRunner, Connection connection,String sql,int param){
        int count = 0;
        try {
            if (param == 0){
                count = ((Long) queryRunner.query(connection,sql,new ScalarHandler<>())).intValue();
            } else {
                count = ((Long) queryRunner.query(connection,sql,new ScalarHandler<>(),param)).intValue();
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null,null);
        }
        return count;
    }
}
