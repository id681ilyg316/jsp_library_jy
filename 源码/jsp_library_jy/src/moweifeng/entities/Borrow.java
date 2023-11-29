package moweifeng.entities;

import java.io.Serializable;

/**
 * 图书借阅管理实体类
 *用于存储图书借阅的相关信息
 */
public class Borrow implements Serializable {
    //借阅编号
    private int id;
    //借阅图书
    private Book book;
    //图书借阅人
    private Reader reader;
    //借阅时间
    private String borrowtime;
    //归还时间
    private String returntime;
    //审核人
    private BookAdmin bookAdmin;
    //状态  0---->借阅审核中 1----->借阅审核通过   2------>借阅审核未通过   3------>已归还   4------>归还审核中   5----->归还审核失败
    private int state;
    public Borrow() {
    }

    public Borrow(Book book, Reader reader, String borrowtime, String returntime, BookAdmin bookAdmin) {
        this.book = book;
        this.reader = reader;
        this.borrowtime = borrowtime;
        this.returntime = returntime;
        this.bookAdmin = bookAdmin;
    }

    public Borrow(Book book, Reader reader, String borrowtime, String returntime, BookAdmin bookAdmin, int state) {
        this.book = book;
        this.reader = reader;
        this.borrowtime = borrowtime;
        this.returntime = returntime;
        this.bookAdmin = bookAdmin;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public String getBorrowtime() {
        return borrowtime;
    }

    public void setBorrowtime(String borrowtime) {
        this.borrowtime = borrowtime;
    }

    public String getReturntime() {
        return returntime;
    }

    public void setReturntime(String returntime) {
        this.returntime = returntime;
    }

    public BookAdmin getBookAdmin() {
        return bookAdmin;
    }

    public void setBookAdmin(BookAdmin bookAdmin) {
        this.bookAdmin = bookAdmin;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
