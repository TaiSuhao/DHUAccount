package com.tables;


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
//用户表
public class User {
    private int user_id;
    private String user_username;
    private String user_password;
    private String user_phonenumber;
    private String user_gender;
    private String user_email;
    private String user_note;
    private int user_number;
    private Set<CustomerInfo> user_setcustomerinfo=new HashSet<CustomerInfo>();  //一对多（客户信息）
    private Set<Document> user_setdocument=new HashSet<Document>(); //一对多（凭证）
    private Set<Account> user_setaccount=new HashSet<Account>(); //一对多（科目）

    public int getUser_number() {
        return user_number;
    }
    public void setUser_number(int user_number) {
        this.user_number = user_number;
    }
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public String getUser_username() {
        return user_username;
    }
    public void setUser_username(String user_username) {
        this.user_username = user_username;
    }
    public String getUser_password() {
        return user_password;
    }
    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
    public String getUser_phonenumber() {
        return user_phonenumber;
    }
    public void setUser_phonenumber(String user_phonenumber) {
        this.user_phonenumber = user_phonenumber;
    }
    public String getUser_gender() {
        return user_gender;
    }
    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }
    public String getUser_email() {
        return user_email;
    }
    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
    public String getUser_note() {
        return user_note;
    }
    public void setUser_note(String user_note) {
        this.user_note = user_note;
    }
    public Set<CustomerInfo> getUser_setcustomerinfo() {
        return user_setcustomerinfo;
    }
    public void setUser_setcustomerinfo(Set<CustomerInfo> user_setcustomerinfo) {
        this.user_setcustomerinfo = user_setcustomerinfo;
    }
    public Set<Document> getUser_setdocument() {
        return user_setdocument;
    }
    public void setUser_setdocument(Set<Document> user_setdocument) {
        this.user_setdocument = user_setdocument;
    }
    public Set<Account> getUser_setaccount() {
        return user_setaccount;
    }
    public void setUser_setaccount(Set<Account> user_setaccount) {
        this.user_setaccount = user_setaccount;
    }
}
