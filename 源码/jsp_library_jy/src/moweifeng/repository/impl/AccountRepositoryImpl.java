package moweifeng.repository.impl;

import moweifeng.entities.BookAdmin;
import moweifeng.entities.Reader;
import moweifeng.repository.AccountRepository;
import moweifeng.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.SQLException;

public class AccountRepositoryImpl implements AccountRepository {
    private Connection connection = null;
    private String sql = null;
    private QueryRunner queryRunner = new QueryRunner();
    @Override
    public BookAdmin adminLogin(String username,String password) {
        connection = JDBCUtils.getConnection();
        sql = "select * from bookadmin where username = ? and password = ?";
        BookAdmin account = null;
        try {
            account = queryRunner.query(connection,sql,new BeanHandler<>(BookAdmin.class),username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null,null);
        }
        return account;

    }

    @Override
    public Reader readerLogin(String username, String password) {
        connection = JDBCUtils.getConnection();
        sql = "select * from reader where username = ? and password = ?";
        Reader reader = null;
        try {
            reader = queryRunner.query(connection,sql,new BeanHandler<>(Reader.class),username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null,null);
        }
        return reader;
    }
    
    @Override
    public int readerRegister(String username, String password) {
  
        connection = JDBCUtils.getConnection();
        sql = "insert into reader(username,password,name,tel,cardid,gender) values(?,?,?,?,?,?)";
        try {
            queryRunner.update(connection,sql,username,password,"","","","男");
        } catch (SQLException e) {
            return 0;
        } finally {
            JDBCUtils.release(connection,null,null);
        }
        return 1;
    }
}
