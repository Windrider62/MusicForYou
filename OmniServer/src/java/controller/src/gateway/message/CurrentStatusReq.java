package gateway.message;

public class CurrentStatusReq {

    private int val1;
    private String val2;

    public CurrentStatusReq() {
    }

    public CurrentStatusReq(int val1, String val2) {
        this.val1 = val1;
        this.val2 = val2;
    }

    @Override
    public String toString() {
        return  "{" +
                "val1=" + val1 +
                ", val2='" + val2 + '\'' +
                '}';
    }
}
