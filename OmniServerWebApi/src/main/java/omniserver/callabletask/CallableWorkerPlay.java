package omniserver.demo.callabletask;

import omniserver.demo.LogicLayer.NodeHttpRequests_Logic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.concurrent.Callable;

public class CallableWorkerPlay implements Callable<String>{

	String name;
	List<String> nodeIps;
	private NodeHttpRequests_Logic _nodeHttp = new NodeHttpRequests_Logic();

	public CallableWorkerPlay(String name, List<String> nodeIps) {
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
			return _nodeHttp.StartMusic(nodeIps);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST).toString();
		}
	}
	
}
