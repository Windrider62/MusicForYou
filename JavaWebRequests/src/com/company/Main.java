package com.company;
import java.io.*;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;






public class Main {


    static HttpControls httpControls= new HttpControls();

    public static void main(String[] args) {
        readJsonRadioStations();
    }

    public static void readJsonRadioStations(){
        JSONParser parser= new JSONParser();

        try{

            FileReader reader= new FileReader("src\\RadioStations.json");
            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray radioStations = (JSONArray) jsonObject.get("RadioStations");
            for (Object radioStation : radioStations) {
                JSONObject radio=(JSONObject)radioStation;
                System.out.println(radio.get("name")+"---"+radio.get("country"));
            }



        }
         catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


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
