package omniserver.demo.LogicLayer;

import omniserver.demo.Models.RadioStationModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebRadio_Logic {
    private  List<RadioStationModel> radioList= new ArrayList<>();

    public WebRadio_Logic(){
        readJsonRadioStations();
    }

    private  void readJsonRadioStations(){//read json file with radio stations to List<objects>
        JSONParser parser= new JSONParser();
        try{


            FileReader reader= new FileReader("src\\main\\java\\omniserver\\demo\\ObjectFiles\\RadioStations.json");
            Object obj= parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray radioStations = (JSONArray) jsonObject.get("RadioStations");

            for (Object radioStation : radioStations) {
                JSONObject radio=(JSONObject)radioStation;
                RadioStationModel radioModel= new RadioStationModel();
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


    }

    public List<String> getAllRadioStationNames(){//Get all radio station names
        List<String> ListRadioNames= new ArrayList<>();
        for (RadioStationModel radio: radioList){
            boolean add = ListRadioNames.add(radio.name);
        }
        return ListRadioNames;
    }

    public RadioStationModel GetRadioStationByName(String RadioStationName){//get the radio station object by name
        RadioStationModel webRadio= new RadioStationModel();
        for ( RadioStationModel radio: radioList){
            if(radio.name.equals(RadioStationName)){
                webRadio= radio;
            }
        }
        return webRadio;

    }

}
