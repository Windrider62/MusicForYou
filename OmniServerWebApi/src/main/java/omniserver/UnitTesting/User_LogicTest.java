package omniserver.UnitTesting;

import omniserver.LogicLayer.User_Logic;
import omniserver.Models.UserModel;
import omniserver.UnitTest_Methods.TestFolder.User_Dal_Test;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class User_LogicTest {
 private User_Dal_Test _testDal= new User_Dal_Test();
 private User_Logic _logic= new User_Logic(_testDal);

    @Test
    public void autenticateUser() {

        assertEquals(true, testAutent("admin","admin"));
    }
    @Test
    public void autenticateUserFalse() {

        assertEquals(false, testAutent("a", "admin"));
    }
    private Boolean testAutent(String name , String password){
        UserModel user= new UserModel();
        user.name=name;
        user.password=password;

        return _logic.autenticateUser(user);
    }
}