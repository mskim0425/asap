package asap.be.facade;

import asap.be.dto.PostProductDto;
import asap.be.service.ProductServiceImpl;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@RequiredArgsConstructor
public class RedissonLockServiceFacade {
    private final RedissonClient redissonClient;
    private final ProductServiceImpl productService;

    public void save(PostProductDto dto, HttpSession session) throws IOException, WriterException {
        productService.insertOrUpdateStock(dto, session);
    }

    public void release(String key, PostProductDto dto, HttpSession session) {
        RLock lock = redissonClient.getLock(key);

        try {
            boolean available = lock.tryLock(5, 1, TimeUnit.SECONDS);

            if (!available) {
                log.info("{} lock 획득 실패", key);
                return;
            }
            productService.insertOrUpdateStock(dto, session);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();

        }
    }

}
