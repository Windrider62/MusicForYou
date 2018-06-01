package omniserver.demo.LogicLayer;
import omniserver.demo.DataLayer.NodeInfo_Dal;
import omniserver.demo.Models.Node;
import java.util.List;

public class NodeInfo_Logic {

    private NodeInfo_Dal _NodeDal= new NodeInfo_Dal();


    public List<Node> GetAllNodes(){//get all the nodes form the json file
    return _NodeDal.nodeList.nodes;
    }
    public Boolean AddNewNode(Node node)  {
        return _NodeDal.addNewNode(node);
    }
    public String RenameNode(String oldName, String newName)  {
        return _NodeDal.RenameNode(oldName, newName);
    }
    public Boolean RemoveNode(String nodeName) {
        return _NodeDal.RemoveNode(nodeName);
    }

}
