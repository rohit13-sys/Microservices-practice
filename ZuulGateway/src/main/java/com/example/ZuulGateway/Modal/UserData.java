package com.example.ZuulGateway.Modal;


public class UserData {

    private String userName;
    private String pwd;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "userName='" + userName + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
