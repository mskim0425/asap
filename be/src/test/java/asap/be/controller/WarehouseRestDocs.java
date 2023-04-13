package asap.be.controller;

import asap.be.domain.Warehouse;
import asap.be.service.WarehouseServiceImpl;
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
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static asap.be.utils.MainControllerConstants.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class WarehouseRestDocs {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private Gson gson;

	@MockBean
	private WarehouseServiceImpl warehouseService;

	@Test
	@DisplayName("창고 만들엉")
	void test1() throws Exception {

		String content = gson.toJson(WAREHOUSE_POST);

		doNothing().when(warehouseService).wSave(WAREHOUSE_POST);

		ResultActions actions =
				mockMvc.perform(
						RestDocumentationRequestBuilders.post("/api/warehouse")
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON)
								.content(content)
				);

		actions.andExpect(status().isOk())
				.andDo(document(
						"post-warehouse",
						requestFields(
								List.of(
										fieldWithPath("wname").type(JsonFieldType.STRING).description("창고명"),
										fieldWithPath("wloc").type(JsonFieldType.STRING).description("창고 위치")
								)
						),
						responseFields(
								List.of(
										fieldWithPath("[].wid").type(JsonFieldType.NUMBER).description("창고 식별자"),
										fieldWithPath("[].wname").type(JsonFieldType.STRING).description("창고명"),
										fieldWithPath("[].wloc").type(JsonFieldType.STRING).description("창고 위치")
								)
						)
				));
	}

	@Test
	@DisplayName("창고 지웡")
	void deleteWarehouse() throws Exception {
		Long wId = 1L;

		doNothing().when(warehouseService).wDelete(wId);

		ResultActions actions =
				mockMvc.perform(
						RestDocumentationRequestBuilders.delete("/api/warehouse/{w-id}", wId)
								.accept(MediaType.APPLICATION_JSON)
				);

		actions.andExpect(status().isOk()); // stock_table과 연관관계로 창고 삭제 불가...
	}

	@Test
	@DisplayName("창고 이름 바꿩")
	void patchWarehouseName() throws Exception {
		String content = gson.toJson(WAREHOUSE_PATCH);

		doNothing().when(warehouseService).wChange(WAREHOUSE_PATCH);

		ResultActions actions =
				mockMvc.perform(
						RestDocumentationRequestBuilders.patch("/api/warehouse")
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON)
								.content(content)
				);

		actions.andExpect(status().isOk())
				.andDo(document(
						"patch-warehouse-name",
						requestFields(
								List.of(
										fieldWithPath("newName").type(JsonFieldType.STRING).description("새로운 창고 이름"),
										fieldWithPath("oldName").type(JsonFieldType.STRING).description("원래 창고 이름")
								)
						),
						responseFields(
								List.of(
										fieldWithPath("[].wid").type(JsonFieldType.NUMBER).description("창고 식별자"),
										fieldWithPath("[].wname").type(JsonFieldType.STRING).description("바뀐 창고명"),
										fieldWithPath("[].wloc").type(JsonFieldType.STRING).description("창고 위치")
								)
						)
				));
	}

	@Test
	@DisplayName("창고 위치 바꿩")
	void patchWarehouseLoc() throws Exception {
		String content = gson.toJson(WAREHOUSE_PATCH_2);

		doNothing().when(warehouseService).wChange(WAREHOUSE_PATCH_2);

		ResultActions actions =
				mockMvc.perform(
						RestDocumentationRequestBuilders.patch("/api/warehouse")
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON)
								.content(content)
				);

		actions.andExpect(status().isOk())
				.andDo(document(
						"patch-warehouse-name",
						requestFields(
								List.of(
										fieldWithPath("newLoc").type(JsonFieldType.STRING).description("새로운 창고 위치"),
										fieldWithPath("oldLoc").type(JsonFieldType.STRING).description("원래 창고 위치"),
										fieldWithPath("oldName").type(JsonFieldType.STRING).description("원래 창고 이름")
								)
						),
						responseFields(
								List.of(
										fieldWithPath("[].wid").type(JsonFieldType.NUMBER).description("창고 식별자"),
										fieldWithPath("[].wname").type(JsonFieldType.STRING).description("바뀐 창고명"),
										fieldWithPath("[].wloc").type(JsonFieldType.STRING).description("창고 위치")
								)
						)
				));
	}

	@Test
	@DisplayName("창고이름으로 조회행")
	void getWarehouse() throws Exception {

		String wname = "BMW M5";

		given(warehouseService.findWarehouseByName(anyString())).willReturn(WAREHOUSE_LIST);

		ResultActions actions =
				mockMvc.perform(
						RestDocumentationRequestBuilders.get("/api/warehouse?wName={wName}", wname)
								.accept(MediaType.APPLICATION_JSON)
				);

		actions.andExpect(status().isOk())
				.andDo(document(
						"get-warehouse-by-name",
						responseFields(
								List.of(
										fieldWithPath("[].wid").type(JsonFieldType.NUMBER).description("창고 식별자"),
										fieldWithPath("[].wname").type(JsonFieldType.STRING).description("창고명"),
										fieldWithPath("[].wloc").type(JsonFieldType.STRING).description("창고 위치")
								)
						)
				));
	}

	@Test
	@DisplayName("창고 위치로 조회행")
	void getWarehouseByLoc() throws Exception {

		String wLoc = "Nicaragua";

		given(warehouseService.findWarehouseByLoc(anyString())).willReturn(WAREHOUSE_LIST);

		ResultActions actions =
				mockMvc.perform(
						RestDocumentationRequestBuilders.get("/api/warehouse?wLoc={wLoc}", wLoc)
								.accept(MediaType.APPLICATION_JSON)
				);

		actions.andExpect(status().isOk())
				.andDo(document(
						"get-warehouse-by-loc",
						responseFields(
								List.of(
										fieldWithPath("[].wid").type(JsonFieldType.NUMBER).description("창고 식별자"),
										fieldWithPath("[].wname").type(JsonFieldType.STRING).description("창고명"),
										fieldWithPath("[].wloc").type(JsonFieldType.STRING).description("창고 위치")
								)
						)
				));

	}
}
