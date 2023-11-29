package moweifeng.entities;

import java.io.Serializable;

/**
 * 图书归还实体类
 */
public class ReturnBook implements Serializable {
    //编号
    private int id;
    //归还人
    private Reader reader;
    //归还图书
    private Book book;
    //归还时间
    private String returntime;
    //审核人
    private BookAdmin bookAdmin;
    //归还状态  0  归还审核中  1  归还成功  2  归还失败
    private int state;

    public ReturnBook() {
    }

    public ReturnBook(Reader reader, Book book, String returntime, BookAdmin bookAdmin) {
        this.reader = reader;
        this.book = book;
        this.returntime = returntime;
        this.bookAdmin = bookAdmin;
    }

    public ReturnBook(Reader reader, Book book, String returntime, BookAdmin bookAdmin, int state) {
        this.reader = reader;
        this.book = book;
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

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
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
