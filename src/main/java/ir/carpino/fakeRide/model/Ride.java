package ir.carpino.fakeRide.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Setter
@Getter
@Document(collection = "rides_lastyear")
public class Ride {
    @Id
    private long id;
    @Field(value = "pickup")
    private GeoJsonPoint pickup;
    @Field(value = "dest")
    private GeoJsonPoint dest;
    @Field(value = "pickup_address")
    private String pickupAddress;
    @Field(value = "dest_address")
    private String destAddress;
    @Field(value = "total_price")
    private double totalPrice;
    @Field(value = "type")
    private String type;

}
