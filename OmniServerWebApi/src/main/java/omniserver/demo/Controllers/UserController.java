package omniserver.demo.Controllers;

import omniserver.demo.LogicLayer.User_Logic;
import omniserver.demo.Models.UserModel;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    User_Logic _userLogic= new User_Logic();

    @PostMapping("/user/login")
    public Boolean AutenicateUser(@RequestBody UserModel user){
        return _userLogic.autenticateUser(user);

    }
}
