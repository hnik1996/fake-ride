package ir.carpino.fakeRide.configuraiton;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "fake-ride.mqtt")
public class MqttConfiguration {
    private String url;
    private String username;
    private String password;
    private int connectionTimeout;

    private MqttConnectOptions getMqttOption() {
        MqttConnectOptions options = new MqttConnectOptions();

        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(connectionTimeout);

        options.setUserName(username);
        options.setPassword(password.toCharArray());

        return options;
    }

    @Bean
    public IMqttClient getMqttClient() throws MqttException {
        MqttClient publisher = new MqttClient(url, UUID.randomUUID().toString());
        publisher.connect(getMqttOption());

        return publisher;
    }
}
