package moweifeng.repository.impl;

import moweifeng.entities.*;
import moweifeng.repository.BookRepository;
import moweifeng.repository.ReaderRepository;
import moweifeng.utils.JDBCUtils;
import moweifeng.utils.PubUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReaderRepositoryImpl implements ReaderRepository {
    private Connection connection = null;
    private String sql = null;
    private QueryRunner queryRunner = new QueryRunner();
    private BookRepository bookRepository = new BookRepositoryImpl();
    @Override
    public List<Borrow> selectBorrowsByReaderId(int readerId,int start,int limit) {
        connection = JDBCUtils.getConnection();
        sql = "select * from borrow where readerid = ? limit ?,?";
        List<Borrow> borrows = new ArrayList<>();
        try {
            List<Borrow> borrowList = queryRunner.query(connection,sql,new BeanListHandler<>(Borrow.class),readerId,start,limit);
            Reader reader = queryRunner.query(connection,"select * from reader where id = ?",new BeanHandler<>(Reader.class),readerId);
            for (int i = 0; i < borrowList.size(); i++) {
                Borrow borrow = borrowList.get(i);
                BookAdmin bookAdmin = null;
                if (borrow.getState() == 1 || borrow.getState() == 2 || borrow.getState() == 3){
                    List<Integer> bAdmins = queryRunner.query(connection,"select adminid from borrow where id = ?",new ColumnListHandler<>(),borrow.getId());
                    bookAdmin = queryRunner.query(connection,"select * from bookadmin where id = ?",new BeanHandler<>(BookAdmin.class),bAdmins.get(0));
                }
                List<Integer> bids = queryRunner.query(connection,"select bookid from borrow where id = ?",new ColumnListHandler<>(),borrow.getId());
                List<Integer> caseIds = queryRunner.query(connection,"select bookcaseid from book where id = ?",new ColumnListHandler<>(),bids.get(0));
                BookCase bookCase =  queryRunner.query(connection,"select * from bookcase where id = ?",new BeanHandler<>(BookCase.class),caseIds.get(0));
                Book book =  queryRunner.query(connection,"select * from book where id = ?",new BeanHandler<>(Book.class),bids.get(0));
                book.setBookCase(bookCase);
                borrow.setBook(book);
                borrow.setReader(reader);
                borrows.add(borrow);
                borrow.setBookAdmin(bookAdmin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null,null);
        }
        return borrows;
    }

    @Override
    public List<Borrow> selectAllBorrows() {
        connection = JDBCUtils.getConnection();
        sql = "select * from borrow where state = 0 or state = 2";
        List<Borrow> borrowList = null;
        List<Borrow> borrows = new ArrayList<>();
        try {
            borrowList = queryRunner.query(connection,sql,new BeanListHandler<>(Borrow.class));
            for (int i = 0; i < borrowList.size(); i++) {
                Borrow borrow = borrowList.get(i);
                BookAdmin bookAdmin = null;
                if (borrow.getState() == 1 || borrow.getState() == 2 || borrow.getState() == 3){
                    List<Integer> bAdmins = queryRunner.query(connection,"select adminid from borrow where id = ?",new ColumnListHandler<>(),borrow.getId());
                    bookAdmin = queryRunner.query(connection,"select * from bookadmin where id = ?",new BeanHandler<>(BookAdmin.class),bAdmins.get(0));
                    borrow.setBookAdmin(bookAdmin);
                } else {
                    borrow.setBookAdmin(null);
                }
                List<Integer> bids = queryRunner.query(connection,"select bookid from borrow where id = ?",new ColumnListHandler<>(),borrow.getId());
                List<Integer> caseIds = queryRunner.query(connection,"select bookcaseid from book where id = ?",new ColumnListHandler<>(),bids.get(0));
                List<Integer> rids = queryRunner.query(connection,"select readerid from borrow where id = ?",new ColumnListHandler<>(),borrow.getId());
                BookCase bookCase =  queryRunner.query(connection,"select * from bookcase where id = ?",new BeanHandler<>(BookCase.class),caseIds.get(0));
                Book book =  queryRunner.query(connection,"select * from book where id = ?",new BeanHandler<>(Book.class),bids.get(0));
                Reader reader =  queryRunner.query(connection,"select * from reader where id = ?",new BeanHandler<>(Reader.class),rids.get(0));
                book.setBookCase(bookCase);
                borrow.setBook(book);
                borrow.setReader(reader);
                borrows.add(borrow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null,null);
        }

        return borrows;
    }

    /**
     * 获取每个读者对应的借阅图书的数量
     * @param readerId
     * @return
     */
    @Override
    public int selectBorrowsCountByReaderId(int readerId) {
        connection = JDBCUtils.getConnection();
        sql = "select count(*) from borrow where readerid = ?";
        return PubUtils.getCount(queryRunner,connection,sql,readerId);
    }

    /**
     * 获取所有的未审核状态下的借阅数量
     */
    @Override
    public int selectBorrowCountsByState() {
        connection = JDBCUtils.getConnection();
        sql = "select count(*) from borrow where state != 3";
        return PubUtils.getCount(queryRunner,connection,sql,0);
    }


    @Override
    public void saveBorrow(Borrow borrow) {
        connection = JDBCUtils.getConnection();
        sql = "insert into borrow(readerid,bookid,borrowtime,returntime,adminid,state) values(?,?,?,?,?,?)";
        try {
            queryRunner.update(connection,sql,borrow.getReader().getId(),borrow.getBook().getId(),borrow.getBorrowtime(),borrow.getReturntime(),borrow.getBookAdmin() == null ? null : borrow.getBookAdmin().getId(),borrow.getState());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null,null);
        }
    }

    @Override
    public Borrow selectBorrowById(int borrowId) {

        connection = JDBCUtils.getConnection();
        Borrow borrow = null;
        try {
            List<Integer> bids = queryRunner.query(connection,"select bookid from borrow where id = ?",new ColumnListHandler<>(),borrowId);
            List<Integer> rids = queryRunner.query(connection,"select readerid from borrow where id = ?",new ColumnListHandler<>(),borrowId);
            List<Integer> bAdmins = queryRunner.query(connection,"select adminid from borrow where id = ?",new ColumnListHandler<>(),borrowId);
            Book book = bookRepository.selectById(bids.get(0));
            Reader reader = this.selectReaderById(rids.get(0));
            connection = JDBCUtils.getConnection();
            sql = "select * from borrow where id = ?";
            borrow = queryRunner.query(connection,sql,new BeanHandler<>(Borrow.class),borrowId);
            if (bAdmins.size() != 0){
                BookAdmin bookAdmin = queryRunner.query(connection,"select * from bookadmin where id = ?",new BeanHandler<>(BookAdmin.class),bAdmins.get(0));
                borrow.setBookAdmin(bookAdmin);
            }
            borrow.setReader(reader);
            borrow.setBook(book);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return borrow;
    }



    @Override
    public List<Reader> selectReaders(int start, int limit) {
        connection = JDBCUtils.getConnection();
        sql = "select * from reader limit ?,?";
        List<Reader> readerList = null;
        try {
            readerList = queryRunner.query(connection,sql,new BeanListHandler<>(Reader.class),start,limit);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null,null);
        }
        return readerList;
    }

    @Override
    public int selectReaderCount() {
        connection = JDBCUtils.getConnection();
        sql = "select count(*) from reader";
        return PubUtils.getCount(queryRunner,connection,sql,0);
    }

    @Override
    public Reader selectReaderById(int readerId) {
        connection = JDBCUtils.getConnection();
        sql = "select * from reader where id = ?";
        Reader reader = null;
        try {
            reader = queryRunner.query(connection,sql,new BeanHandler<>(Reader.class),readerId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null,null);
        }
        return reader;
    }

    @Override
    public void updateReader(Reader reader) {
        connection = JDBCUtils.getConnection();
        sql = "update reader set username = ?,password = ?,name = ?,tel = ?,cardid = ?,gender = ? where id = ?";
        try {
            queryRunner.update(connection,sql,reader.getUsername(),reader.getPassword(),reader.getName(),reader.getTel(),reader.getCardid(),reader.getGender(),reader.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null,null);
        }
    }

    @Override
    public void deleteReaderById(int readerId) {
        connection = JDBCUtils.getConnection();
        sql = "delete from reader where id = ?";
        try {
            queryRunner.update(connection,sql,readerId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null,null);
        }
    }

    @Override
    public void saveReader(Reader reader) {
        connection = JDBCUtils.getConnection();
        sql = "insert into reader(name,username,password,tel,cardid,gender) values(?,?,?,?,?,?)";
        try {
            queryRunner.update(connection,sql,reader.getName(),reader.getUsername(),reader.getPassword(),reader.getTel(),reader.getCardid(),reader.getGender());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null,null);
        }
    }

    @Override
    public Borrow selectBorrowByIds(int bookId, int readerId, int state) {
        connection = JDBCUtils.getConnection();
        sql = "select * from borrow where bookid = ? and readerid = ? and state = ?";
        Borrow borrow = null;
        List<Integer> bAdmins = null;
        BookAdmin bookAdmin = null;
        try {
            borrow = queryRunner.query(connection,sql,new BeanHandler<>(Borrow.class),bookId,readerId,state);
            if (borrow != null){
                Book book = queryRunner.query(connection,"select * from book where id = ?",new BeanHandler<>(Book.class),bookId);
                Reader reader = queryRunner.query(connection,"select * from reader where id = ?",new BeanHandler<>(Reader.class),readerId);
                bAdmins = queryRunner.query(connection,"select adminid from borrow where id = ?",new ColumnListHandler<>(),borrow.getId());
                bookAdmin = queryRunner.query(connection,"select * from bookadmin where id = ?",new BeanHandler<>(BookAdmin.class),bAdmins.get(0));
                borrow.setBookAdmin(bookAdmin);
                borrow.setBook(book);
                borrow.setReader(reader);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null,null);
        }
        return borrow;
    }

    @Override
    public List<Borrow> selectAllBorrowByReaderId(int readerId) {
        connection = JDBCUtils.getConnection();
        sql = "select * from borrow where readerid = ?";
        List<Borrow> borrows = new ArrayList<>();
        try {
            List<Borrow> borrowList = queryRunner.query(connection,sql,new BeanListHandler<>(Borrow.class),readerId);
            Reader reader = queryRunner.query(connection,"select * from reader where id = ?",new BeanHandler<>(Reader.class),readerId);
            for (int i = 0; i < borrowList.size(); i++) {
                Borrow borrow = borrowList.get(i);
                BookAdmin bookAdmin = null;
//                if (borrow.getState() == 1 || borrow.getState() == 2 || borrow.getState() == 3){
                List<Integer> bAdmins = queryRunner.query(connection,"select adminid from borrow where id = ?",new ColumnListHandler<>(),borrow.getId());
                bookAdmin = queryRunner.query(connection,"select * from bookadmin where id = ?",new BeanHandler<>(BookAdmin.class),bAdmins.get(0));
//                }
                List<Integer> bids = queryRunner.query(connection,"select bookid from borrow where id = ?",new ColumnListHandler<>(),borrow.getId());
                List<Integer> caseIds = queryRunner.query(connection,"select bookcaseid from book where id = ?",new ColumnListHandler<>(),bids.get(0));
                BookCase bookCase =  queryRunner.query(connection,"select * from bookcase where id = ?",new BeanHandler<>(BookCase.class),caseIds.get(0));
                Book book =  queryRunner.query(connection,"select * from book where id = ?",new BeanHandler<>(Book.class),bids.get(0));
                book.setBookCase(bookCase);
                borrow.setBook(book);
                borrow.setReader(reader);
                borrows.add(borrow);
                borrow.setBookAdmin(bookAdmin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null,null);
        }
        return borrows;
    }
}
