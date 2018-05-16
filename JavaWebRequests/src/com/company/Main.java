package com.company;

public class Main {


    static HttpControls httpControls= new HttpControls();

    public static void main(String[] args) throws Exception{
        System.out.println( ChangeRadioStation("http://172.20.10.4", "icecast.omroep.nl", "funx-slowjamz-sb-aac"));
    }
    public  static String StartMusic(String nodeIpAdress)throws Exception{
        String url=nodeIpAdress+"/start";
        return  HttpControls.HttpGetMethod(url);
    }
    public  static String StopMusic(String nodeIpAdress)throws Exception{
        String url=nodeIpAdress+"/stop";
        return  HttpControls.HttpGetMethod(url);
    }
    public   static String MusicVolumePlus(String nodeIpAdress, int volume)throws Exception{
        String url=nodeIpAdress+"/volumeplus?vol="+volume;
        return  HttpControls.HttpGetMethod(url);
    }
    public   static String MusicVolumeMin(String nodeIpAdress, int volume)throws Exception{
        String url=nodeIpAdress+"/volumemin?vol="+volume;
        return  HttpControls.HttpGetMethod(url);
    }
    public static String ChangeRadioStation(String nodeIpAdress, String host, String path)throws Exception{
        String url=nodeIpAdress+String.format("/changepath?host=%s&path=%s", host,path);
        System.out.println(url);
        return HttpControls.HttpGetMethod(url);
    }
}
