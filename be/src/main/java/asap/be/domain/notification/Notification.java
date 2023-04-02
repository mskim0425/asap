package asap.be.domain.notification;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification {
	private Long id;
	private Long userId;
	private String title; // 알림 대표 제목(?)
	private String content; // 알림 내용
	private NotificationType notificationType;
}
