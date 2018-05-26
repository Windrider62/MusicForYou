package omniserver.demo.LogicLayer;

import omniserver.demo.DataLayer.NodeInfo_Dal;
import omniserver.demo.Models.Node;

import java.util.List;

public class NodeInfo_Logic {

    private NodeInfo_Dal _NodeDal= new NodeInfo_Dal();


    public List<Node> GetAllNodes(){//get all the nodes form the json file
    return _NodeDal.NodeList;
    }
    public boolean AddNewNode(Node node){
        return _NodeDal.addNewNode(node);
    }

}
