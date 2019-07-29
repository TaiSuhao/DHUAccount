package com.tables;

import java.math.BigDecimal;
import java.util.*;

//凭证信息表
public class Document {
    private int document_id;  //凭证id
   // private BigDecimal document_money;  //交易金额
    private String document_note;    //  备注
    private int document_group;
    private Calendar document_time;
    private BigDecimal document_debitside;   //借方金额
    private BigDecimal document_creditside; //贷方金额
    private User document_user;   //多对一（用户）
    private Account document_account;//多对一（科目）
    //private Set<AccountReceivable> document_setaccountreceivable=new HashSet<AccountReceivable>();//一对多（应收账管理）
    private AccountReceivable document_accountreceivable;

    public BigDecimal getDocument_debitside() {
        return document_debitside;
    }
    public void setDocument_debitside(BigDecimal document_debitside) {
        this.document_debitside = document_debitside;
    }
    public BigDecimal getDocument_creditside() {
        return document_creditside;
    }
    public void setDocument_creditside(BigDecimal document_creditside) {
        this.document_creditside = document_creditside;
    }
    public Calendar getDocument_time() {
        return document_time;
    }
    public void setDocument_time(Calendar document_time) {
        this.document_time = document_time;
    }
    public int getDocument_id() {
        return document_id;
    }
    public void setDocument_id(int document_id) {
        this.document_id = document_id;
    }
    public String getDocument_note() {
        return document_note;
    }
    public void setDocument_note(String document_note) {
        this.document_note = document_note;
    }
    public int getDocument_group() {
        return document_group;
    }
    public void setDocument_group(int document_group) {
        this.document_group = document_group;
    }
    public User getDocument_user() {
        return document_user;
    }
    public void setDocument_user(User document_user) {
        this.document_user = document_user;
    }
    public Account getDocument_account() {
        return document_account;
    }
    public void setDocument_account(Account document_account) {
        this.document_account = document_account;
    }
    public AccountReceivable getDocument_accountreceivable() {
        return document_accountreceivable;
    }
    public void setDocument_accountreceivable(AccountReceivable document_accountreceivable) {
        this.document_accountreceivable = document_accountreceivable;
    }
}
