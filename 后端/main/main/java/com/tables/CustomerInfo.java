package com.tables;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

//客户信息表
public class CustomerInfo {
    private int customerinfo_id; //客户信息表id
    private BigDecimal customerinfo_money;//应收账款余额
    private String customerinfo_name;// 客户名称
    private String customerinfo_company; //公司名称
    private String customerinfo_phone; //客户手机
    private Set<AccountReceivable> customerinfo_setaccountreceivable=new HashSet<AccountReceivable>();//一对多（应收账管理）
    private User customerinfo_user; //多对一（用户）

    public String getCustomerinfo_phone() {
        return customerinfo_phone;
    }
    public void setCustomerinfo_phone(String customerinfo_phone) {
        this.customerinfo_phone = customerinfo_phone;
    }
    public int getCustomerinfo_id() {
        return customerinfo_id;
    }
    public void setCustomerinfo_id(int customerinfo_id) {
        this.customerinfo_id = customerinfo_id;
    }
    public BigDecimal getCustomerinfo_money() {
        return customerinfo_money;
    }
    public void setCustomerinfo_money(BigDecimal customerinfo_money) {
        this.customerinfo_money = customerinfo_money;
    }
    public String getCustomerinfo_name() {
        return customerinfo_name;
    }
    public void setCustomerinfo_name(String customerinfo_name) {
        this.customerinfo_name = customerinfo_name;
    }
    public String getCustomerinfo_company() {
        return customerinfo_company;
    }
    public void setCustomerinfo_company(String customerinfo_company) {
        this.customerinfo_company = customerinfo_company;
    }
    public Set<AccountReceivable> getCustomerinfo_setaccountreceivable() {
        return customerinfo_setaccountreceivable;
    }
    public void setCustomerinfo_setaccountreceivable(Set<AccountReceivable> customerinfo_setaccountreceivable) {
        this.customerinfo_setaccountreceivable = customerinfo_setaccountreceivable;
    }
    public User getCustomerinfo_user() {
        return customerinfo_user;
    }
    public void setCustomerinfo_user(User customerinfo_user) {
        this.customerinfo_user = customerinfo_user;
    }
}
