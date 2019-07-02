package ir.carpino.fakeRide.service;

import com.google.gson.Gson;
import ir.carpino.fakeRide.model.Ride;
import ir.carpino.fakeRide.model.request.MqttPayloadDropOff;
import ir.carpino.fakeRide.model.request.MqttPayloadPickUp;
import ir.carpino.fakeRide.model.request.RequestedRide;
import ir.carpino.fakeRide.repository.OnlineUserRepository;
import ir.carpino.fakeRide.repository.RideRepository;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.Distance;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FakeRide {

    @Value("${fake-ride.geo.minimumDistance}")
    private double distance;

    private final RideRepository rideRepo;
    private final OnlineUserRepository onlineUserRepo;
    private final MqttService mqttService;

    @Autowired
    public FakeRide(RideRepository rideRepo, OnlineUserRepository onlineUserRepo, MqttService mqttService) {
        this.rideRepo = rideRepo;
        this.onlineUserRepo = onlineUserRepo;
        this.mqttService = mqttService;
    }


    @Scheduled(fixedRateString = "${fake-ride.create.milliseconds-rate}")
    public void fireUp() {
        onlineUserRepo.getOnlineUsers()
                .forEach(
                        (k, v) -> {

                            Ride ride = rideRepo.findByPickupNear(v.getPoint(), new Distance(distance)).get(0);

                            if (ride != null) {

                                MqttPayloadPickUp payloadPickUp = MqttPayloadPickUp.builder()
                                        .lat(ride.getPickup().getX() + "")
                                        .lng(ride.getPickup().getY() + "")
                                        .address(ride.getPickupAddress()).build();

                                MqttPayloadDropOff payloadDropOff = MqttPayloadDropOff.builder()
                                        .lat(ride.getDest().getX() + "")
                                        .lng(ride.getDest().getY() + "")
                                        .address(ride.getDestAddress()).build();

                                RequestedRide requestedRide = RequestedRide.builder()
                                        .rideId(ride.getId() + "")
                                        .price(ride.getTotalPrice())
                                        .rideType("SINGLE")
                                        .waitingTime(0)
                                        .mqttPayloadPickUp(payloadPickUp)
                                        .mqttPayloadDropOff(payloadDropOff)
                                        .responseTime(3000)
                                        .driverId(v.getId())
                                        //TODO driver location list was null in other code
                                        .driverLocation(null)
                                        .isCanceled("false").build();

                                try {
                                    createRideAndCancel(requestedRide);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                );
//        onlineUserRepo.getOnlineUsers().entrySet().stream().forEach();
    }

    @Async
    public void createRideAndCancel(RequestedRide requestedRide) throws MqttException, InterruptedException {
        MqttMessage mqttMessage = new MqttMessage(requestRideToString(requestedRide).getBytes());
        mqttMessage.setQos(1);
        mqttMessage.setRetained(false);

        mqttService.publish(mqttMessage, requestedRide.getDriverId());

        Thread.sleep(5000);

        requestedRide.setIsCanceled("true");

        mqttService.publish(new MqttMessage(requestRideToString(requestedRide).getBytes()), requestedRide.getDriverId());


    }

    private String requestRideToString(RequestedRide requestedRide) {
        return new Gson().toJson(requestedRide);
    }

}
