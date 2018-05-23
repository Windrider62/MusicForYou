package gateway.protocol;

import java.io.Serializable;

public abstract class Command implements ICommand, Serializable{

    ProtocolCommand cmd;

    @Override
    public String toString() {
        return "toString not implemented for this cmd";
    }

    public Object getType(){
        return this.getClass();
    }
}
