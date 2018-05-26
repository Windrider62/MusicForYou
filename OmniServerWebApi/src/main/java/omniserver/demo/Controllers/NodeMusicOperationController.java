package omniserver.demo.Controllers;

import omniserver.demo.LogicLayer.NodeHttpRequests_Logic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NodeMusicOperationController {

    private NodeHttpRequests_Logic _nodeHttp = new NodeHttpRequests_Logic();



    @PostMapping("/node/start")//starts the music on the node
    public String startMusic(@RequestBody List<String> nodeIps)  {

        try {

            return _nodeHttp.StartMusic(nodeIps);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST).toString();
        }
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
