package omniserver.Controllers;

import omniserver.DataLayer.WebRadio_Dal;
import omniserver.LogicLayer.WebRadio_Logic;
import omniserver.Models.RadioStationModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RadioStationController {

    private WebRadio_Dal _dal= new WebRadio_Dal();
    private WebRadio_Logic _logic = new WebRadio_Logic(_dal);

    @GetMapping("/stationnames")//returns all radio station names
    public List<String> GetRadioStationNames(){
        return _logic.GetAllRadioStationNames();
    }

    @GetMapping("/stationbyname/{name}")//returns 1 radio station by name
    public RadioStationModel GetRadioStationByName (@PathVariable("name") String RadioName){
        return _logic.GetRadioStationByName(RadioName);
    }
}
