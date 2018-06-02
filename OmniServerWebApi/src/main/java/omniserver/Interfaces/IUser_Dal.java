package omniserver.Interfaces;

import omniserver.Models.UserModel;
import omniserver.fileEditor.PathConverter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface IUser_Dal {

     Boolean AutenticateUser(UserModel user);
}
