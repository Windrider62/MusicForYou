package omniserver.LogicLayer;
import omniserver.DataLayer.NodeInfo_Dal;
import omniserver.Interfaces.INodeInfo_Dal;
import omniserver.Models.Node;

import java.util.List;

public class NodeInfo_Logic {

    private INodeInfo_Dal _nodeInfo_dal;

    public NodeInfo_Logic(INodeInfo_Dal nodeInfo_dal){
        _nodeInfo_dal=nodeInfo_dal;
    }

    //get all the nodes form the json file
    public List<Node> GetAllNodes(){
    return _nodeInfo_dal.GetAllNodes();
    }
    public Boolean AddNewNode(Node node)  {
        return _nodeInfo_dal.addNewNode(node);
    }
    public String RenameNode(String oldName, String newName)  {
        return _nodeInfo_dal.RenameNode(oldName, newName);
    }
    public Boolean RemoveNode(String nodeName) {
        return _nodeInfo_dal.RemoveNode(nodeName);
    }

}
