package com.mall.common.domain.entity;



import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "user")
public class User implements Serializable {


    @Id
    private Integer id;

    @Column(name = "user_name",nullable = false)
    private String userName;

    @Column(name = "pass_word",nullable = false)
    private String passWord;

    @Column(name = "address")
    private String address;

    @Column(name = "balance", nullable = false, precision = 10, scale = 2)
    private BigDecimal balance;


    public User() {
    }

    public User(Integer id, String userName, String passWord, String address, BigDecimal balance) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.address = address;
        this.balance = balance;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Integer id) {
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
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取
     * @return balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * 设置
     * @param balance
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String toString() {
        return "User{id = " + id + ", userName = " + userName + ", passWord = " + passWord + ", address = " + address + ", balance = " + balance + "}";
    }
}