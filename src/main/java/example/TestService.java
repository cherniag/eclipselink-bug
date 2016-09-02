package example;

import example.dao.UserRepository;
import example.domain.User;
import example.domain.UserTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class TestService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;


    @PostConstruct
    void init() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch latch = new CountDownLatch(1);
        for (long i = 0; i < 4; i++) {
            executorService.submit(getTask(latch, i));
        }
        latch.countDown();
    }

    @PreDestroy
    void destroy() {
        userRepository.deleteAll();
    }

    private Runnable getTask(CountDownLatch latch, long id) {
        return () -> {
            User user = new User();
            user.setId(id);
            user.getTags().add(tag("k1", "v1"));
            user.getTags().add(tag("k2", "v2"));
            user.getTags().add(tag("k3", "v3"));
            try {
                latch.await();
            } catch (InterruptedException e) {
                logger.error(e.getMessage(), e);
            }
            logger.info("Save user {}",user);
            try {
                userRepository.save(user);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        };
    }

    private UserTag tag(String key, String value) {
        UserTag userTag = new UserTag();
        userTag.setKey(key);
        userTag.setValue(value);
        return userTag;
    }


}
