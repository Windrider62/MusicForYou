package omniserver.demo.LogicLayer;
import omniserver.demo.DataLayer.WebRadio_Dal;
import omniserver.demo.Models.RadioStationModel;


import java.util.List;

public class WebRadio_Logic {

    private WebRadio_Dal _webRadioDal= new WebRadio_Dal();

    public List<String> GetAllRadioStationNames(){//Get all radio station names

        return _webRadioDal.GetAllRadioStationNames();
    }
    public RadioStationModel GetRadioStationByName(String RadioStationName){//get the radio station object by name

        return _webRadioDal.GetRadioStationByName(RadioStationName);

    }






}
