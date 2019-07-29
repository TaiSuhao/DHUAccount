package com.dhufore;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Tess {
    public static void main(String args[]) throws UnsupportedEncodingException {
        /*JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("status","哈哈");
        System.out.println(jsonObject.toString());
        Gson gson=new Gson();
        temp tp=new temp();
        tp.status="哈哈哈";
        System.out.println(gson.toJson(tp));
        List<String> list=new ArrayList<String>();
        list.add(jsonObject.toString());
        System.out.println(gson.toJson(list));*/
       // String temp="你好";
      //  temp=new String(temp.getBytes(),"chunked");
       // System.out.println(temp.length());
        temp tp=new temp();
        temp tp2=tp;
        System.out.println(tp.a);
        tp2.a=1000;
        System.out.println(tp.a);

    }
}
class temp{
    int a=1;
}


