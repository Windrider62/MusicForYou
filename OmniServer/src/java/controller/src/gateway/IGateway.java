package gateway;

import persistence.UserInfo;

import java.util.UUID;

public interface IGateway {
    int requestCurrentDeviceStatus(UserInfo user, UUID deviceId);

    void changeDeviceStatus(UserInfo user, UUID deviceId, int value);
}
