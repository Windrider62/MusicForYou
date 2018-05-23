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


    public NodeInfo_Dal(){
        ReadNodeInfoJson();

    }

    private void ReadNodeInfoJson() {//read json file with node information to List<objects>
        JSONParser parser = new JSONParser();
        try {


            FileReader reader = new FileReader("src\\main\\java\\omniserver\\demo\\ObjectFiles\\nodeConfig.json");
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
}
