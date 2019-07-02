package ir.carpino.fakeRide.model.mqtt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String id;

    @JsonProperty("loc")
    private String location;

    @JsonProperty("ts")
    private String timeStamp;

    private Long lastPublished;

    private double lat;
    private double lon;

    public GeoJsonPoint getPoint() {
        String[] geoLoc = this.location.split(",");
        lat = Double.valueOf(geoLoc[0]);
        lon = Double.valueOf(geoLoc[1]);

        return new GeoJsonPoint(lat, lon);
    }
}
