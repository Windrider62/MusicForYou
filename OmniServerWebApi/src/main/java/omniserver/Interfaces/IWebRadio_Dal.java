package omniserver.Interfaces;

import omniserver.Models.RadioStationModel;
import java.util.List;

public interface IWebRadio_Dal {


     List<String> GetAllRadioStationNames();
     RadioStationModel GetRadioStationByName(String RadioStationName);
}
