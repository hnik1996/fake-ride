package ir.carpino.fakeRide.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class RequestedRide {
    private String rideId;
    private double price;
    private String rideType;
    private int waitingTime;
    private MqttPayloadPickUp mqttPayloadPickUp;
    private MqttPayloadDropOff mqttPayloadDropOff;
    private int responseTime;
    private List<Double> driverLocation;
    private String driverId;
    private int step;
    private int radius;
    private String isCanceled;
}
