package omniserver.demo.Controllers;

import omniserver.demo.LogicLayer.NodeInfo_Logic;
import omniserver.demo.Models.Node;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class NodeInfoController {

    private NodeInfo_Logic _nodeLogic= new NodeInfo_Logic();

    @GetMapping("nodeInfo/getnodes")
    public List<Node> getAllNodes(){
        return _nodeLogic.GetAllNodes();

    }
    @PostMapping("nodeInfo/addnode")
    public boolean AddNewNode(@RequestBody Node node) throws IOException {
        return _nodeLogic.AddNewNode(node);

    }
    @PostMapping("nodeInfo/renamenode/{oldname}/{newname}")
    public String RenameNode(@PathVariable("oldname") String oldName, @PathVariable("newname") String newName) throws IOException {
        //Node name if no error return new name else return old name
        return _nodeLogic.RenameNode(oldName, newName);

    }
    @DeleteMapping("nodeInfo/deletenode/{nodename}")
    public Boolean RenameNode(@PathVariable("nodename") String nodeName) throws IOException {
        //Node name if no error return new name else return old name
        return _nodeLogic.RemoveNode(nodeName);

    }

}
