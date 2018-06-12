package omniserver.UnitTesting;

import omniserver.UnitTest_Methods.TestFolder.NodeInfo_Dal_Test;
import omniserver.LogicLayer.NodeInfo_Logic;
import omniserver.Models.Node;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class NodeInfo_LogicTest {

    private NodeInfo_Dal_Test _testDal= new NodeInfo_Dal_Test();
    private NodeInfo_Logic _logic= new NodeInfo_Logic(_testDal);
    @Test
    public void getAllNodes() throws Exception {
       List<Node> nodeList= _logic.GetAllNodes();
       assertEquals(true, nodeList.size()>0);
    }

    @Test
    public void addNewNode() {
        Node node=new Node();
        node.name="testNode";
        node.ip="111Test";
        Boolean nodeAdded= _logic.AddNewNode(node);
        assertEquals(true, nodeAdded);
        _logic.RemoveNode("testNode");

    }

    @Test
    public void renameNode() {
        addNewNode();
        String renamedNode=_logic.RenameNode("testNode", "testNodeUpdated");
        assertEquals("testNodeUpdated", renamedNode);
        _logic.RemoveNode("testNodeUpdated");
    }

    @Test
    public void removeNode() {
        addNewNode();

        Boolean nodeRemoved=_logic.RemoveNode("testNode");
        assertEquals(true, nodeRemoved);
    }
}