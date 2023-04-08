package asap.be.controller;

import asap.be.service.NotificationServiceImpl;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class NotificationRestDocs {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private Gson gson;

	@MockBean
	private NotificationServiceImpl notificationService;

	@Test
	@DisplayName("SSE 연결 테스트")
	void connectionSSE() throws Exception {

		String lastEventId = "";

		given(notificationService.connection(anyString(), any())).willReturn(new SseEmitter());

		ResultActions actions =
				mockMvc.perform(
						RestDocumentationRequestBuilders.get("/api/connect")
								.header("Last-Event-ID", lastEventId)
								.accept(MediaType.TEXT_EVENT_STREAM)
				);

		actions.andExpect(status().isOk())
				.andDo(document(
						"SSE-connection"
				));
	}
}
