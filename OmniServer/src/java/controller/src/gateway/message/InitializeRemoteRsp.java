package gateway.message;

import controller.common.GatewayCntrCB;
import gateway.IGatewayCntrCB;
import gateway.protocol.Command;

public class InitializeRemoteRsp extends Command{

    private String macAddress;

    public InitializeRemoteRsp(String macAddress) {
        this.macAddress = macAddress;
    }

    @Override
    public void processMessage() {
        IGatewayCntrCB gatewayCntrCB = new GatewayCntrCB();
        gatewayCntrCB.registerHomeBox(macAddress);
    }
}
