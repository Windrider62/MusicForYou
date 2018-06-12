package omniserver.Controllers;

import io.swagger.annotations.Api;
import omniserver.DataLayer.Http_Dal;
import omniserver.DataLayer.WebRadio_Dal;
import omniserver.LogicLayer.NodeHttpRequests_Logic;
import omniserver.callabletask.CallableWorkerPlay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@RestController
public class NodeMusicOperationController {

    @Autowired
    ThreadPoolTaskExecutor threadPool;
    Http_Dal _dal= new Http_Dal();
    WebRadio_Dal _radioDal= new WebRadio_Dal();

    private NodeHttpRequests_Logic _nodeHttp = new NodeHttpRequests_Logic(_dal, _radioDal);
    private int threadNumber =0;
    List<Future<String>> futureList = new ArrayList<>();




    @PostMapping("/node/start")//starts the music on the node
    public void startMusic(@RequestBody List<String> nodeIps)  {
    threadNumber++;
    CallableWorkerPlay callableTask = new CallableWorkerPlay(String.valueOf(threadNumber),nodeIps);
    Future<String> result = threadPool.submit(callableTask);
    futureList.add(result);
    }

    @PostMapping("/node/stop")// stop the music on the node
    public String StopMusic(@RequestBody List<String> nodeIps)  {

        try {
            return _nodeHttp.StopMusic(nodeIps);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST).toString();
        }
    }
    @PostMapping("/node/changevolume/{volume}")
    public String MusicVolume(@RequestBody List<String> nodeIps, @PathVariable("volume") int volume) throws Exception {

        try {
            return _nodeHttp.MusicVolume(nodeIps, volume);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST).toString();
        }
    }

    @PostMapping("/node/changeradiostation/{stationname}")
    public String ChangeRadioStation(@RequestBody List<String> nodeIps,@PathVariable("stationname") String stationName) throws Exception {

        try {
            return _nodeHttp.ChangeRadioStation(nodeIps, stationName);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST).toString();
        }
    }

}
