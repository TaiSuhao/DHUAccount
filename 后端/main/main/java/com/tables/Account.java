package com.tables;

import javax.print.Doc;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

//科目表
public class Account {
    private int account_id;
    private String account_name;
    private Set<Document> account_setdocument=new HashSet<Document>();  //一对多（凭证）

    private User account_user;
    private BigDecimal account_debitside_base;  //借方月初余额
    private BigDecimal account_creditside_base;     // 贷方月初余额
    private BigDecimal account_debitside_occur;  //借方本期发生额
    private BigDecimal account_creditside_occur; //贷方本期发生额
    private BigDecimal account_debitside_final; //借方期末余额
    private BigDecimal account_creditside_final;// 贷方期末余额
    private String account_category; // assets 表示资产，liabilities 表示负债

    public BigDecimal getAccount_debitside_base() {
        return account_debitside_base;
    }
    public void setAccount_debitside_base(BigDecimal account_debitside_base) {
        this.account_debitside_base = account_debitside_base;
    }
    public BigDecimal getAccount_creditside_base() {
        return account_creditside_base;
    }
    public void setAccount_creditside_base(BigDecimal account_creditside_base) {
        this.account_creditside_base = account_creditside_base;
    }
    public BigDecimal getAccount_debitside_occur() {
        return account_debitside_occur;
    }
    public void setAccount_debitside_occur(BigDecimal account_debitside_occur) {
        this.account_debitside_occur = account_debitside_occur;
    }
    public BigDecimal getAccount_creditside_occur() {
        return account_creditside_occur;
    }
    public void setAccount_creditside_occur(BigDecimal account_creditside_occur) {
        this.account_creditside_occur = account_creditside_occur;
    }
    public BigDecimal getAccount_debitside_final() {
        return account_debitside_final;
    }
    public void setAccount_debitside_final(BigDecimal account_debitside_final) {
        this.account_debitside_final = account_debitside_final;
    }
    public BigDecimal getAccount_creditside_final() {
        return account_creditside_final;
    }
    public void setAccount_creditside_final(BigDecimal account_creditside_final) {
        this.account_creditside_final = account_creditside_final;
    }
    public String getAccount_category() {
        return account_category;
    }
    public void setAccount_category(String account_category) {
        this.account_category = account_category;
    }
    public int getAccount_id() {
        return account_id;
    }
    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }
    public String getAccount_name() {
        return account_name;
    }
    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }
    public Set<Document> getAccount_setdocument() {
        return account_setdocument;
    }
    public void setAccount_setdocument(Set<Document> account_setdocument) {
        this.account_setdocument = account_setdocument;
    }
    public User getAccount_user() {
        return account_user;
    }
    public void setAccount_user(User account_user) {
        this.account_user = account_user;
    }

}
