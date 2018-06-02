package omniserver.UnitTest_Methods.TestFolder;


import com.google.gson.Gson;
import omniserver.Interfaces.INodeInfo_Dal;
import omniserver.Models.Node;
import omniserver.Models.NodeList;
import omniserver.fileEditor.PathConverter;
import omniserver.fileEditor.WriteFile;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;


public class NodeInfo_Dal_Test implements INodeInfo_Dal {

    private NodeList nodeList = new NodeList();
    private  String path="src/main/java/omniserver/UnitTest_Methods/TestFiles/nodeConfigTest.json";

    public NodeInfo_Dal_Test(){
      ReadNodeInfoJson();
      path= PathConverter.StringConverter(path);
    }


    private void ReadNodeInfoJson() {//read json file with node information to List<objects>
        nodeList.nodes.clear();
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
    public List<Node> GetAllNodes(){
      ReadNodeInfoJson();
      return nodeList.nodes;
    }
    public Boolean addNewNode(Node node) {// returns all the node information
      ReadNodeInfoJson();
      nodeList.nodes.add(node);
      String json= new Gson().toJson(nodeList);
      WriteFile fileWriter= new WriteFile();
      return fileWriter.WriteFileOperator(path, json);

    }
    public String RenameNode(String oldName, String newNodeName) {// returns all the node information
        ReadNodeInfoJson();
        for(Node node: nodeList.nodes){
            if(node.name.equals(oldName)){
                node.name=newNodeName;
            }
        }

        String json= new Gson().toJson(nodeList);
        WriteFile fileWriter= new WriteFile();
        if(fileWriter.WriteFileOperator(path, json)){
            return newNodeName;
        }
        else
        {
            return oldName;
        }

    }
    public Boolean RemoveNode(String nodeName) {// returns all the node information
        ReadNodeInfoJson();
        Node nodeToRemove= new Node();
        for(Node node: nodeList.nodes){
            if(node.name.equals(nodeName)){
                nodeToRemove=node;
            }
        }
        nodeList.nodes.remove(nodeToRemove);
        String json= new Gson().toJson(nodeList);
        WriteFile fileWriter= new WriteFile();
        return fileWriter.WriteFileOperator(path, json);


    }

}
