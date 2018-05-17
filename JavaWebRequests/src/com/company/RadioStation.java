package com.company;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class RadioStation {
	public List<WebRadioModel> radioList= new ArrayList<>();

	public RadioStation(){
	    readJsonRadioStations();
    }

	public  List<WebRadioModel> readJsonRadioStations(){//read json file with radio stations to List<objects>
        JSONParser parser= new JSONParser();
        try{

            FileReader reader= new FileReader("src\\RadioStations.json");
            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray radioStations = (JSONArray) jsonObject.get("RadioStations");

            for (Object radioStation : radioStations) {
                JSONObject radio=(JSONObject)radioStation;
                WebRadioModel radioModel= new WebRadioModel();
                radioModel.name=radio.get("name").toString();
                radioModel.host=radio.get("host").toString();
                radioModel.path=radio.get("path").toString();
                radioList.add(radioModel);
            }



        }
         catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    return radioList;
    }

    public List<String> getAllRadioStationNames(){//Get all radio station names
	    List<String> ListRadioNames= new ArrayList<>();
	    for (WebRadioModel radio: radioList){
            ListRadioNames.add(radio.name);
        }
        return ListRadioNames;
    }

    public WebRadioModel GetRadioStationByName(String RadioStationName){//get the radio station object by name
        WebRadioModel webRadio= new WebRadioModel();
        for (WebRadioModel radio: radioList){
            if(radio.name.equals(RadioStationName)){
                webRadio= radio;
            }
        }
        return webRadio;

    }
}
