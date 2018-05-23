package RadioStations;

import Models.radioStationModel;
import org.json.simple.*;
import org.json.simple.parser.*;

import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class webRadioStation {
    
    public List<Models.radioStationModel> radioList= new ArrayList<>();

    public webRadioStation(){
        readJsonRadioStations();
    }

    public  List<radioStationModel> readJsonRadioStations(){//read json file with radio stations to List<objects>
        JSONParser parser= new JSONParser();
            try{

            FileReader reader= new FileReader("src\\ObjectFiles\\RadioStations.json");
            Object obj= parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray radioStations = (JSONArray) jsonObject.get("RadioStations");

            for (Object radioStation : radioStations) {
                JSONObject radio=(JSONObject)radioStation;
                radioStationModel radioModel= new radioStationModel();
                radioModel.name=radio.get("name").toString();
                radioModel.host=radio.get("host").toString();
                radioModel.path=radio.get("path").toString();
                radioList.add(radioModel);
            }



        }
         catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
                e.printStackTrace();
            }

        return radioList;
    }

    public List<String> getAllRadioStationNames(){//Get all radio station names
        List<String> ListRadioNames= new ArrayList<>();
        for (Models.radioStationModel radio: radioList){
            boolean add = ListRadioNames.add(radio.name);
        }
        return ListRadioNames;
    }

    public radioStationModel GetRadioStationByName(String RadioStationName){//get the radio station object by name
        radioStationModel webRadio= new radioStationModel();
        for (Models.radioStationModel radio: radioList){
            if(radio.name.equals(RadioStationName)){
                webRadio= radio;
            }
        }
        return webRadio;

    }
}
