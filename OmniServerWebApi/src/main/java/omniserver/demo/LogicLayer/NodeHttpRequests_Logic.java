package omniserver.demo.LogicLayer;

import omniserver.demo.DataLayer.Http_Dal;
import omniserver.demo.Models.RadioStationModel;


public class NodeHttpRequests_Logic {

     private static Http_Dal NodeHttp= new Http_Dal();
     private WebRadio_Logic webRadioLogic= new WebRadio_Logic();

    public String StartMusic(String nodeIpAdress)throws Exception{// starts music, only the  arduino ipadress is used
        String url=nodeIpAdress+"/start";
        return  NodeHttp.HttpGetMethod(url);
    }
    public String StopMusic(String nodeIpAdress)throws Exception{// stops music, only the  arduino ipadress is used
        String url=nodeIpAdress+"/stop";
        return  NodeHttp.HttpGetMethod(url);
    }
    public  String MusicVolumePlus(String nodeIpAdress, int volume)throws Exception{// volume + music. send arduino ip adress and volume as int(negative works as well)
        int newVolume=0;
         if(150-volume<=0){
             newVolume=0;
         }
         else{
              newVolume=150-volume;
         }
         String url=nodeIpAdress+"/volumeplus?vol="+newVolume;
        return  NodeHttp.HttpGetMethod(url);
    }
    public  String MusicVolumeMin(String nodeIpAdress, int volume)throws Exception{// volume - music. send arduino ip adress and volume as int(negative works as well)
        int newVolume=0;
         if(150-volume<=0){
             newVolume=0;
         }
         else{
              newVolume=150-volume;
         }
         String url=nodeIpAdress+"/volumemin?vol="+newVolume;
        return  NodeHttp.HttpGetMethod(url);
    }
    public String ChangeRadioStation(String nodeIpAdress, String stationName)throws Exception{//change the radio station path. send ip, web url host(no http:// or www.) and the path
        RadioStationModel station = webRadioLogic.GetRadioStationByName(stationName);
        String url=nodeIpAdress+String.format("/changepath?host=%s&path=%s", station.host,station.path);
        System.out.println(url);
        return NodeHttp.HttpGetMethod(url);
    }

}
