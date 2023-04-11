package asap.be.facade;

import asap.be.dto.EditProductDto;
import asap.be.dto.PostProductDto;
import asap.be.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;


@Component
@Slf4j
@RequiredArgsConstructor
public class RedissonLockServiceFacade {
    private final RedissonClient redissonClient;
    private final ProductServiceImpl productService;

    public void save(PostProductDto dto) {
        productService.insertOrUpdateStock(dto);
    }

    public void release(String key, PostProductDto dto) {
        RLock lock = redissonClient.getLock(key);

        try {
            boolean available = lock.tryLock(5, 1, TimeUnit.SECONDS);

            if (!available) {
                log.info("{} lock 획득 실패", key);
                return;
            }
            productService.insertOrUpdateStock(dto);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();

        }
    }

}
