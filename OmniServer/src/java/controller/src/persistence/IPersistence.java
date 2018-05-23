package persistence;

import controller.common.DeviceData;
import controller.common.DeviceType;

import java.util.ArrayList;
import java.util.UUID;

public interface IPersistence {
    UserLogin getUserLogin(String user);

    UserInfo getUserInfo(UUID user, String name);

    ArrayList<Device> getDeviceList(UUID groupId);

    ArrayList<DeviceData<DeviceType>> requestDeviceHistory();

    void registerDevice(UUID deviceId);

    void deleteDevice(UUID deviceId);

    ArrayList<Group> getAllGroups(UUID userId);

    void registerHomeBox(UUID homeBoxId);

    void storeDeviceData(ArrayList<DeviceData<DeviceType>> data);
  }
