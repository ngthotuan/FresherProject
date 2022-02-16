package redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

public class RedissonExample {
    public static void main(String[] args) throws InterruptedException {
        Config config = new Config();
        config
//                .useSingleServer()
//                .setAddress("redis://localhost:6379")
//                .setConnectionPoolSize(10)
//                .setConnectionMinimumIdleSize(5)
//                .setConnectTimeout(30000);
                .useClusterServers()
                .addNodeAddress("redis://localhost:6001", "redis://localhost:6002", "redis://localhost:6003",
                        "redis://localhost:6004", "redis://localhost:6005", "redis://localhost:6006")
                .setConnectTimeout(30000)
        ;

        RedissonClient client = Redisson.create(config);

        client.getBucket("test").set("123");
        System.out.println(client.getBucket("test").get());

        client.getMap("testMap").put("a", 1);
        client.getMap("testMap").put("b", 2);
        client.getMap("testMap").put("c", 3);
        System.out.println(client.getMap("testMap").keySet());
        System.out.println(client.getMap("testMap").values());

        client.getMapCache("testMapCache").put("a", 1, 2, TimeUnit.SECONDS);
        System.out.println(client.getMapCache("testMapCache").keySet());
        Thread.sleep(3000);
        System.out.println("After 2s: " + client.getMapCache("testMapCache").keySet());

        client.getAtomicLong("long").set(5);
        System.out.println(client.getAtomicLong("long").get());

    }
}
