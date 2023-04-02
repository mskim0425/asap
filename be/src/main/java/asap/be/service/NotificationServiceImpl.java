package asap.be.service;

import asap.be.domain.notification.Notification;
import asap.be.domain.notification.NotificationType;
import asap.be.repository.EmitterRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService{
	private static final Logger log = LoggerFactory.getLogger(NotificationService.class);
	private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;
	private final EmitterRepository emitterRepository;

	// SSE 통신
	@Override
	public SseEmitter connection(String lastEventId) {

		String id = "user";
		SseEmitter emitter = emitterRepository.save(id, new SseEmitter(DEFAULT_TIMEOUT));

		emitter.onCompletion(() -> emitterRepository.deleteAllStartByWithId(id));
		emitter.onTimeout(() -> emitterRepository.deleteAllStartByWithId(id));
		emitter.onError((e) -> emitterRepository.deleteAllStartByWithId(id));

		// 503 방지 위한 첫 더미데이터 전송
		sendToClient(emitter, id, "연결되었습니다. " + id + "님");

		// 미수신 Event 목록 존재 시 전송으로 Event 유실 방지
		if (!lastEventId.isEmpty()) {
			Map<String, SseEmitter> events = emitterRepository.findAllStartById(id);
			events.entrySet().stream()
					.filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
					.forEach(entry -> sendToClient(emitter, entry.getKey(), entry.getValue()));
		}

		return emitter;
	}

	@Override
	public void sendToClient(SseEmitter emitter, String id, Object data) {

		try {
			emitter.send(SseEmitter.event()
					.id(id)
					.name("sse")
					.data(data));
		} catch (IOException e) {
			emitterRepository.deleteAllStartByWithId(id);
			log.error("SSE 연결 오류 발생! 오류났으니까 다 지울꺼임~", e);
		}
	}

	@Override
	public void send(String title, String content, NotificationType notificationType) {

		Notification notification = Notification.builder()
				.title(title)
				.content(content)
				.notificationType(notificationType)
				.build();

	}
}
