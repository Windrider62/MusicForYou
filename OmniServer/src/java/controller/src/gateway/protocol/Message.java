package gateway.protocol;

import java.io.Serializable;

public class Message extends ProtocolHeader implements Serializable{
    private Command command = null;

    public Message(String target, String source, int version, String info, ProtocolCommand p, Command cmd) {
        super(target, source, version, info, p);
        this.command = cmd;
    }

    public Command getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return "Message{" +
                super.toString() +
                "COMMAND" + command.toString() +
                '}';
    }
}
