package ir.carpino.fakeRide.repository;

import ir.carpino.fakeRide.model.Ride;
import org.springframework.data.geo.Distance;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RideRepository extends MongoRepository<Ride, String> {

    public List<Ride> findByPickupNear(GeoJsonPoint p, Distance d);

}
