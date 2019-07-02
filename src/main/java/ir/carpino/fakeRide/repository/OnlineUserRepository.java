package ir.carpino.fakeRide.repository;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Expiry;
import com.github.benmanes.caffeine.cache.LoadingCache;
import ir.carpino.fakeRide.model.mqtt.User;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Repository
public class OnlineUserRepository {

    @Value("${fake-ride.online-user.inactive-time}")
    private int expireTime;

    private LoadingCache<String, User> cache;
    private static User shareUser;
    private static int onlineUser;

    @PostConstruct
    public void initCache() {
        cache = Caffeine.newBuilder()
                .expireAfterAccess(expireTime, TimeUnit.SECONDS)
//                .expireAfter(new Expiry<String, User>() {
//                    @Override
//                    public long expireAfterCreate(@NonNull String key, @NonNull User value, long currentTime) {
//                        --onlineUser;
//                        return 0;
//                    }
//
//                    @Override
//                    public long expireAfterUpdate(@NonNull String key, @NonNull User value, long currentTime, @NonNegative long currentDuration) {
//                        return 0;
//                    }
//
//                    @Override
//                    public long expireAfterRead(@NonNull String key, @NonNull User value, long currentTime, @NonNegative long currentDuration) {
//                        return 0;
//                    }
//                })
                .build(this::createUser);
    }

    public void aliveUser(User user) {
        shareUser = user;
        cache.get(user.getId());
    }

    private User createUser(String userId) {
        ++onlineUser;
        return shareUser;
    }

    public int onlineUserCount() {
        return onlineUser;
    }

    public Map<String, User> getOnlineUsers() {
        return cache.asMap();
    }
}
