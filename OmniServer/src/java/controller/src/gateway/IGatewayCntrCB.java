package gateway;

import controller.common.DeviceData;
import controller.common.DeviceType;

import java.util.ArrayList;

public interface IGatewayCntrCB {
    void registerHomeBox(String homeBoxId);
    void registerDevice(String deviceId);
    void storeDeviceData(ArrayList<DeviceData<DeviceType>> data);
    void deleteDevice(String deviceId);
}

