import omniserver.Interfaces.IHttp_Dal;
import omniserver.LogicLayer.User_Logic;
import omniserver.Models.UserModel;
import omniserver.UnitTest_Methods.TestFolder.Http_Dal_Test;
import omniserver.UnitTest_Methods.TestFolder.User_Dal_Test;
import org.junit.Test;

import static org.junit.Assert.*;

public class User_LogicTest {
 private User_Dal_Test _testDal= new User_Dal_Test();
 private User_Logic _logic= new User_Logic(_testDal);

    @Test
    public void autenticateUser() {
        UserModel user= new UserModel();
        user.name="admin";
        user.password="admin";
        Boolean acces=_logic.autenticateUser(user);
        assertEquals(true, acces);
    }
}