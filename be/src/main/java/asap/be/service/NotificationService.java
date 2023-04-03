package asap.be.service;

import asap.be.domain.notification.NotificationType;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface NotificationService {
	SseEmitter connection(String lastEventId); // SseEmitter : Spring framework 지원 SSE 통신 지원 클래스

	void sendToClient(SseEmitter emitter, String id, Object data);

	void send(String title, String content, NotificationType notificationType);

}
