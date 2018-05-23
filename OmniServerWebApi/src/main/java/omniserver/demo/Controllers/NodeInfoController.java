package omniserver.demo.Controllers;

import omniserver.demo.Models.Node;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NodeInfoController {

    @GetMapping("nodeInfo/GetNodes")
    public List<Node> getAllNodes(){
        return null;

    }
}
