package omniserver.DataLayer;
import omniserver.Interfaces.IUser_Dal;
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

public class User_Dal implements IUser_Dal {
    private String path="src/main/java/omniserver/ObjectFiles/Users.json";

    public User_Dal(){

        path= PathConverter.StringConverter(path);

    }
    public Boolean AutenticateUser(UserModel user){
        boolean acces=false;
        List<UserModel>Users= new ArrayList<>();
        JSONParser parser= new JSONParser();
        try{


            FileReader reader= new FileReader(path);
            Object obj= parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray listUsers = (JSONArray) jsonObject.get("Users");

            for (Object u : listUsers) {
                JSONObject jUser=(JSONObject)u;
                UserModel userModel= new UserModel();
                userModel.name=jUser.get("name").toString();
                userModel.password=jUser.get("password").toString();
                Users.add(userModel);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for(UserModel u:Users){
            if(user.name.equals(user.name)&&user.name.equals(user.name)){
                acces =true;
            }
            else
            {
                acces= false;
            }
        }
        return acces;
    }
}
