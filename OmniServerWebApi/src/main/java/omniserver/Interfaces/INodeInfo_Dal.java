package omniserver.Interfaces;
import omniserver.Models.Node;
import omniserver.Models.NodeList;

import java.util.List;


public interface INodeInfo_Dal {

     NodeList nodeList = new NodeList();
     String path = null;


     List<Node> GetAllNodes();
     Boolean addNewNode(Node node);
     String RenameNode(String oldName, String newNodeName);
     Boolean RemoveNode(String nodeName);
}
