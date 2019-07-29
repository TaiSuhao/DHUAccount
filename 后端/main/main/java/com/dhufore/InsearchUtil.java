package com.dhufore;

import com.tables.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import javax.print.Doc;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

public class InsearchUtil {
    private static SessionFactory sessionFactory;
    private static Session session;
    private static Transaction transaction;
    static {
        org.hibernate.cfg.Configuration cfg=new org.hibernate.cfg.Configuration();
        cfg.configure();
        sessionFactory=cfg.buildSessionFactory();
        System.out.println("loading sessionFactory...");
    }
    public static Session getSessionobject(){
        return sessionFactory.openSession();
    }
    public static void temp(){
        System.out.println("this is a static function");
    }
    //保存信息
    void save(Object object){
        try {
            session = InsearchUtil.getSessionobject();
            transaction = session.beginTransaction();
            session.save(object);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
        }
    }
    //通过用户的属性和属性值查找用户，返回list
    List<User> searchUser(String type,Object value){
        List<User> list=new ArrayList<User>();
        try {
            session=InsearchUtil.getSessionobject();
            transaction=session.beginTransaction();
            String hql="from com.tables.User as u where u."+type+"=? order by u.user_id asc";
            Query query=session.createQuery(hql);
            query.setParameter(0,value);
            list=query.list();
            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
            return list;
        }
    }

    //通过用户名查找客户信息表,返回list
    List<CustomerInfo> searchCustomerInfo(String type,Object value){
        /*session=Info.getSessionobject();
        transaction=session.beginTransaction();
        String hql="from com.tables.CustomerInfo as u left outer join u.customerinfo_user where u.customerinfo_user.user_username=?";
        Query query=session.createQuery(hql);
        query.setParameter(0,value);
        List<Object[]> list=query.list();
        transaction.commit();
        return list;*/
        List<CustomerInfo> list=new ArrayList<CustomerInfo>();
        try {
            session = InsearchUtil.getSessionobject();
            transaction = session.beginTransaction();
            String hql = "from com.tables.CustomerInfo as u left join fetch u.customerinfo_user where u." + type + "=? order by u.customerinfo_id asc";
            System.out.println("searching customerinfo");
            Query query = session.createQuery(hql);
            query.setParameter(0, value);
            list = query.list();
            // System.out.println(list.get(0).getCustomerinfo_user().getUser_username());
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
            return list;
        }
    }

    //查找科目表，返回list
    List<Account> searchAccount(String type,Object value){
        List<Account> list=new ArrayList<Account>();
        try{
            session=InsearchUtil.getSessionobject();
            transaction = session.beginTransaction();
            String hql="from com.tables.Account as u left join fetch u.account_user where u."+type+"=? order by u.account_id asc";
            System.out.println("searching account");
            Query query=session.createQuery(hql);
            query.setParameter(0,value);
            list=query.list();
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
            return list;
        }
    }

    //查找凭证，返回list
    List<Document> searchDocument(String type,Object value){
        List<Document> list=new ArrayList<Document>();
        try{
            session=InsearchUtil.getSessionobject();
            transaction=session.beginTransaction();
            String hql="from com.tables.Document as u left join fetch u.document_account left join fetch u.document_user   left join  fetch  u.document_accountreceivable.accountreceivable_customerinfo where u."+type+"=? order by u.document_id asc";
            System.out.println("searching document");
            Query query=session.createQuery(hql);
            query.setParameter(0,value);
            list=query.list();
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
            return list;
        }
    }

    //查找应收账款，返回list
    List<AccountReceivable> searchAccountReceivable(String type,Object value){
        List<AccountReceivable> list=new ArrayList<AccountReceivable>();
        try{
            session=InsearchUtil.getSessionobject();
            transaction=session.beginTransaction();
            String hql="from com.tables.AccountReceivable as u  left join fetch u.accountreceivable_document left join fetch u.accountreceivable_document.document_account left join fetch u.accountreceivable_document.document_user left join fetch u.accountreceivable_customerinfo where u."+type+"=? order by u.accountreceivable_id asc";
            System.out.println("searching accountreceivable");
            Query query=session.createQuery(hql);
            query.setParameter(0,value);
            list=query.list();
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
            return list;
        }
    }

    void test(){
        try{
            session=InsearchUtil.getSessionobject();
            transaction=session.beginTransaction();
            String hql="update com.tables.Document t set t.document_time=? where t.document_id<=15";

            Calendar calendar=Calendar.getInstance();
            calendar.set(Calendar.YEAR,2002);
            calendar.set(Calendar.MONTH,2);
            calendar.set(Calendar.DAY_OF_MONTH,17);
            calendar.set(Calendar.HOUR_OF_DAY,23);
            calendar.set(Calendar.MINUTE,59);
            calendar.set(Calendar.SECOND,59);


            Query query=session.createQuery(hql);

            query.setParameter(0,calendar);
            query.executeUpdate();
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
        }
    }

    //改变科目余额（传入数值进行增加或者减少）
    Account cgAccount(Account account,BigDecimal account_debitside_occur,BigDecimal account_creditside_occur){
        try {
            session = InsearchUtil.getSessionobject();
            transaction = session.beginTransaction();
            String hql = "update com.tables.Account t set t.account_debitside_occur=(t.account_debitside_occur+?) where t.account_id=" + account.getAccount_id();
            Query query = session.createQuery(hql);
            query.setParameter(0, account_debitside_occur);
            query.executeUpdate();
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
        }
        try {
            session = InsearchUtil.getSessionobject();
            transaction = session.beginTransaction();
            String hql = "update com.tables.Account t set t.account_creditside_occur=(t.account_creditside_occur+?) where t.account_id=" + account.getAccount_id();
            Query query = session.createQuery(hql);
            query.setParameter(0, account_creditside_occur);
            query.executeUpdate();
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
        }

        if(account.getAccount_category().equals("assets")) {
            try {
                session = InsearchUtil.getSessionobject();
                transaction = session.beginTransaction();
                String hql = "update com.tables.Account t set t.account_debitside_final=(t.account_debitside_final+?-?) where t.account_id=" + account.getAccount_id();
                Query query = session.createQuery(hql);
                query.setParameter(0, account_debitside_occur);
                query.setParameter(1, account_creditside_occur);
                query.executeUpdate();
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                transaction.rollback();
            } finally {
                session.close();
            }
        }else
           {
               try {
                   session = InsearchUtil.getSessionobject();
                   transaction = session.beginTransaction();
                   String hql = "update com.tables.Account t set t.account_creditside_final=(t.account_creditside_final-?+?) where t.account_id=" + account.getAccount_id();
                   Query query = session.createQuery(hql);
                   query.setParameter(0, account_debitside_occur);
                   query.setParameter(1, account_creditside_occur);
                   query.executeUpdate();
                   transaction.commit();
               }catch (Exception e){
                   e.printStackTrace();
                   transaction.rollback();
            }finally {
                   session.close();
               }

        }
        return new InsearchUtil().searchAccount("account_id",account.getAccount_id()).get(0);
    }

    //改变客户的应收账户金额（传入数值进行增加或者减少）
    CustomerInfo cgCustomerInfo(CustomerInfo customerInfo,BigDecimal document_debitside,BigDecimal document_creditside){
        try{
            session=InsearchUtil.getSessionobject();
            transaction=session.beginTransaction();
            String hql="update com.tables.CustomerInfo t set t.customerinfo_money=(t.customerinfo_money+?-?) where t.customerinfo_id="+customerInfo.getCustomerinfo_id();
            Query query=session.createQuery(hql);
            query.setParameter(0,document_debitside);
            query.setParameter(1,document_creditside);
            query.executeUpdate();
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            return new InsearchUtil().searchCustomerInfo("customerinfo_id",customerInfo.getCustomerinfo_id()).get(0);
        }
    }

    //改变用户对应的凭证组号
    User cgUser(User user,int value){
        try{
            session=InsearchUtil.getSessionobject();
            transaction=session.beginTransaction();
            String hql="update com.tables.User t set t.user_number=? where t.user_id="+user.getUser_id();
            Query query=session.createQuery(hql);
            query.setParameter(0,value);
            query.executeUpdate();
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally{
            return new InsearchUtil().searchUser("user_id",user.getUser_id()).get(0);
        }
    }

    void inituser(User user){
        Account []accounts=new Account[5];
        for(int i=0;i<accounts.length;i++) accounts[i]=new Account();
        BigDecimal zero=new BigDecimal(0);
        accounts[0].setAccount_name("1001库存现金");
        accounts[1].setAccount_name("1002银行存款");
        accounts[2].setAccount_name("1003应收账款");
        accounts[3].setAccount_name("1004原材料");
        accounts[4].setAccount_name("1005库存商品");
        for(int i=0;i<accounts.length;i++){
            accounts[i].setAccount_debitside_base(zero);
            accounts[i].setAccount_creditside_base(zero);
            accounts[i].setAccount_debitside_occur(zero);
            accounts[i].setAccount_creditside_occur(zero);
            accounts[i].setAccount_debitside_final(zero);
            accounts[i].setAccount_creditside_final(zero);
            accounts[i].setAccount_user(user);
            accounts[i].setAccount_category("assets");
            new InsearchUtil().save(accounts[i]);
        }

        accounts=new Account[5];
        for(int i=0;i<accounts.length;i++) accounts[i]=new Account();
        accounts[0].setAccount_name("2001短期借款");
        accounts[1].setAccount_name("2002交易性金融负债");
        accounts[2].setAccount_name("2003应付票据");
        accounts[3].setAccount_name("2004应付账款");
        accounts[4].setAccount_name("2005预付账款");
        for(int i=0;i<accounts.length;i++){
            accounts[i].setAccount_debitside_base(zero);
            accounts[i].setAccount_creditside_base(zero);
            accounts[i].setAccount_debitside_occur(zero);
            accounts[i].setAccount_creditside_occur(zero);
            accounts[i].setAccount_debitside_final(zero);
            accounts[i].setAccount_creditside_final(zero);
            accounts[i].setAccount_user(user);
            accounts[i].setAccount_category("liabilities");
            new InsearchUtil().save(accounts[i]);
        }
    }

    //增加科目金额
    void adAccountMoney(Account account,BigDecimal account_debitside_base,BigDecimal account_creditside_base){
        try {
            session = InsearchUtil.getSessionobject();
            transaction = session.beginTransaction();
            String hql = "update com.tables.Account t set t.account_debitside_base=(t.account_debitside_base+?) where t.account_id=" + account.getAccount_id();
            Query query = session.createQuery(hql);
            query.setParameter(0, account_debitside_base);
            query.executeUpdate();
            transaction.commit();
        }catch(Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
        }
        try {
            session = InsearchUtil.getSessionobject();
            transaction = session.beginTransaction();
            String hql = "update com.tables.Account t set t.account_creditside_base=(t.account_creditside_base+?) where t.account_id=" + account.getAccount_id();
            Query query = session.createQuery(hql);
            query.setParameter(0, account_creditside_base);
            query.executeUpdate();
            transaction.commit();
        }catch(Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
        }



        if(account.getAccount_category().equals("assets")){
            try {
                session = InsearchUtil.getSessionobject();
                transaction = session.beginTransaction();
                String hql = "update com.tables.Account t set t.account_debitside_final=(t.account_debitside_final+?-?) where t.account_id=" + account.getAccount_id();
                Query query = session.createQuery(hql);
                query.setParameter(0, account_debitside_base);
                query.setParameter(1, account_creditside_base);
                query.executeUpdate();
                transaction.commit();
            }catch (Exception e) {
                e.printStackTrace();
                transaction.rollback();
            }finally {
                session.close();
            }
            }else{
            try {
                session = InsearchUtil.getSessionobject();
                transaction = session.beginTransaction();
                String hql = "update com.tables.Account t set t.account_creditside_final=(t.account_creditside_final-?+?) where t.account_id=" + account.getAccount_id();
                Query query = session.createQuery(hql);
                query.setParameter(0, account_debitside_base);
                query.setParameter(1, account_creditside_base);
                query.executeUpdate();
                transaction.commit();
            }catch ( Exception e) {
                e.printStackTrace();
                transaction.rollback();
            }finally {
                session.close();
            }
        }
    }
    void searchUserMore(String type,Object value){
        List<CustomerInfo> list=new ArrayList<CustomerInfo>();
        try {
            session = InsearchUtil.getSessionobject();

            transaction = session.beginTransaction();
            String hql = "from com.tables.CustomerInfo as u left join fetch u.customerinfo_user where u." + type + "=? ";
            System.out.println("noo");
            Query query = session.createQuery(hql);
            query.setParameter(0, value);
            list = query.list();
            System.out.println(list.size());
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
                // Set<CustomerInfo> temp=list.get(i).getUser_setcustomerinfo();
                System.out.println(list.get(i).getCustomerinfo_name());
            }
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally {

        }
    }
}
