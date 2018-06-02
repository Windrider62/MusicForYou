package omniserver.LogicLayer;
import omniserver.Interfaces.IHttp_Dal;
import omniserver.Interfaces.IWebRadio_Dal;
import omniserver.Models.RadioStationModel;
import java.util.List;


public class NodeHttpRequests_Logic {

     private  IHttp_Dal NodeHttp;
     private  IWebRadio_Dal _radioDal;
     private WebRadio_Logic webRadioLogic;

     public NodeHttpRequests_Logic(IHttp_Dal http_dal, IWebRadio_Dal radioDal){
         NodeHttp=http_dal;
         _radioDal=radioDal;
         webRadioLogic= new WebRadio_Logic(_radioDal);
     }

    public String StartMusic(List<String> nodeIpAdress)throws Exception{// starts music, only the  arduino ipadress is used
        for(String ip:nodeIpAdress) {
            String url = ip + "/start";
             NodeHttp.HttpGetMethod(url);
        }
        return "";
    }
    public String StopMusic(List<String>  nodeIpAdress)throws Exception{// stops music, only the  arduino ipadress is used
        for(String ip:nodeIpAdress) {
        String url=ip+"/stop";
             NodeHttp.HttpGetMethod(url);
        }
        return "";
    }
    public  String MusicVolume(List<String>  nodeIpAdress, int volume)throws Exception{// volume + music. send arduino ip adress and volume as int(negative works as well)
        int newVolume=0;
         if(150-volume<=0){
             newVolume=0;
         }
         else{
              newVolume=150-volume;
         }
        for(String ip:nodeIpAdress) {
            String url = ip + "/changevolume?vol=" + newVolume;
              NodeHttp.HttpGetMethod(url);
        }
        return "";
    }

    public String ChangeRadioStation(List<String>  nodeIpAdress, String stationName)throws Exception{//change the radio station path. send ip, web url host(no http:// or www.) and the path
        RadioStationModel station = webRadioLogic.GetRadioStationByName(stationName);

        for(String ip:nodeIpAdress) {
        String url=ip+String.format("/changepath?host=%s&path=%s", station.host,station.path);
          NodeHttp.HttpGetMethod(url);
        }
        return "";
    }

}
