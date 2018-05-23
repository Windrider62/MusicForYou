package Node;

import java.net.HttpURLConnection;
import java.net.URL;

public class NodeHttp {
    public   String HttpGetMethod(String requestPath) throws Exception{// http get method to arduino

        URL url = new URL(requestPath);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        System.out.println(conn.getResponseMessage());
        System.out.println("end method");
        return conn.getResponseMessage();

    }
}
