package asap.be.repository;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.Map;

public interface EmitterRepository {
	SseEmitter save(String id, SseEmitter emitter);

	void saveEventCache(String id, Object event);

	Map<String, SseEmitter> findAllStartById(String id);

	void deleteAllStartByWithId(String id);

	List<SseEmitter> getAll();

}
