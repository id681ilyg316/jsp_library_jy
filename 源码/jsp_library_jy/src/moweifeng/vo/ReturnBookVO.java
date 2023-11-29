package moweifeng.vo;

import moweifeng.entities.ReturnBook;

import java.util.List;

public class ReturnBookVO {
    private int code;
    private int count;
    private String msg;
    private List<ReturnBook> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ReturnBook> getData() {
        return data;
    }

    public void setData(List<ReturnBook> data) {
        this.data = data;
    }
}
