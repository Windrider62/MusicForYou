package persistence;

import controller.common.DeviceData;
import controller.common.DeviceType;

import java.util.ArrayList;
import java.util.UUID;

public class Persistence implements IPersistence {
    @Override
    public UserLogin getUserLogin(String user) {
        // TODO make database call to get userlogin info


        // hardcoded for testing
        return new UserLogin("user1", "1234", UUID.fromString("0af75061-9b09-45b6-8e0f-4f1d1197df98"));
    }

    @Override
    public ArrayList<Device> getDeviceList(UUID groupId) {
        // TODO make database call to get devices

        // hardcoded for testing
        ArrayList<Device> devices= new ArrayList<>();

        devices.add(new Device());

        return devices;
    }

    @Override
    public void registerDevice(UUID deviceId) {

    }

    @Override
    public void deleteDevice(UUID deviceId) {

    }

    @Override
    public UserInfo getUserInfo(UUID userId, String userName) {
        UserInfo userInfo = new UserInfo(userId, userName);
        return userInfo;
    }

    @Override
    public ArrayList<DeviceData<DeviceType>> requestDeviceHistory() {
        return null;
    }

    @Override
    public void storeDeviceData(ArrayList<DeviceData<DeviceType>> data) {

    }

    @Override
    public ArrayList<Group> getAllGroups(UUID userId) {
        // TODO make database call to get groups

        // hardcoded for testing
        ArrayList<Group> groups = new ArrayList<>();
        groups.add(new Group());
        return groups;
    }

    @Override
    public void registerHomeBox(UUID homeBoxId) {

    }
}
