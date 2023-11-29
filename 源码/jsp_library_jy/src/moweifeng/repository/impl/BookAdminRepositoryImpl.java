package moweifeng.repository.impl;

import moweifeng.entities.*;
import moweifeng.repository.BookAdminRepository;
import moweifeng.repository.BookRepository;
import moweifeng.repository.ReaderRepository;
import moweifeng.utils.JDBCUtils;
import moweifeng.utils.PubUtils;
import moweifeng.vo.BookBarData;
import moweifeng.vo.BookPieData;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookAdminRepositoryImpl implements BookAdminRepository {
    private Connection connection = null;
    private QueryRunner queryRunner = new QueryRunner();
    private BookRepository bookRepository = new BookRepositoryImpl();
    private ReaderRepository readerRepository = new ReaderRepositoryImpl();
    private String sql = null;
    @Override
    public void saveBookCase(BookCase bookCase) {
        connection = JDBCUtils.getConnection();
        sql = "insert into bookcase(name) values(?)";
        try {
            queryRunner.update(connection,sql,bookCase.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null,null);
        }

    }

    @Override
    public void deleteBookCaseById(int bookCaseId) {
        connection = JDBCUtils.getConnection();
        List<Book> bookList = new ArrayList<>();
        Book book = null;
        try {
            List<Integer> bookIds = queryRunner.query(connection,"select id from book where bookcaseid = ?",new ColumnListHandler<>(),bookCaseId);
            for (int i = 0; i < bookIds.size(); i++) {
                book = queryRunner.query(connection,"select *  from book where id = ?",new BeanHandler<>(Book.class),bookIds.get(i));
                bookList.add(book);
            }
            if (bookList.size() > 0){
                for (int i = 0; i < bookList.size(); i++) {
                    book.setBookCase(null);
                    queryRunner.update(connection,"update book set bookcaseid = ? where id = ?",book.getBookCase() == null ? null : book.getBookCase().getId(),book.getId());
                }
            }
            sql = "delete from bookcase where id = ?";
            queryRunner.update(connection,sql,bookCaseId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null,null);
        }

    }

    @Override
    public void updateBookCase(BookCase bookCase) {
        connection = JDBCUtils.getConnection();
        sql = "update bookcase set name = ? where id = ?";
        try {
            queryRunner.update(connection,sql,bookCase.getName(),bookCase.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null,null);
        }
    }

    @Override
    public BookCase selectBookCaseById(int bookCaseId) {
        connection = JDBCUtils.getConnection();
        sql = "select * from bookcase where id = ?";
        BookCase bookCase = null;
        try {
            bookCase = queryRunner.query(connection,sql,new BeanHandler<>(BookCase.class),bookCaseId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null,null);
        }
        return bookCase;
    }

    @Override
    public List<BookCase> selectAllBookCases() {
        connection = JDBCUtils.getConnection();
        sql = "select * from bookcase";
        List<BookCase> bookCaseList = null;
        try {
            bookCaseList = queryRunner.query(connection,sql,new BeanListHandler<>(BookCase.class));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null,null);
        }
        return bookCaseList;
    }

    @Override
    public List<BookCase> selectAllBookCasesByPage(int start, int limit) {
        connection = JDBCUtils.getConnection();
        sql = "select * from bookcase limit ?,?";
        List<BookCase> bookCaseList = null;
        try {
            bookCaseList = queryRunner.query(connection,sql,new BeanListHandler<>(BookCase.class),start,limit);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null,null);
        }
        return bookCaseList;
    }

    @Override
    public int selectAllCount() {
        int count = 0;
        connection = JDBCUtils.getConnection();
        sql = "select count(*) from bookcase";
        count = PubUtils.getCount(queryRunner,connection,sql,0);
        return count;
    }


    @Override
    public void updateBorrow(Borrow borrow) {
        connection = JDBCUtils.getConnection();
        sql = "update borrow set state = ?,readerid = ?,bookid = ?,borrowtime = ?,returntime = ?,adminid = ? where id = ?";
        try {
            queryRunner.update(connection,sql,borrow.getState(),borrow.getReader().getId(),borrow.getBook().getId(),borrow.getBorrowtime(),borrow.getReturntime() == null ? null : borrow.getReturntime(),borrow.getBookAdmin().getId(),borrow.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null,null);
        }
    }


    @Override
    public void saveReturnBook(ReturnBook returnBook) {
        connection = JDBCUtils.getConnection();
        sql = "insert into returnbook(bookid,readerid,returntime,adminid,state) values(?,?,?,?,?)";
        try {
            queryRunner.update(connection,sql,returnBook.getBook().getId(),returnBook.getReader().getId(),returnBook.getReturntime(),returnBook.getBookAdmin() == null ? null : returnBook.getBookAdmin().getId(),returnBook.getState());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null,null);
        }
    }


    @Override
    public List<ReturnBook> selectAllReturns() {
        connection = JDBCUtils.getConnection();
        sql = "select * from returnbook where state = 4 or state = 5";
        List<ReturnBook> returnBookList = new ArrayList<>();
        try {
            List<ReturnBook> returnBooks = queryRunner.query(connection,sql,new BeanListHandler<>(ReturnBook.class));
            for (int i = 0; i < returnBooks.size(); i++) {
                ReturnBook returnBook = returnBooks.get(i);
                List<Integer> bids = queryRunner.query(connection,"select bookid from returnbook where id = ?",new ColumnListHandler<>(),returnBook.getId());
                List<Integer> rids = queryRunner.query(connection,"select readerid from returnbook where id = ?",new ColumnListHandler<>(),returnBook.getId());
                List<Integer> bAdmins = queryRunner.query(connection,"select adminid from returnbook where id = ?",new ColumnListHandler<>(),returnBook.getId());
                Book book = queryRunner.query(connection,"select * from book where id = ?",new BeanHandler<>(Book.class),bids.get(0));
                Reader reader =  queryRunner.query(connection,"select * from reader where id = ?",new BeanHandler<>(Reader.class),rids.get(0));
                BookAdmin bookAdmin =  queryRunner.query(connection,"select * from bookadmin where id = ?",new BeanHandler<>(BookAdmin.class),bAdmins.get(0));
                returnBook.setBook(book);
                returnBook.setReader(reader);
                returnBook.setBookAdmin(bookAdmin);
                returnBookList.add(returnBook);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null,null);
        }
        return returnBookList;
    }

    @Override
    public int selectReturnCount() {
        connection = JDBCUtils.getConnection();
        sql = "select count(*) from returnbook where state != 1";
        int count = 0;
        try {
            count = ((Long) queryRunner.query(connection,sql,new ScalarHandler<>())).intValue();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null,null);
        }
        return count;
    }

    @Override
    public ReturnBook selectReturnById(int returnId) {
        connection = JDBCUtils.getConnection();
        sql = "select * from returnbook where id = ?";
        ReturnBook returnBook = null;
        try {
            returnBook = queryRunner.query(connection,sql,new BeanHandler<>(ReturnBook.class),returnId);
            List<Integer> bids = queryRunner.query(connection,"select bookid from returnbook where id = ?",new ColumnListHandler<>(),returnBook.getId());
            List<Integer> rids = queryRunner.query(connection,"select readerid from returnbook where id = ?",new ColumnListHandler<>(),returnBook.getId());
            Book book = queryRunner.query(connection,"select * from book where id = ?",new BeanHandler<>(Book.class),bids.get(0));
            Reader reader= queryRunner.query(connection,"select * from reader where id = ?",new BeanHandler<>(Reader.class),rids.get(0));
            returnBook.setBook(book);
            returnBook.setReader(reader);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null,null);
        }
        return returnBook;
    }

    @Override
    public void updateReturn(ReturnBook returnBook) {
        connection = JDBCUtils.getConnection();
        sql = "update returnbook set bookid = ?,readerid = ?,returntime = ?,adminid = ?,state = ? where id = ?";
        try {
            queryRunner.update(connection,sql,returnBook.getBook().getId(),returnBook.getReader().getId(),returnBook.getReturntime(),returnBook.getBookAdmin().getId(),returnBook.getState(),returnBook.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null,null);
        }
    }

    @Override
    public List<BookPieData> selectBookPieData() {
        List<BookPieData> bookPieDataList = new ArrayList<>();
        BookPieData bookPieData = null;
        try {
            connection = JDBCUtils.getConnection();
            sql = "select count(bookid) from borrow group by bookid order by id";
            List<Integer> valueList = new ArrayList<>();
            List<Integer> value = queryRunner.query(connection,sql,new ColumnListHandler<>());
            for (int i = 0; i < value.size(); i++) {
                valueList.add(((Number)value.get(i)).intValue());
            }
            List<Integer> bookIds = queryRunner.query(connection,"select distinct bookid from borrow order by id",new ColumnListHandler<>());
            for (int i = 0; i < bookIds.size(); i++) {
                bookPieData = new BookPieData();
                Book book = queryRunner.query(connection,"select * from book where id = ?",new BeanHandler<>(Book.class),bookIds.get(i));
                bookPieData.setName(book.getName());
                bookPieData.setValue(valueList.get(i));
                bookPieDataList.add(bookPieData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return bookPieDataList;
    }

    @Override
    public BookBarData selectBookBarData() {

        BookBarData bookBarData = null;
        try {
            connection = JDBCUtils.getConnection();
            sql = "select count(bookid) from borrow group by bookid order by id";
            List<Integer> valueList = queryRunner.query(connection,sql,new ColumnListHandler<>());
            List<String> nameList = new ArrayList<>();
            bookBarData = new BookBarData();
            bookBarData.setCount(valueList);
            List<Integer> bookIds = queryRunner.query(connection,"select distinct bookid from borrow order by id",new ColumnListHandler<>());
            for (int i = 0; i < bookIds.size(); i++) {
                Book book = queryRunner.query(connection,"select * from book where id = ?",new BeanHandler<>(Book.class),bookIds.get(i));
                nameList.add(book.getName());
            }
            bookBarData.setName(nameList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null,null);
        }
        return bookBarData;
    }
}
