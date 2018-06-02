package omniserver.UnitTest_Methods.TestFolder;
import omniserver.Interfaces.IHttp_Dal;

import java.net.HttpURLConnection;
import java.net.URL;

public class Http_Dal_Test implements IHttp_Dal {


    public String HttpGetMethod(String requestPath) throws Exception{// http get method to arduino

        URL url = new URL(requestPath);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        System.out.println(conn.getResponseMessage());
        System.out.println("end method");
        return conn.getResponseMessage();

    }
}
