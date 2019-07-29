package com.dhufore;

import com.google.gson.*;

import com.opensymphony.xwork2.ActionSupport;
import com.tables.*;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;


import javax.print.Doc;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;

public class Process extends ActionSupport implements ServletRequestAware, ServletResponseAware {
    private static final long serialVersionUID = 1L;
    HttpServletRequest request;
    HttpServletResponse response;
  //  private Map<String, Object> dataMap=new HashMap<String,Object>();
    private List<String> myjson=new ArrayList<String>();
    public void setServletRequest(HttpServletRequest request)
    {
        this.request = request;
    }
    public void setServletResponse(HttpServletResponse response)
    {
        this.response = response;
    }
    //登录


    public List<String> getMyjson() {
        return myjson;
    }

    public String login() throws IOException {
        InsearchUtil insearchUtil=new InsearchUtil();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //跨域设置返回头
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token");//这里“Access-Token”是我要传到后台的内容key
        response.setHeader("Access-Control-Expose-Headers", "*");
        //  System.out.println("the ans is "+ans.toString());
        //   response.getWriter().write(ans.toString());
        //跳过跨域请求post中的第一次options
        System.out.println("accept request in login！");
        if (request.getMethod().equals("OPTIONS")) {
            System.out.println("it is OPTIONS");
            return "ok";
        }

        String user_username=request.getParameter("user_username");
        String user_password=request.getParameter("user_password");
        //  String data=request.getParameter("data");
        System.out.println(user_username);
        System.out.println(user_password);
        // System.out.println(data);
        List<User> list=insearchUtil.searchUser("user_username",user_username);
        System.out.println("hello");
        JsonObject jsonObject=new JsonObject();
        if(list.isEmpty()){
            jsonObject.addProperty("status","NoUser");
        }else if(list.get(0).getUser_password().equals(user_password)){
            jsonObject.addProperty("status","Success");
            jsonObject.addProperty("user_number",list.get(0).getUser_number());
            jsonObject.addProperty("user_phonenumber",list.get(0).getUser_phonenumber());
            jsonObject.addProperty("user_gender",list.get(0).getUser_gender());
            jsonObject.addProperty("user_email",list.get(0).getUser_email());
            jsonObject.addProperty("user_note",list.get(0).getUser_note());
        }else {
            jsonObject.addProperty("status","Fail");
        }
      //  response.setContentLength(jsonObject.toString().length());
     //   response.getWriter().write(jsonObject.toString());
        myjson.clear();
        myjson.add(jsonObject.toString());
        System.out.println(jsonObject.toString());
        return "ok";
    }
    //注册
    public String register()throws IOException{
        InsearchUtil insearchUtil=new InsearchUtil();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //跨域设置返回头
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token");//这里“Access-Token”是我要传到后台的内容key
        response.setHeader("Access-Control-Expose-Headers", "*");
        System.out.println("accept request in register");
        if (request.getMethod().equals("OPTIONS")) {
            System.out.println("it is OPTIONS");
            return "ok";
        }
        String user_username=request.getParameter("user_username");
        String user_password=request.getParameter("user_password");
        String user_phonenumber=request.getParameter("user_phonenumber");
        String user_gender=request.getParameter("user_gender");
        String user_email=request.getParameter("user_email");
        String user_note=request.getParameter("user_note");
        List<User> list=insearchUtil.searchUser("user_username",user_username);
        JsonObject jsonObject=new JsonObject();
        if(list.isEmpty()){
            User user=new User();
            user.setUser_username(user_username);
            user.setUser_password(user_password);
            user.setUser_phonenumber(user_phonenumber);
            user.setUser_gender(user_gender);
            user.setUser_email(user_email);
            user.setUser_note(user_note);
            user.setUser_number(1);
            new InsearchUtil().save(user);
            new InsearchUtil().inituser(user);
            jsonObject.addProperty("status","Success");
        }else{
            jsonObject.addProperty("status","UserExist");
        }
        response.setContentLength(jsonObject.toString().length());
        response.getWriter().write(jsonObject.toString());
        return "ok";
    }
    //添加客户信息  传入用户名，客户名，公司名，
    // 返回 status： 1.Success （成功） 2.CustomerInfoExist （客户信息已存在） 3.NoUser （没有此用户）
    public String adCustomerInfo() throws IOException{
        InsearchUtil insearchUtil=new InsearchUtil();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //跨域设置返回头
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token");//这里“Access-Token”是我要传到后台的内容key
        response.setHeader("Access-Control-Expose-Headers", "*");
        System.out.println("accept request in addCustomerInfo");
        if (request.getMethod().equals("OPTIONS")) {
            System.out.println("it is OPTIONS");
            return "ok";
        }
        String user_username=request.getParameter("user_username");
        String customerinfo_name=request.getParameter("customerinfo_name");
        String customerinfo_company=request.getParameter("customerinfo_company");
        String customerinfo_phone=request.getParameter("customerinfo_phone");
        List<User> users=insearchUtil.searchUser("user_username",user_username);
        if(users.isEmpty()){
            JsonObject jsonObject=new JsonObject();
            jsonObject.addProperty("status","NoUser");
            response.setContentLength(jsonObject.toString().length());
            response.getWriter().write(jsonObject.toString());
            return "ok";
        }
        List<CustomerInfo> list=insearchUtil.searchCustomerInfo("customerinfo_user.user_username",user_username);
        JsonObject jsonObject=new JsonObject();
        Boolean flag=false;

        for(int i=0;i<list.size();i++){
            if(list.get(i).getCustomerinfo_name().equals(customerinfo_name)){
                flag=true;
                break;
            }
        }
        if(!flag){
            CustomerInfo customerInfo=new CustomerInfo();
            customerInfo.setCustomerinfo_name(customerinfo_name);
            customerInfo.setCustomerinfo_company(customerinfo_company);
            customerInfo.setCustomerinfo_phone(customerinfo_phone);
            BigDecimal money=new BigDecimal(0);
            customerInfo.setCustomerinfo_money(money);

            User user=users.get(0);
            customerInfo.setCustomerinfo_user(user);
            new InsearchUtil().save(customerInfo);
            jsonObject.addProperty("status","Success");
        }else{
            jsonObject.addProperty("status","CustomerInfoExist");
        }
        response.setContentLength(jsonObject.toString().length());
        response.getWriter().write(jsonObject.toString());
        return "ok";
    }
    /*得到客户信息，传入用户名，返回一个list<String> 的json
    String为
    customerinfo_money
    customerinfo_name
    customerinfo_company
    的json格式

    或者返回status:NoUser 的json
    */
    public String geCustomerInfo() throws IOException{
        InsearchUtil insearchUtil=new InsearchUtil();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //跨域设置返回头
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token");//这里“Access-Token”是我要传到后台的内容key
        response.setHeader("Access-Control-Expose-Headers", "*");
        System.out.println("accept request in geCustomerInfo");
        if (request.getMethod().equals("OPTIONS")) {
            System.out.println("it is OPTIONS");
            return "ok";
        }

        String user_username=request.getParameter("user_username");

        List<User> users=insearchUtil.searchUser("user_username",user_username);
        if(users.isEmpty()){
            JsonObject jsonObject=new JsonObject();
            jsonObject.addProperty("status","NoUser");
            response.setContentLength(jsonObject.toString().length());
            response.getWriter().write(jsonObject.toString());
            return "ok";
        }

        List<CustomerInfo> list=insearchUtil.searchCustomerInfo("customerinfo_user.user_username",user_username);
        JsonObject jsonObject=new JsonObject();
      //  List<String> list1=new ArrayList<String>();
        myjson.clear();
        for(int i=0;i<list.size();i++){
            GsonBuilder gsonBuilder=new GsonBuilder();
            gsonBuilder.setExclusionStrategies(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                    Boolean flag=false;
                    if(fieldAttributes.getName().equals("customerinfo_setaccountreceivable"))
                        flag=true;
                    else if(fieldAttributes.getName().equals("customerinfo_user"))
                        flag=true;
                    else if(fieldAttributes.getName().equals("customerinfo_id"))
                        flag=true;
                    return flag;
                }
                @Override
                public boolean shouldSkipClass(Class<?> aClass) {
                    return false;
                }
            });
            Gson gson=gsonBuilder.create();
            String result=gson.toJson(list.get(i));
            System.out.println(result);
            myjson.add(result);
        }
     //   res=new Gson().toJson(list1);
      //  System.out.println(res);
      //  System.out.println(res.length());
      //  System.out.println(res.getBytes().length);
   //     response.setContentLength(res.length()+10);
       // response.setBufferSize();
     //   response.getWriter().print(res);
        //PrintWriter out=response.getWriter();
       // out.print(res);
        return "ok";
    }

    //添加科目信息 传入用户名，科目名称，科目余额
    // 返回 status： 1.Success （成功） 2.AccountExist （科目已存在） 3.NoUser （没有此用户）
   /* public String adAccount() throws  IOException{
        InsearchUtil insearchUtil=new InsearchUtil();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //跨域设置返回头
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token");//这里“Access-Token”是我要传到后台的内容key
        response.setHeader("Access-Control-Expose-Headers", "*");
        System.out.println("accept request in adAccount");
        if (request.getMethod().equals("OPTIONS")) {
            System.out.println("it is OPTIONS");
            return "ok";
        }
        //没有此用户就返回 status:NoUser
        String user_username=request.getParameter("user_username");
        String account_name=request.getParameter("account_name");
        BigDecimal account_debitside_base=new BigDecimal(request.getParameter("account_debitside_base"));
        BigDecimal account_creditside_base=new BigDecimal(request.getParameter("account_creditside_base"));

        List<User> users=insearchUtil.searchUser("user_username",user_username);
        if(users.isEmpty()){
            JsonObject jsonObject=new JsonObject();
            jsonObject.addProperty("status","NoUser");
            response.setContentLength(jsonObject.toString().length());
            response.getWriter().write(jsonObject.toString());
            return "ok";
        }

        List<Account> list=insearchUtil.searchAccount("account_user.user_username",user_username);
        JsonObject jsonObject=new JsonObject();
        Boolean flag=false;

        for(int i=0;i<list.size();i++){
            if(list.get(i).getAccount_name().equals(account_name)){
                flag=true;
                break;
            }
        }
        if(!flag){
            Account account=new Account();
            account.setAccount_name(account_name);
         //   account.setAccount_money(account_money);
            account.setAccount_debitside_base(account_debitside_base);
            account.setAccount_creditside_base(account_creditside_base);
            User user=users.get(0);
            account.setAccount_user(user);
            new InsearchUtil().save(account);
            jsonObject.addProperty("status","Success");
        }else{
            jsonObject.addProperty("status","AccountExist");
        }
        response.setContentLength(jsonObject.toString().length());
        response.getWriter().write(jsonObject.toString());
        return "ok";
    }*/


    /*得到科目信息，传入用户名，返回一个list<String> 的json
   String为
   account_name
   account_money
   account_debitside_base
   account_creditside_base
   account_debitside_occur
   account_creditside_occur
   account_debitside_final
   account_creditside_final
   的json格式
   或者返回status:NoUser 的json
   */
    public String geAccount() throws IOException{
        InsearchUtil insearchUtil=new InsearchUtil();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //跨域设置返回头
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token");//这里“Access-Token”是我要传到后台的内容key
        response.setHeader("Access-Control-Expose-Headers", "*");
        System.out.println("accept request in geCustomerInfo");
        if (request.getMethod().equals("OPTIONS")) {
            System.out.println("it is OPTIONS");
            return "ok";
        }

        String user_username=request.getParameter("user_username");
        List<User> users=insearchUtil.searchUser("user_username",user_username);
        if(users.isEmpty()){
            JsonObject jsonObject=new JsonObject();
            jsonObject.addProperty("status","NoUser");
            response.setContentLength(jsonObject.toString().length());
            response.getWriter().write(jsonObject.toString());
            return "ok";
        }

        List<Account> list=insearchUtil.searchAccount("account_user.user_username",user_username);
        JsonObject jsonObject=new JsonObject();
      //  List<String> list1=new ArrayList<String>();
        myjson.clear();
        for(int i=0;i<list.size();i++){
            GsonBuilder gsonBuilder=new GsonBuilder();
            gsonBuilder.setExclusionStrategies(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                    Boolean flag=false;
                    if(fieldAttributes.getName().equals("account_setdocument"))
                        flag=true;
                    else if(fieldAttributes.getName().equals("account_user"))
                        flag=true;
                    else if(fieldAttributes.getName().equals("account_id"))
                        flag=true;
                    return flag;
                }
                @Override
                public boolean shouldSkipClass(Class<?> aClass) {
                    return false;
                }
            });
            Gson gson=gsonBuilder.create();
            String result=gson.toJson(list.get(i));
            System.out.println(result);
            myjson.add(result);
        }
      //  String res=new Gson().toJson(myjson);
      //  response.setContentLength(res.length());
     //   response.getWriter().write(res);
        return "ok";
    }

    //添加凭证信息 传入用户名，科目名称（在此用户下的），交易金额，凭证组号，备注（如果包含应收账款，还应当传入客户姓名）
    //用户名和科目名称必有   ,凭证
    // 返回 status： 1.Success （成功）  2.NoUser （没有此用户）3.NoAccount （没有此科目，注：是在该用户的情况下没此科目名字）4.NoCustomerInfo（如果包含收账且没有该用户的客户信息）


    public String adDocument() throws IOException{
        InsearchUtil insearchUtil=new InsearchUtil();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //跨域设置返回头
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token");//这里“Access-Token”是我要传到后台的内容key
        response.setHeader("Access-Control-Expose-Headers", "*");
        System.out.println("accept request in adDocument");
        if (request.getMethod().equals("OPTIONS")) {
            System.out.println("it is OPTIONS");
            return "ok";
        }
        //没有此用户就返回 status:NoUser
        String user_username=request.getParameter("user_username");
        String account_name=request.getParameter("account_name");
        // BigDecimal document_money=new BigDecimal(request.getParameter("document_money"));
        BigDecimal document_debitside=new BigDecimal(request.getParameter("document_debitside"));
        BigDecimal document_creditside=new BigDecimal(request.getParameter("document_creditside"));
        int document_group=Integer.parseInt(request.getParameter("document_group"));
        String document_note=request.getParameter("document_note");
        String customerinfo_name=request.getParameter("customerinfo_name");
        Calendar document_time=Calendar.getInstance();
        document_time.set(Calendar.YEAR,Integer.parseInt(request.getParameter("YEAR")));
        document_time.set(Calendar.MONTH,Integer.parseInt(request.getParameter("MONTH"))-1);
        document_time.set(Calendar.DAY_OF_MONTH,Integer.parseInt(request.getParameter("DAY_OF_MONTH")));
        document_time.set(Calendar.HOUR_OF_DAY,Integer.parseInt(request.getParameter("HOUR_OF_DAY")));
        document_time.set(Calendar.MINUTE,Integer.parseInt(request.getParameter("MINUTE")));
        document_time.set(Calendar.SECOND,Integer.parseInt(request.getParameter("SECOND")));
        System.out.println("hi:"+customerinfo_name);
        JsonObject jsonObject=new JsonObject();
        List<User> users=insearchUtil.searchUser("user_username",user_username);
        if(users.isEmpty()){
            jsonObject.addProperty("status","NoUser");
        }else {
            List<Account> accounts = insearchUtil.searchAccount("account_user.user_username", user_username);
            Boolean flag = true;
            Account account=null;
            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).getAccount_name().equals(account_name)) {
                    account=accounts.get(i);
                    flag = false;
                    break;
                }
            }
            //该用户没有此项科目
            if (flag) {
                jsonObject.addProperty("status","NoCustomerInfo");
            }else{

                if(customerinfo_name!=null){
                    List<CustomerInfo> customerInfos=insearchUtil.searchCustomerInfo("customerinfo_user.user_username",user_username);
                    Boolean flag1=true;
                    CustomerInfo customerInfo=null;
                    for(int i=0;i<customerInfos.size();i++){
                        if(customerInfos.get(i).getCustomerinfo_name().equals(customerinfo_name)){
                            customerInfo=customerInfos.get(i);
                            flag1=false;
                            break;
                        }
                    }
                    if(flag1){
                        jsonObject.addProperty("status","NoCustomerInfo");
                    }else{
                        account=insearchUtil.cgAccount(account,document_debitside,document_creditside);
                        customerInfo=insearchUtil.cgCustomerInfo(customerInfo,document_debitside,document_creditside);
                        User user=users.get(0);
                        user=insearchUtil.cgUser(user,document_group+1);

                        Document document = new Document();
                        document.setDocument_group(document_group);
                        //  document.setDocument_money(document_money);
                        document.setDocument_debitside(document_debitside);
                        document.setDocument_creditside(document_creditside);
                        document.setDocument_note(document_note);
                        document.setDocument_user(user);
                        document.setDocument_account(account);
                        document.setDocument_time(document_time);

                        AccountReceivable accountReceivable=new AccountReceivable();
                        //  accountReceivable.setAccountreceivable_money(document_money);
                        accountReceivable.setAccountreceivable_debitside(document_debitside);
                        accountReceivable.setAccountreceivable_creditside(document_creditside);
                        accountReceivable.setAccountreceivable_document(document);
                        accountReceivable.setAccountreceivable_customerinfo(customerInfo);

                        insearchUtil.save(document);
                        insearchUtil.save(accountReceivable);
                        jsonObject.addProperty("status", "success");
                    }
                }else {
                    account=insearchUtil.cgAccount(account,document_debitside,document_creditside);


                    Document document = new Document();
                    document.setDocument_group(document_group);
                    // document.setDocument_money(document_money);
                    document.setDocument_debitside(document_debitside);
                    document.setDocument_creditside(document_creditside);
                    document.setDocument_note(document_note);
                    document.setDocument_user(users.get(0));
                    document.setDocument_account(account);
                    document.setDocument_time(document_time);
                    insearchUtil.save(document);
                    jsonObject.addProperty("status", "success");
                }
            }
        }
        response.setContentLength(jsonObject.toString().length());
        response.getWriter().write(jsonObject.toString());
        return "ok";
    }

    /*获取凭证信息，传入用户名，获取该用户的所有凭证信息，返回一个list<String> 的json
    String 为
    document_money
    document_note
    document_group
    的json格式
    或者返回status:NoUser 的json
    */
    public String geDocument() throws IOException{
        InsearchUtil insearchUtil=new InsearchUtil();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //跨域设置返回头
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token");//这里“Access-Token”是我要传到后台的内容key
        response.setHeader("Access-Control-Expose-Headers", "*");
        System.out.println("accept request in geDocument");
        if (request.getMethod().equals("OPTIONS")) {
            System.out.println("it is OPTIONS");
            return "ok";
        }

        String user_username=request.getParameter("user_username");
        List<User> users=insearchUtil.searchUser("user_username",user_username);
        if(users.isEmpty()){
            JsonObject jsonObject=new JsonObject();
            jsonObject.addProperty("status","NoUser");
            response.setContentLength(jsonObject.toString().length());
            response.getWriter().write(jsonObject.toString());
            return "ok";
        }

        List<Document> list=insearchUtil.searchDocument("document_user.user_username",user_username);
     //   List<String> list1=new ArrayList<String>();
        myjson.clear();
        for(int i=0;i<list.size();i++){
            /*GsonBuilder gsonBuilder=new GsonBuilder();

            gsonBuilder.setExclusionStrategies(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                    Boolean flag=false;
                    if(fieldAttributes.getName().equals("document_accountreceivable"))
                        flag=true;
                    else if(fieldAttributes.getName().equals("document_user"))
                        flag=true;
                    else if(fieldAttributes.getName().equals("document_account"))
                        flag=true;
                    else if(fieldAttributes.getName().equals("document_id"))
                        flag=true;
                    return flag;
                }
                @Override
                public boolean shouldSkipClass(Class<?> aClass) {
                    return false;
                }
            });
            Gson gson=gsonBuilder.create();
            String result=gson.toJson(list.get(i));
            System.out.println(result);
            myjson.add(result);*/
            JsonObject jsonObject=new JsonObject();
            jsonObject.addProperty("document_group",list.get(i).getDocument_group());
            jsonObject.addProperty("document_time",new Gson().toJson(list.get(i).getDocument_time()));
            jsonObject.addProperty("document_note",list.get(i).getDocument_note());
            jsonObject.addProperty("account_name",list.get(i).getDocument_account().getAccount_name());
            jsonObject.addProperty("document_creditside",list.get(i).getDocument_creditside());
            jsonObject.addProperty("document_debitside",list.get(i).getDocument_debitside());
            String result=jsonObject.toString();
            System.out.println(result);
            myjson.add(result);
        }

     //   String res=new Gson().toJson(list1);
     //   response.setContentLength(res.length());
      //  response.getWriter().write(res);
        return "ok";
    }
    /*获取应收账款信息，传入用户名
    返回一个list<String> 的json
    String 为

    document_time
    document_note
    document_group
    account_name
    customerinfo_name
    customerinfo_company
    customerinfo_phone
    的json格式
    或者返回status:NoUser 的json
    */
    public String geAccountReceivable() throws IOException{
        InsearchUtil insearchUtil=new InsearchUtil();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //跨域设置返回头
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token");//这里“Access-Token”是我要传到后台的内容key
        response.setHeader("Access-Control-Expose-Headers", "*");
        System.out.println("accept request in geAccountReceivable");
        if (request.getMethod().equals("OPTIONS")) {
            System.out.println("it is OPTIONS");
            return "ok";
        }

        String user_username=request.getParameter("user_username");
        List<User> users=insearchUtil.searchUser("user_username",user_username);
        if(users.isEmpty()){
            JsonObject jsonObject=new JsonObject();
            jsonObject.addProperty("status","NoUser");
            response.setContentLength(jsonObject.toString().length());
            response.getWriter().write(jsonObject.toString());
            return "ok";
        }

        List<AccountReceivable> list=insearchUtil.searchAccountReceivable("accountreceivable_document.document_user.user_username",user_username);
        JsonObject jsonObject1=new JsonObject();
     //   List<String> list1=new ArrayList<String>();
        myjson.clear();
        for(int i=0;i<list.size();i++){
            JsonObject jsonObject=new JsonObject();
            jsonObject.addProperty("accountreceivable_debitside",list.get(i).getAccountreceivable_debitside().toString());
            jsonObject.addProperty("accountreceivable_creditside",list.get(i).getAccountreceivable_creditside().toString());
            jsonObject.addProperty("document_time",list.get(i).getAccountreceivable_document().getDocument_time().getTime().toString());
            jsonObject.addProperty("document_note",list.get(i).getAccountreceivable_document().getDocument_note());
            jsonObject.addProperty("document_group",list.get(i).getAccountreceivable_document().getDocument_group());
            jsonObject.addProperty("account_name",list.get(i).getAccountreceivable_document().getDocument_account().getAccount_name());
            jsonObject.addProperty("customerinfo_name",list.get(i).getAccountreceivable_customerinfo().getCustomerinfo_name());
            jsonObject.addProperty("customerinfo_company",list.get(i).getAccountreceivable_customerinfo().getCustomerinfo_company());
            jsonObject.addProperty("customerinfo_phone",list.get(i).getAccountreceivable_customerinfo().getCustomerinfo_phone());
            myjson.add(jsonObject.toString());
        }
      //  Gson gson=new Gson();
       // String res=gson.toJson(list1);
     //   response.setContentLength(res.length());
     //   response.getWriter().write(res);
        return "ok";

    }

    /*传入用户名，科目名称，增加的月初借方金额，增加的月初贷方金额
     * 返回json
     * */
    public String adAccountMoney()throws  IOException{
        InsearchUtil insearchUtil=new InsearchUtil();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //跨域设置返回头
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token");//这里“Access-Token”是我要传到后台的内容key
        response.setHeader("Access-Control-Expose-Headers", "*");
        System.out.println("accept request in geAccountReceivable");
        if (request.getMethod().equals("OPTIONS")) {
            System.out.println("it is OPTIONS");
            return "ok";
        }
        String user_username=request.getParameter("user_username");
        String account_name=request.getParameter("account_name");
        // BigDecimal account_addmoney=new BigDecimal(request.getParameter("account_addmoney"));
        BigDecimal account_debitside_base=new BigDecimal(request.getParameter("account_debitside_base"));
        BigDecimal account_creditside_base=new BigDecimal(request.getParameter("account_creditside_base"));
        List<User> users=insearchUtil.searchUser("user_username",user_username);
        if(users.isEmpty()){
            JsonObject jsonObject=new JsonObject();
            jsonObject.addProperty("status","NoUser");
            response.setContentLength(jsonObject.toString().length());
            response.getWriter().write(jsonObject.toString());
            return "ok";
        }
        Account account=null;
        List<Account> lists=insearchUtil.searchAccount("account_user.user_username",user_username);
        for(int i=0;i<lists.size();i++){
            if(lists.get(i).getAccount_name().equals(account_name)){
                account=lists.get(i);
                break;
            }
        }
        insearchUtil.adAccountMoney(account,account_debitside_base,account_creditside_base);
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("status","success");
        response.setContentLength(jsonObject.toString().length());
        response.getWriter().write(jsonObject.toString());
        return "ok";
    }
}
