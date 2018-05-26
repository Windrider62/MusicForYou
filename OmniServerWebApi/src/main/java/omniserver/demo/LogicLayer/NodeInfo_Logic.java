package omniserver.demo.LogicLayer;

import omniserver.demo.DataLayer.NodeInfo_Dal;
import omniserver.demo.Models.Node;

import java.io.IOException;
import java.util.List;

public class NodeInfo_Logic {

    private NodeInfo_Dal _NodeDal= new NodeInfo_Dal();


    public List<Node> GetAllNodes(){//get all the nodes form the json file
    return _NodeDal.nodeList.nodes;
    }
    public boolean AddNewNode(Node node) throws IOException {
        return _NodeDal.addNewNode(node);
    }
    public String RenameNode(String oldName, String newName) throws IOException {
        return _NodeDal.RenameNode(oldName, newName);
    }
    public Boolean RemoveNode(String nodeName) throws IOException {
        return _NodeDal.RemoveNode(nodeName);
    }

}
