package ir.carpino.fakeRide.model.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MqttPayloadPickUp {
    private String lat;
    private String lng;
    private String address;
}