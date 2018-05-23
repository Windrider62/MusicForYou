package omniserver.demo.Controllers;

import omniserver.demo.LogicLayer.NodeHttpRequests_Logic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NodeMusicOperationController {

    private NodeHttpRequests_Logic _nodeHttp = new NodeHttpRequests_Logic();
    private String NodeIp= "http://172.20.10.4";


    @GetMapping("/node/start")//starts the music on the node
    public String startMusic()  {

        try {
            return _nodeHttp.StartMusic(NodeIp);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST).toString();
        }
    }

    @GetMapping("/node/stop")// stop s the music on the node
    public String StopMusic()  {

        try {
            return _nodeHttp.StopMusic(NodeIp);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST).toString();
        }
    }
    @GetMapping("/node/volumeplus/{volume}")
    public String MusicVolumePlus(@PathVariable("volume") int volume) throws Exception {

        try {
            return _nodeHttp.MusicVolumePlus(NodeIp, volume);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST).toString();
        }
    }
    @GetMapping("/node/volumemin/{volume}")
    public String MusicVolumeMin(@PathVariable("volume") int volume) throws Exception {

        try {
            return _nodeHttp.MusicVolumeMin(NodeIp, volume);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST).toString();
        }
    }
    @GetMapping("/node/changeradiostation/{stationname}")
    public String ChangeRadioStation(@PathVariable("stationname") String stationName) throws Exception {

        try {
            return _nodeHttp.ChangeRadioStation(NodeIp, stationName);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST).toString();
        }
    }
}
