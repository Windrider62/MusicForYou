package com.company;

public class Main {


    static HttpControls httpControls= new HttpControls();

    public static void main(String[] args) throws Exception{
        System.out.println( StartStopMusic("http://172.20.10.4/start"));
    }
    public  static String StartStopMusic(String Requesturl)throws Exception{
        return  HttpControls.HttpGetMethod(Requesturl);
    }
    public   static String ControlTheMusic(String Requesturl)throws Exception{
        return  HttpControls.HttpGetMethod(Requesturl);
    }
}
