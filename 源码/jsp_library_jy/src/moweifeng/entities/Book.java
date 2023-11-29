package moweifeng.entities;

import java.io.Serializable;

/**
 *图书类
 */
public class Book implements Serializable {
    //图书编号
    private int id;
    //图书书名
    private String name;
    //图书页码
    private int pages;
    //图书价格
    private float price;
    //出版社
    private String publish;
    //图书作者
    private String author;
    //图书类目
    private BookCase bookCase;
    //图书状态：0---图书被借阅 1---图书未被借阅
    private int abled;

    public Book() {
    }

    public Book(String name, int pages, float price, String publish, String author,BookCase bookCase,int abled) {
        this.name = name;
        this.pages = pages;
        this.price = price;
        this.publish = publish;
        this.author = author;
        this.bookCase = bookCase;
        this.abled = abled;
    }

    public BookCase getBookCase() {
        return bookCase;
    }

    public void setBookCase(BookCase bookCase) {
        this.bookCase = bookCase;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAbled() {
        return abled;
    }

    public void setAbled(int abled) {
        this.abled = abled;
    }
}
