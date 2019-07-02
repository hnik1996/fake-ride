package ir.carpino.fakeRide.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.carpino.fakeRide.model.mqtt.User;
import ir.carpino.fakeRide.repository.OnlineUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class MqttService implements IMqttMessageListener {

    @Value("${fake-ride.mqtt.location-topic}")
    private String locationTopic;


    @Value("${fake-ride.mqtt.ride-topic}")
    private String rideTopic;

    private final IMqttClient client;
    private final OnlineUserRepository onlineUserRepository;
    private final ObjectMapper mapper;

    @Autowired
    public MqttService(IMqttClient client, OnlineUserRepository onlineUserRepository) {
        this.client = client;
        this.onlineUserRepository = onlineUserRepository;

        mapper = new ObjectMapper();
    }

    @PostConstruct
    private void subscribe() throws MqttException {
        client.subscribe(locationTopic, 2, this);
    }

    public void publish(MqttMessage mqttMessage, String id) throws MqttException {
        client.publish(rideTopic + id, mqttMessage);
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        String content = message.toString();
        User user = mapper.readValue(content, User.class);

        onlineUserRepository.aliveUser(user);
    }

    @Scheduled(fixedRateString = "${fake-ride.log.milliseconds-rate}")
    public void onlineLog() {
        log.info("online users {}", onlineUserRepository.onlineUserCount());
    }


}
