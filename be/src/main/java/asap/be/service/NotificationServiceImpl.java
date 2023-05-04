package asap.be.service;

import asap.be.domain.notification.Notification;
import asap.be.domain.notification.NotificationType;
import asap.be.repository.EmitterRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
	private static final Logger log = LoggerFactory.getLogger(NotificationService.class);
	private static final Long DEFAULT_TIMEOUT = 120L * 1000 * 60; // SSE 유효시간
	private final EmitterRepository emitterRepository;

	/**
	 * SSE 통신
	 */
	@Override
	public SseEmitter connection(String lastEventId, HttpServletResponse response) {

		String userid = "user"; // 고정 유저 아이디. 원래는 유저 별 고유 아이디여야 하지만 로그인이 없으니,,
		String id = userid + "_" + System.currentTimeMillis(); // 데이터 유실 시점 파악 위함

		// 클라이언트의 sse 연결 요청에 응답하기 위한 SseEmitter 객체 생성
		// 유효시간 지정으로 시간이 지나면 클라이언트에서 자동으로 재연결 요청함
		SseEmitter emitter = emitterRepository.save(id, new SseEmitter(DEFAULT_TIMEOUT));
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Connection", "keep-alive");
		response.setHeader("Content-Type", "text/event-stream");
		response.setHeader("X-Accel-Buffering", "no"); // NGINX PROXY 에서의 필요설정
		response.setCharacterEncoding("UTF-8");

		// SseEmitter 의 완료/시간초과/에러로 인한 전송 불가 시 sseEmitter 삭제
		emitter.onCompletion(() -> emitterRepository.deleteAllStartByWithId(id));
		emitter.onTimeout(() -> emitterRepository.deleteAllStartByWithId(id));
		emitter.onError((e) -> emitterRepository.deleteAllStartByWithId(id));

		// 연결 직후, 데이터 전송이 없을 시 503 에러 발생. 에러 방지 위한 더미데이터 전송
		send("SSE Connection check", "Connected. " + id, NotificationType.CONNECTION_CHECK);

		if (!lastEventId.isEmpty()) { // 클라이언트가 미수신한 Event 유실 예방, 연결이 끊켰거나 미수신된 데이터를 다 찾아서 보내준다.
			Map<String, SseEmitter> events = emitterRepository.findAllStartById(userid);
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
//			log.error("SSE 연결 오류 발생! 오류났으니까 다 지울꺼임~", e);
		}
	}

	@Override
	@Transactional
	// 알림 보낼 로직에 send 메서드 호출하면 됨
	public void send(String title, String content, NotificationType notificationType) {

		String id = "user";
		Notification notification = createNotification(id, title, content, notificationType);

		// 유저의 모든 SseEmitter 가져옴
		Map<String, SseEmitter> sseEmitters = emitterRepository.findAllStartById(id);
		sseEmitters.forEach(
				(key, emitter) -> {
					// 데이터 캐시 저장 (유실된 데이터 처리 위함)
					emitterRepository.saveEventCache(key, notification);
					// 데이터 전송
					sendToClient(emitter, key, notification);
				}
		);
	}

	private Notification createNotification(String id, String title, String content, NotificationType notificationType) {

		return Notification.builder()
				.userId(id)
				.title(title)
				.content(content)
				.notificationType(notificationType)
				.build();
	}
}
