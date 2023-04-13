package asap.be.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;

public interface EmitterRepository {
	SseEmitter save(String id, SseEmitter emitter);

	void saveEventCache(String id, Object event);

	Map<String, SseEmitter> findAllStartById(String id);

	void deleteAllStartByWithId(String id);

}
