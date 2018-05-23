package omniserver.demo.LogicLayer;

import omniserver.demo.DataLayer.Http_Dal;


public class NodeHttpRequests_Logic {

     private static Http_Dal NodeHttp= new Http_Dal();

    public String StartMusic(String nodeIpAdress)throws Exception{// starts music, only the  arduino ipadress is used
        String url=nodeIpAdress+"/start";
        return  NodeHttp.HttpGetMethod(url);
    }
    public String StopMusic(String nodeIpAdress)throws Exception{// stops music, only the  arduino ipadress is used
        String url=nodeIpAdress+"/stop";
        return  NodeHttp.HttpGetMethod(url);
    }
    public  String MusicVolumePlus(String nodeIpAdress, int volume)throws Exception{// volume + music. send arduino ip adress and volume as int(negative works as well)
        String url=nodeIpAdress+"/volumeplus?vol="+volume;
        return  NodeHttp.HttpGetMethod(url);
    }
    public  String MusicVolumeMin(String nodeIpAdress, int volume)throws Exception{// volume - music. send arduino ip adress and volume as int(negative works as well)
        String url=nodeIpAdress+"/volumemin?vol="+volume;
        return  NodeHttp.HttpGetMethod(url);
    }
    public String ChangeRadioStation(String nodeIpAdress, String host, String path)throws Exception{//change the radio station path. send ip, web url host(no http:// or www.) and the path
        String url=nodeIpAdress+String.format("/changepath?host=%s&path=%s", host,path);
        System.out.println(url);
        return NodeHttp.HttpGetMethod(url);
    }
}
