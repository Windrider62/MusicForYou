package omniserver.demo.DataLayer;


import omniserver.demo.Models.Node;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NodeInfo_Dal {

    public List<Node> NodeList = new ArrayList<Node>();
    private  String path="src\\main\\java\\omniserver\\demo\\ObjectFiles\\nodeConfig.json";

    public NodeInfo_Dal(){
        ReadNodeInfoJson();
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

    public Boolean addNewNode() {// returns all the node information
        /*try {
            Node node = new Node();
            org.json.JSONObject obj = new org.json.JSONObject(node);
            FileWriter fileWriter = new FileWriter(path, true);
        return true;
        }
        catch (Exception e){
            return false;
        }
        */
        return null;
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
