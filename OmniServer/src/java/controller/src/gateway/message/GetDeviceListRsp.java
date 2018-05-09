package gateway.message;

import gateway.protocol.Command;
import persistence.Device;

import java.util.ArrayList;

public class GetDeviceListRsp extends Command {
    ArrayList<Device> devices;

    @Override
    public void processMessage() {

    }
}
