package omniserver.demo.Controllers;

import omniserver.demo.LogicLayer.WebRadio_Logic;
import omniserver.demo.Models.RadioStationModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RadioStationController {

    private WebRadio_Logic _logic = new WebRadio_Logic();

    @GetMapping("/stationnames")//returns all radio station names
    public List<String> GetRadioStationNames(){
        return _logic.GetAllRadioStationNames();
    }

    @GetMapping("/stationbyname/{name}")//returns 1 radio station by name
    public RadioStationModel GetRadioStationByName (@PathVariable("name") String RadioName){
        return _logic.GetRadioStationByName(RadioName);
    }
}
