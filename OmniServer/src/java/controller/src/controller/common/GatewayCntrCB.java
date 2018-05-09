package controller.common;

import gateway.IGatewayCntrCB;
import persistence.IPersistence;
import persistence.Persistence;

import java.util.ArrayList;
import java.util.UUID;

public class GatewayCntrCB implements IGatewayCntrCB {
    IPersistence persist = new Persistence();

    @Override
    public void registerHomeBox(String homeBoxId) {
        persist.registerHomeBox(UUID.fromString(homeBoxId));
    }

    @Override
    public void registerDevice(String deviceId) {
        persist.registerDevice(UUID.fromString(deviceId));
    }

    @Override
    public void storeDeviceData(ArrayList<DeviceData<DeviceType>> data){
        persist.storeDeviceData(data);
    }

    @Override
    public void deleteDevice(String deviceId) {
        persist.deleteDevice(UUID.fromString(deviceId));
    }
}
