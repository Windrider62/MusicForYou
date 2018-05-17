package com.company;


public class Main {


    static HttpControls httpControls= new HttpControls();

    public static void main(String[] args) {

    }

    

    public  static String StartMusic(String nodeIpAdress)throws Exception{// starts music, only the  arduino ipadress is used
        String url=nodeIpAdress+"/start";
        return  HttpControls.HttpGetMethod(url);
    }
    public  static String StopMusic(String nodeIpAdress)throws Exception{// stops music, only the  arduino ipadress is used
        String url=nodeIpAdress+"/stop";
        return  HttpControls.HttpGetMethod(url);
    }
    public   static String MusicVolumePlus(String nodeIpAdress, int volume)throws Exception{// volume + music. send arduino ip adress and volume as int(negative works as well)
        String url=nodeIpAdress+"/volumeplus?vol="+volume;
        return  HttpControls.HttpGetMethod(url);
    }
    public   static String MusicVolumeMin(String nodeIpAdress, int volume)throws Exception{// volume - music. send arduino ip adress and volume as int(negative works as well)
        String url=nodeIpAdress+"/volumemin?vol="+volume;
        return  HttpControls.HttpGetMethod(url);
    }
    public static String ChangeRadioStation(String nodeIpAdress, String host, String path)throws Exception{//change the radio station path. send ip, web url host(no http:// or www.) and the path
        String url=nodeIpAdress+String.format("/changepath?host=%s&path=%s", host,path);
        System.out.println(url);
        return HttpControls.HttpGetMethod(url);
    }
}
