package gateway;

import controller.webservice.Controller;
import gateway.message.CurrentStatusReq;
import persistence.UserInfo;

import java.util.UUID;

public class Gateway implements IGateway {

    @Override
    public void changeDeviceStatus(UserInfo user, UUID deviceId, int value) {
    }

    @Override
    public int requestCurrentDeviceStatus(UserInfo user, UUID deviceId) {
        CurrentStatusReq currentStatusReq = new CurrentStatusReq();


        return 0;
    }
}
