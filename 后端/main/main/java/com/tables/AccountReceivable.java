package com.tables;

import java.math.BigDecimal;

//应收账管理
public class AccountReceivable {
    private int accountreceivable_id;
    private BigDecimal accountreceivable_debitside;//借方
    private BigDecimal accountreceivable_creditside;//贷方
    private Document accountreceivable_document; //多对一（凭证）
    private CustomerInfo accountreceivable_customerinfo; //多对一（客户信息）

    public BigDecimal getAccountreceivable_debitside() {
        return accountreceivable_debitside;
    }
    public void setAccountreceivable_debitside(BigDecimal accountreceivable_debitside) {
        this.accountreceivable_debitside = accountreceivable_debitside;
    }
    public BigDecimal getAccountreceivable_creditside() {
        return accountreceivable_creditside;
    }
    public void setAccountreceivable_creditside(BigDecimal accountreceivable_creditside) {
        this.accountreceivable_creditside = accountreceivable_creditside;
    }
    public int getAccountreceivable_id() {
        return accountreceivable_id;
    }
    public void setAccountreceivable_id(int accountreceivable_id) {
        this.accountreceivable_id = accountreceivable_id;
    }
    public Document getAccountreceivable_document() {
        return accountreceivable_document;
    }
    public void setAccountreceivable_document(Document accountreceivable_document) {
        this.accountreceivable_document = accountreceivable_document;
    }
    public CustomerInfo getAccountreceivable_customerinfo() {
        return accountreceivable_customerinfo;
    }
    public void setAccountreceivable_customerinfo(CustomerInfo accountreceivable_customerinfo) {
        this.accountreceivable_customerinfo = accountreceivable_customerinfo;
    }
}
