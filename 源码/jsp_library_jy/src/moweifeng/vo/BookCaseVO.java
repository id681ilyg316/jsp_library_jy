package moweifeng.vo;

import moweifeng.entities.BookCase;

import java.util.List;

public class BookCaseVO {
    private int count;
    private int code;
    private String msg;
    private List<BookCase> data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

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

    public List<BookCase> getData() {
        return data;
    }

    public void setData(List<BookCase> data) {
        this.data = data;
    }
}
