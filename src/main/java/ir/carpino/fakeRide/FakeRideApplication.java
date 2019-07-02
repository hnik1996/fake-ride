package ir.carpino.fakeRide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FakeRideApplication {

	public static void main(String[] args) {
		SpringApplication.run(FakeRideApplication.class, args);
	}

}
