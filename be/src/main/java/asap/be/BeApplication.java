package asap.be;

import asap.be.config.MybatisConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Import(MybatisConfig.class) // 마이바티스 설정 사용
@EnableCaching // 캐싱 기능 활성화
@SpringBootApplication
@EnableRedisHttpSession
public class BeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeApplication.class, args);
	}


}
