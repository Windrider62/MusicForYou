package persistence;

import java.util.UUID;

public class UserInfo {
    private UUID userId;
    private String userName;

    public UserInfo() {
    }

    public UserInfo(UUID userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
