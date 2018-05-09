package persistence;

import java.util.UUID;

public class UserLogin {
    private UUID userID;
    private String userName;
    private String passWord;


    public UserLogin(String userName, String passWord, UUID userID) {
        this.userName = userName;
        this.passWord = passWord;
        this.userID = userID;
    }

    public boolean validatePassword(String pass){
        return this.passWord.equals(pass);
    }
}
