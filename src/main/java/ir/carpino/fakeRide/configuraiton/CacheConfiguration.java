package ir.carpino.fakeRide.configuraiton;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "fake-ride.cache")
public class CacheConfiguration {
    private int expireTime;
}
