package omniserver.demo.Controllers;

import omniserver.demo.LogicLayer.NodeHttpRequests;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NodeMusicOperationController {
    private NodeHttpRequests _nodeHttp = new NodeHttpRequests();

    @GetMapping("/node/start")
    public String startMusic() throws Exception {

        try {
            return _nodeHttp.StartMusic("");
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST).toString();
        }
    }

    @GetMapping("/node/stop")
    public String StopMusic() throws Exception {

        try {
            return _nodeHttp.StopMusic("");
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST).toString();
        }
    }
    @GetMapping("/node/volumeplus/{volume}")
    public String MusicVolumePlus(@PathVariable("volume") int volume) throws Exception {

        try {
            return _nodeHttp.MusicVolumePlus("", volume);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST).toString();
        }
    }
    @GetMapping("/node/volumemin/{volume}")
    public String MusicVolumeMin(@PathVariable("volume") int volume) throws Exception {

        try {
            return _nodeHttp.MusicVolumeMin("", volume);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST).toString();
        }
    }
    @GetMapping("/node/changeradiostation/{host}")
    public String ChangeRadioStation(@PathVariable("host") String host,@PathVariable("path") String path) throws Exception {

        try {
            return _nodeHttp.ChangeRadioStation("", host, path);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST).toString();
        }
    }
}
