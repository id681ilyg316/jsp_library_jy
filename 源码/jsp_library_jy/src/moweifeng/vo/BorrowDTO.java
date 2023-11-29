package moweifeng.vo;

import moweifeng.entities.Book;
import moweifeng.entities.BookAdmin;
import moweifeng.entities.Reader;

public class BorrowDTO {
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
    //状态  0---->未审核 1----->审核通过   2------>审核未通过   3------>已归还
    private String state;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
