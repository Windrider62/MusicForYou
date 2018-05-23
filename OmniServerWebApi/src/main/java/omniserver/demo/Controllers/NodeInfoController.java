package omniserver.demo.Controllers;

import omniserver.demo.LogicLayer.NodeInfo_Logic;
import omniserver.demo.Models.Node;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NodeInfoController {

    NodeInfo_Logic _nodeLogic= new NodeInfo_Logic();

    @GetMapping("nodeInfo/GetNodes")
    public List<Node> getAllNodes(){
        return _nodeLogic.GetAllNodes();

    }
    @GetMapping("nodeInfo/AddNode")
    public boolean AddNewNode(@RequestBody Node node){
        return _nodeLogic.AddNewNode(node);

    }

}
