package omniserver.Controllers;

import omniserver.DataLayer.User_Dal;
import omniserver.LogicLayer.User_Logic;
import omniserver.Models.UserModel;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private User_Dal user_dal= new User_Dal();
    private User_Logic _userLogic= new User_Logic(user_dal);

    @PostMapping("/user/login")
    public Boolean AutenicateUser(@RequestBody UserModel user){
        return _userLogic.autenticateUser(user);

    }
}
