package gateway.protocol;

import java.io.Serializable;

public class ProtocolHeader implements Serializable{
    private String target;
    private String source;
    private int version;
    private String info;
    private ProtocolCommand cmd;


    public ProtocolHeader(String target, String source, int version, String info, ProtocolCommand cmd) {
        this.target = target;
        this.source = source;
        this.version = version;
        this.info = info;
        this.cmd = cmd;
    }

    public String getTarget() {
        return target;
    }

    public String getSource() {
        return source;
    }

    public int getVersion() {
        return version;
    }

    public String getInfo() {
        return info;
    }

    public ProtocolCommand getCmd() {
        return cmd;
    }

    @Override
    public String toString() {
        return "HEADER{" +
                ", target=" + target +
                ", source=" + source +
                ", version=" + version +
                ", info=" + info +
                ", cmd=" + cmd +
                "}, ";
    }
}
