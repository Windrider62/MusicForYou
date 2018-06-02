package omniserver.demo.DataLayer;
import omniserver.demo.Models.RadioStationList;
import omniserver.demo.Models.RadioStationModel;
import omniserver.demo.fileEditor.PathConverter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebRadio_Dal {

    private RadioStationList radioList= new RadioStationList();
    private String path="src/main/java/omniserver/demo/ObjectFiles/RadioStations.json";

    public WebRadio_Dal(){
        readJsonRadioStations();
        path= PathConverter.StringConverter(path);

    }
    private  void readJsonRadioStations(){//read json file with radio stations to List<objects>
        JSONParser parser= new JSONParser();
        try{


            FileReader reader= new FileReader(path);
            Object obj= parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray radioStations = (JSONArray) jsonObject.get("RadioStations");

            for (Object radioStation : radioStations) {
                JSONObject radio=(JSONObject)radioStation;
                RadioStationModel radioModel= new RadioStationModel();
                radioModel.name=radio.get("name").toString();
                radioModel.host=radio.get("host").toString();
                radioModel.path=radio.get("path").toString();
                radioList.RadioStations.add(radioModel);
            }



        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
    public List<String> GetAllRadioStationNames(){//Get all radio station names
        List<String> ListRadioNames= new ArrayList<>();
        for (RadioStationModel radio: radioList.RadioStations){
             ListRadioNames.add(radio.name);
        }
        return ListRadioNames;
    }
    public RadioStationModel GetRadioStationByName(String RadioStationName){//get the radio station object by name
        RadioStationModel webRadio= new RadioStationModel();
        for ( RadioStationModel radio: radioList.RadioStations){
            if(radio.name.equals(RadioStationName)){
                webRadio= radio;
            }
        }
        return webRadio;

    }
}
