package com.mall.common.domain.entity;



import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable {


    @Id
    private Long id;

    private String userName;
    private String passWord;

    public User() {
    }

    public User(Long id, String userName, String passWord) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
    }

    /**
     * 获取
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取
     * @return passWord
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * 设置
     * @param passWord
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String toString() {
        return "User{id = " + id + ", userName = " + userName + ", passWord = " + passWord + "}";
    }
}