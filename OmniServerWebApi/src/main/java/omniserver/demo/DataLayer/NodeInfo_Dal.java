package omniserver.demo.DataLayer;


import omniserver.demo.Models.Node;
import omniserver.demo.PathConverter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NodeInfo_Dal {

    public List<Node> NodeList = new ArrayList<Node>();
    private  String path="src\\main\\java\\omniserver\\demo\\ObjectFiles\\nodeConfig.json";

    public NodeInfo_Dal(){
      ReadNodeInfoJson();
      path= PathConverter.StringConverter(path);
    }


    private void ReadNodeInfoJson() {//read json file with node information to List<objects>
        JSONParser parser = new JSONParser();
        try {


            FileReader reader = new FileReader(path);
            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray nodes = (JSONArray) jsonObject.get("nodes");

            for (Object radioStation : nodes) {
                JSONObject radio = (JSONObject) radioStation;
                Node node = new Node();
                node.name = radio.get("name").toString();
                node.ip = radio.get("ip").toString();
                NodeList.add(node);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public List<Node> GetAllNodes(){// returns all the node information
        return NodeList;
    }

    public Boolean addNewNode(Node node) {// returns all the node information
        try {
            JSONObject obj = new JSONObject((Map) node);
            FileWriter fileWriter = new FileWriter(path, true);
        return true;
        }
        catch (Exception e) {
            return false;
        }
    }
    /*public Boolean EditNodeName() {// returns all the node information
        JSONParser parser = new JSONParser();
        try {


            FileReader reader = new FileReader(path);
            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray nodes = (JSONArray) jsonObject.get("nodes");




        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    */
}
