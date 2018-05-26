package omniserver.demo.LogicLayer;

import omniserver.demo.DataLayer.User_Dal;
import omniserver.demo.Models.UserModel;

public class User_Logic {
    User_Dal _userDal= new User_Dal();

public boolean autenticateUser(UserModel user){
    return _userDal.AutenticateUser(user);
}
}
