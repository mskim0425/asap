package asap.be.repository.mybatis;

import asap.be.repository.EmitterRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class EmitterMyBatisRepository implements EmitterRepository {
	public final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
	public final Map<String, Object> eventCache = new ConcurrentHashMap<>();

	public SseEmitter save(String id, SseEmitter sseEmitter) {
		emitters.put(id, sseEmitter);
		return sseEmitter;
	}

	public void saveEventCache(String id, Object event) {
		eventCache.put(id, event);
	}

	@Override
	public Map<String, SseEmitter> findAllStartById(String id) {
		return emitters.entrySet().stream()
				.filter(entry -> entry.getKey().startsWith(id))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	public void deleteAllStartByWithId(String id) {
		emitters.forEach((key, emitter) -> {
			if (key.startsWith(id)) {
				emitters.remove(key);
			}
		});
	}
}
