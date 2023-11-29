package moweifeng.vo;

import moweifeng.entities.Borrow;

import java.util.List;

public class BorrowVO {
    private int code;
    private int count;
    private String msg;
    private List<Borrow> data;

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

    public List<Borrow> getData() {
        return data;
    }

    public void setData(List<Borrow> data) {
        this.data = data;
    }
}
