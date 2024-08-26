package com.mall.userservice.domain.dto;



public class RegisterRequest {
    private String username;
    private String password;
    private char type;


    public RegisterRequest() {
    }

    public RegisterRequest(String username, String password, char type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    /**
     * 获取
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
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
        return "RegisterRequest{username = " + username + ", password = " + password + ", type = " + type + "}";
    }
}
