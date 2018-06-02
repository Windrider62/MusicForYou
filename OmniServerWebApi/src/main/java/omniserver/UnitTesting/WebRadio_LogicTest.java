package omniserver.UnitTesting;

import omniserver.LogicLayer.WebRadio_Logic;
import omniserver.Models.RadioStationModel;
import omniserver.UnitTest_Methods.TestFolder.WebRadio_Dal_Test;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class WebRadio_LogicTest {
    private WebRadio_Dal_Test _testDal= new WebRadio_Dal_Test();
    private WebRadio_Logic _logic= new WebRadio_Logic(_testDal);

    @Test
    public void getAllRadioStationNames() {
        List<String> stations= _logic.GetAllRadioStationNames();
        assertEquals(true, stations.size()>0);
    }

    @Test
    public void getRadioStationByName() {
        RadioStationModel station= _logic.GetRadioStationByName("radio 1");
        assertEquals(true, station.host!=null);


    }
    @Test
    public void getRadioStationByNameFalse() {
        RadioStationModel station= _logic.GetRadioStationByName("sds");
        assertEquals(true, station.host==null);


    }
}