package omniserver.callabletask;

import omniserver.DataLayer.Http_Dal;
import omniserver.DataLayer.WebRadio_Dal;
import omniserver.LogicLayer.NodeHttpRequests_Logic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.concurrent.Callable;

public class CallableWorkerStop implements Callable<String>{

	String name;
	List<String> nodeIps;
	private Http_Dal _httpDal= new Http_Dal();
	private WebRadio_Dal _radioDal= new WebRadio_Dal();
	private NodeHttpRequests_Logic _nodeHttp = new NodeHttpRequests_Logic(_httpDal, _radioDal);

	public CallableWorkerStop(String name, List<String> nodeIps) {
		this.name = name;
		this.nodeIps =nodeIps;
	}

	@Override
	public String call() throws Exception {
		process();
		String message = String.format("CallableWorker name: %s is Done", name);
		return message;
	}


	private String process(){
		try {
			return _nodeHttp.StopMusic(nodeIps);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST).toString();
		}
	}
	
}
