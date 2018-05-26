package omniserver.demo.DataLayer;


import com.google.gson.Gson;
import omniserver.demo.Models.Node;
import omniserver.demo.Models.NodeList;
import omniserver.demo.fileEditor.PathConverter;
import omniserver.demo.fileEditor.WriteFile;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NodeInfo_Dal {

    public NodeList nodeList = new NodeList();
    private  String path="src/main/java/omniserver/demo/ObjectFiles/nodeConfig.json";

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
                nodeList.nodes.add(node);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public List<Node> GetAllNodes(){// returns all the node information
        return nodeList.nodes;
    }

    public Boolean addNewNode(Node node) {// returns all the node information
      nodeList.nodes.add(node);
      String json= new Gson().toJson(nodeList);
      WriteFile fileWriter= new WriteFile();
      return fileWriter.WriteFileOperator(path, json);

    }
    public String RenameNode(String oldName, String newNodeName) {// returns all the node information
        for(Node node: nodeList.nodes){
            if(node.name.equals(oldName)){
                node.name=newNodeName;
            }
        }

        String json= new Gson().toJson(nodeList.nodes);
        WriteFile fileWriter= new WriteFile();
        if(fileWriter.WriteFileOperator(path, json)){
            return newNodeName;
        }
        else
        {
            return oldName;
        }

    }

}
