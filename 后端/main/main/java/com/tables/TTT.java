package com.tables;

import java.util.HashSet;
import java.util.Set;

public class TTT {
    private int ttt_id;
    private String ttt_name;
    private Set<Account> ttt_setaccount=new HashSet<Account>();

    public int getTtt_id() {
        return ttt_id;
    }
    public void setTtt_id(int ttt_id) {
        this.ttt_id = ttt_id;
    }
    public String getTtt_name() {
        return ttt_name;
    }
    public void setTtt_name(String ttt_name) {
        this.ttt_name = ttt_name;
    }
    public Set<Account> getTtt_setaccount() {
        return ttt_setaccount;
    }
    public void setTtt_setaccount(Set<Account> ttt_setaccount) {
        this.ttt_setaccount = ttt_setaccount;
    }
}
