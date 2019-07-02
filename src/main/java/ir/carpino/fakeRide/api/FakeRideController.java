package ir.carpino.fakeRide.api;

import ir.carpino.fakeRide.model.polygon.Polygon;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class FakeRideController {


    @RequestMapping(path = "/start", method = RequestMethod.POST)
    public ResponseEntity getUserByUserName(@Valid @RequestBody() Polygon polygon) throws Exception {
        return null;
    }

    @RequestMapping(path = "/stop", method = RequestMethod.GET)
    public ResponseEntity getUserByUserName() {
        return null;
    }
}
