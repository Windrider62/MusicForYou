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

    @GetMapping("/station-names")
    public List<String> GetRadioStationNames(){
        return _logic.getAllRadioStationNames();
    }

    @GetMapping("/station-by-name/{name}")
    public RadioStationModel GetRadioStationByName (@PathVariable("name") String RadioName){
        return _logic.GetRadioStationByName("radio 1");
    }
}
