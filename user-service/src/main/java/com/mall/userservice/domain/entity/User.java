package com.mall.userservice.domain.entity;



import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable {


    @Id
    private Long id;

    private String userName;
    private String passWord;
    private char type;


    public User() {
    }

    public User(String userName, String passWord, char type) {
        this.userName = userName;
        this.passWord = passWord;
        this.type = type;
    }

    public User(String userName, String passWord) {
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

    /**
     * 获取
     * @return type
     */
    public char getType() {
        return type;
    }

    /**
     * 设置
     * @param type
     */
    public void setType(char type) {
        this.type = type;
    }

    public String toString() {
        return "User{id = " + id + ", userName = " + userName + ", passWord = " + passWord + ", type = " + type + "}";
    }
}