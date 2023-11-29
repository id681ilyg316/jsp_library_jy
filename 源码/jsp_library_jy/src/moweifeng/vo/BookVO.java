package moweifeng.vo;

import moweifeng.entities.Book;

import java.util.List;

/**
 * @ClassName：BookVO
 * @Description：图书封装实体类，用于匹配layui框架对数据格式的要求
 * @Author：dreambamboo
 * @Date：2019/1/6 22:57
 * @version：1.0
 */
public class BookVO {
    private int code;
    private String msg;
    private int count;
    private List<Book> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Book> getData() {
        return data;
    }

    public void setData(List<Book> data) {
        this.data = data;
    }
}
