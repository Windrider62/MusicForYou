package omniserver.Controllers;

import omniserver.DataLayer.NodeInfo_Dal;
import omniserver.LogicLayer.NodeInfo_Logic;
import omniserver.Models.Node;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class NodeInfoController {

    private NodeInfo_Dal _dal= new NodeInfo_Dal();
    private NodeInfo_Logic _nodeLogic= new NodeInfo_Logic(_dal);

    @GetMapping("nodeinfo/getnodes")
    public List<Node> getAllNodes(){
        return _nodeLogic.GetAllNodes();

    }
    @PostMapping("nodeinfo/addnode")
    public Boolean AddNewNode(@RequestBody Node node) throws IOException {
        return _nodeLogic.AddNewNode(node);

    }
    @PostMapping("nodeinfo/renamenode/{oldname}/{newname}")
    public String RenameNode(@PathVariable("oldname") String oldName, @PathVariable("newname") String newName) throws IOException {
        //Node name if no error return new name else return old name
        return _nodeLogic.RenameNode(oldName, newName);

    }
    @DeleteMapping("nodeinfo/deletenode/{nodename}")
    public Boolean RemoveNode(@PathVariable("nodename") String nodeName) throws IOException {
        //Node name if no error return new name else return old name
        return _nodeLogic.RemoveNode(nodeName);

    }

}
