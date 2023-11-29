package moweifeng.repository.impl;

import moweifeng.entities.Book;
import moweifeng.entities.BookCase;
import moweifeng.repository.BookRepository;
import moweifeng.utils.JDBCUtils;
import moweifeng.utils.PubUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 图书数据访问接口实现类
 */
public class BookRepositoryImpl implements BookRepository {
    private Connection connection = null;
    private QueryRunner queryRunner = new QueryRunner();
    private String sql = null;
    @Override
    public void save(Book book) {
        connection = JDBCUtils.getConnection();
        sql = "insert into book(name,pages,price,publish,author,bookcaseid,abled) values(?,?,?,?,?,?,?)";
        try {
            queryRunner.update(connection,sql,book.getName(),book.getPages(),book.getPrice(),book.getPublish(),book.getAuthor(),book.getBookCase().getId(),book.getAbled());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null,null);
        }
    }

    @Override
    public void deleteById(int id) {
        connection = JDBCUtils.getConnection();
        sql = "delete from book where id = ?";
        try {
            queryRunner.update(connection,sql,id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null,null);
        }

    }

    @Override
    public void update(Book book) {
        connection = JDBCUtils.getConnection();
        sql = "update book set name = ?,author = ?,publish = ?,pages = ?,price = ?,bookcaseid = ?,abled = ? where id = ?";
        try {
            queryRunner.update(connection,sql,book.getName(),book.getAuthor(),book.getPublish(),book.getPages(),book.getPrice(),book.getBookCase().getId(),book.getAbled(),book.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null,null);
        }
    }

    @Override
    public Book selectById(int id) {
        connection = JDBCUtils.getConnection();
        sql = " select * from book where id = ?";
        Book book = null;
        try {
            book = queryRunner.query(connection,sql,new BeanHandler<>(Book.class),id);
            List<Integer> bcids = queryRunner.query(connection,"select bookcaseid from book where id = ?",new ColumnListHandler<>(),id);
            BookCase bookCase = queryRunner.query(connection,"select * from bookcase where id = ?",new BeanHandler<>(BookCase.class),bcids.get(0));
            book.setBookCase(bookCase);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null,null);
        }
        return book;
    }

    @Override
    public List<Book> selectAll(int start,int limit) {
        connection = JDBCUtils.getConnection();
        sql = "select * from book where abled = 1 limit ?,?";
        List<Book> books = null;
        try {
            books = queryRunner.query(connection,sql,new BeanListHandler<>(Book.class),start,limit);
            for (int i = 0; i < books.size(); i++) {
                int bookId = books.get(i).getId();
                Connection caseConnection = JDBCUtils.getConnection();
                String caseSql = "select bookcaseid from book where id = ?";
                List<Integer> cidList = queryRunner.query(caseConnection,caseSql,new ColumnListHandler<Integer>(),bookId);
                Connection cConnection = JDBCUtils.getConnection();
                String cSql = "select * from bookcase where id = ?";
                BookCase bookCase = queryRunner.query(cConnection,cSql,new BeanHandler<>(BookCase.class),cidList.get(0));
                books.get(i).setBookCase(bookCase);
                JDBCUtils.release(caseConnection,null,null);
                JDBCUtils.release(cConnection,null,null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null,null);
        }
        return books;
    }

    @Override
    public int selectBooksCount() {
        connection = JDBCUtils.getConnection();
        sql = "select count(*) from book where abled = 1";
        return PubUtils.getCount(queryRunner,connection,sql,0);
    }
    @Override
    public List<Book> selectAllBooksForPoi() {
        List<Book> books = new ArrayList<>();
        try {
            connection = JDBCUtils.getConnection();
            sql = "select * from book";
            List<Book> bookList = queryRunner.query(connection,sql,new BeanListHandler<>(Book.class));
            for (Book book:bookList) {
                List<Integer> bookCaseIds = queryRunner.query(connection,"select bookcaseid from book where id = ?",new ColumnListHandler<>(),book.getId());
                for (int i = 0;i < bookCaseIds.size();i++) {
                    BookCase bookCase = queryRunner.query(connection,"select * from bookcase where id = ?",new BeanHandler<>(BookCase.class),bookCaseIds.get(i));
                    book.setBookCase(bookCase);
                }
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null,null);
        }

        return books;
    }
}
