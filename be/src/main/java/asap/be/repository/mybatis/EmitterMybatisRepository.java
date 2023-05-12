package asap.be.repository.mybatis;

import asap.be.repository.EmitterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class EmitterMybatisRepository implements EmitterRepository {
	private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
	private final Map<String, Object> eventCache = new ConcurrentHashMap<>();

	@Override
	public SseEmitter save(String id, SseEmitter sseEmitter) {
		emitters.put(id, sseEmitter);
		return sseEmitter;
	}

	@Override
	public void saveEventCache(String id, Object event) {
		eventCache.put(id, event);
	}

	@Override
	public Map<String, SseEmitter> findAllStartById(String id) {
		return emitters.entrySet().stream()
				.filter(entry -> entry.getKey().startsWith(id))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	@Override
	public void deleteAllStartByWithId(String id) {
		emitters.forEach((key, emitter) -> {
			if (key.startsWith(id)) emitters.remove(key);
		});
	}

	@Override
	public List<SseEmitter> getAll() {
		List<SseEmitter> list = new CopyOnWriteArrayList<>();//동시성을 보장하는 ArrayList
		for (SseEmitter se : emitters.values()) {
			list.add(se);
		}
		return list;
	}

}


