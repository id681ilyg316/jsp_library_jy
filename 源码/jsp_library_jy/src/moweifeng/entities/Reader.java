package moweifeng.entities;

import java.io.Serializable;

/**
 *读者实体类，用于存储读者的相关信息
 */
public class Reader implements Serializable {
    //读者编号
    private int id;
    //姓名
    private String name;
    //登录名
    private String username;
    //借书卡号
    private String cardid;
    //联系方式
    private String tel;
    //密码
    private String password;
    //性别
    private String gender;


    public Reader() {
    }

    public Reader(String name, String username, String cardid, String tel, String password, String gender) {
        this.name = name;
        this.username = username;
        this.cardid = cardid;
        this.tel = tel;
        this.password = password;
        this.gender = gender;
    }

    public Reader(int id, String name, String username, String cardid, String tel, String password, String gender) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.cardid = cardid;
        this.tel = tel;
        this.password = password;
        this.gender = gender;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
