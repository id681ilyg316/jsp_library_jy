package moweifeng.vo;

import moweifeng.entities.Reader;

import java.util.List;

public class ReaderVO {
    private int count;
    private int code;
    private String msg;
    private List<Reader> data;

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

    public List<Reader> getData() {
        return data;
    }

    public void setData(List<Reader> data) {
        this.data = data;
    }
}
