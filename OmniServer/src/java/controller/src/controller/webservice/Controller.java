package controller.webservice;

import javax.jws.WebService;

import controller.common.Authenticator;
import gateway.Gateway;
import gateway.IGateway;
import persistence.*;

import java.util.ArrayList;
import java.util.UUID;

//@WebService(
//      name="FontysDomoticaController",
//      portName="FontysDomoticaControllerPort",
//      serviceName="IControllerService",
//      targetNamespace="http://192.168.2.3:20020"
// )
@WebService
public class Controller implements IController {

    // @Resource
    // https://docs.oracle.com/cd/E14571_01/web.1111/e13734/stateful.htm#WSADV238
    // session management


    Authenticator auth = new Authenticator();
    IPersistence persist = new Persistence();
    IGateway gateway = new Gateway();

    @Override
    public boolean login(String user, String pass) {
        // TODO set session state
        return auth.Authenticate(user, pass);
    }

    @Override
    public boolean logout(String user) {
        // @Resource
        // https://docs.oracle.com/cd/E14571_01/web.1111/e13734/stateful.htm#WSADV238
        // session management
        return true;
    }

    @Override
    public UserInfo getUserInfo(String userId, String userName) {
        return persist.getUserInfo(UUID.fromString(userId), userName);
    }

    @Override
    public ArrayList<Device> getDeviceList(String groupId) {
        return persist.getDeviceList(UUID.fromString(groupId));
    }

    @Override
    public int requestCurrentDeviceStatus(String deviceId) {
        // get session info from current user, pass this to gateway to find the corrosponding tcpconnection
        UserInfo userInfo = new UserInfo(UUID.randomUUID(), "random");
        return gateway.requestCurrentDeviceStatus(userInfo, UUID.fromString(deviceId));
    }

    @Override
    public void changeDeviceStatus(String deviceId, int value) {
        // this method should have a boolean returnvalue with a succes/fail callback
        // get session info from current user, pass this to gateway to find the corrosponding tcpconnection
        UserInfo userInfo = new UserInfo();
        gateway.changeDeviceStatus(userInfo, UUID.fromString(deviceId), value);
    }


    @Override
    public ArrayList<Group> showAllGroups(String userId) {
        return persist.getAllGroups(UUID.fromString(userId));
    }
}
