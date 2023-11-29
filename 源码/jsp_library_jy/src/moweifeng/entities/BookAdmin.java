package moweifeng.entities;

import java.io.Serializable;

/**
 *图书馆管理员实体类
 */
public class BookAdmin implements Serializable {
    //编号
    private int id;
    //用户名
    private String username;
    //密码
    private String password;

    public BookAdmin() {
    }

    public BookAdmin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
