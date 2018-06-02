package omniserver.LogicLayer;
import omniserver.Interfaces.IUser_Dal;
import omniserver.Models.UserModel;

public class User_Logic {
    IUser_Dal _userDal;


public User_Logic(IUser_Dal _dal){
    _userDal=_dal;
}
public Boolean autenticateUser(UserModel user){
    return _userDal.AutenticateUser(user);
}
}
