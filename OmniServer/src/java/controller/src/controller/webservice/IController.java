package controller.webservice;

import persistence.Device;
import persistence.Group;
import persistence.UserInfo;

import java.util.ArrayList;


public interface IController {

    boolean login(String user, String pass);

    boolean logout(String user);

    UserInfo getUserInfo(String userId, String userName);

    ArrayList<Device> getDeviceList(String groupId);

    int requestCurrentDeviceStatus(String deviceId);

    ArrayList<Group> showAllGroups(String userId);

    void changeDeviceStatus(String deviceId, int value);

    //    Message<T> requestDeviceHistory();

}
