package omniserver.LogicLayer;
import omniserver.Interfaces.IWebRadio_Dal;
import omniserver.Models.RadioStationModel;


import java.util.List;

public class WebRadio_Logic {

    private IWebRadio_Dal _webRadioDal;

    public WebRadio_Logic(IWebRadio_Dal _dal){
        _webRadioDal=_dal;
    }
    public List<String> GetAllRadioStationNames(){//Get all radio station names

        return _webRadioDal.GetAllRadioStationNames();
    }
    public RadioStationModel GetRadioStationByName(String RadioStationName){//get the radio station object by name

        return _webRadioDal.GetRadioStationByName(RadioStationName);

    }






}
