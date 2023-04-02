package asap.be.controller;

import asap.be.aop.ForUpdate;
import asap.be.domain.Product;
import asap.be.dto.EverythingDto;
import asap.be.dto.ProductUpdateDto;
import asap.be.service.DashBoardService;
import asap.be.service.NotificationService;
import asap.be.service.ProductService;
import asap.be.service.ReleaseService;
import asap.be.service.WarehouseService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainController {
	private final ProductService productService;
	private final ReleaseService releaseService;
	private final WarehouseService warehouseService;
	private final DashBoardService dashBoardService;
	private final NotificationService notificationService;

	@PostMapping("/prod")
	public ResponseEntity<EverythingDto> addProduct(@RequestBody EverythingDto everythingDto) { //TODO: request 수정
		productService.save(everythingDto);
		releaseService.sSave(everythingDto);

		return new ResponseEntity<>(productService.findById(everythingDto.getPId()), HttpStatus.OK);
	}

	@PatchMapping("/prod/{p-id}")
	public ResponseEntity<List<EverythingDto>> deleteProduct(@PathVariable("p-id") Long pId) {

		ProductUpdateDto.UpdatePStatus build = ProductUpdateDto.UpdatePStatus.builder().pStatus(0).pId(pId).build();
		productService.delete(build);

		return new ResponseEntity<>(productService.findByAll(), HttpStatus.OK);
	}

	@ForUpdate
	@PatchMapping("/prod")
	public ResponseEntity<EverythingDto> updateProductName(Product product) {

//		productService.name(updatePName);

		return new ResponseEntity<>( HttpStatus.OK);
	}

	@GetMapping("/date/{p-id}")
	public ResponseEntity getProductCntByDate(@PathVariable("p-id") Long pId) {

		return new ResponseEntity<>(dashBoardService.CntProduct(pId), HttpStatus.OK);
	}

	@GetMapping("/temp")
	public ResponseEntity getTop10() {

		return new ResponseEntity<>(dashBoardService.ProductCntRank(), HttpStatus.OK);
	}
//
//
//	@PatchMapping("/prod")
//	public ResponseEntity<EverythingDto> updateProductName(@RequestBody RequestDto.UpdatePName updatePName) {
//		productService.name(updatePName);
//
//		return new ResponseEntity<>(productService.findById(updatePName.getPId()), HttpStatus.OK);
//	}
//
//	@PatchMapping("/prod")
//	public ResponseEntity<EverythingDto> updateProductPrice(@RequestBody RequestDto.UpdatePrice updatePrice) {
//		productService.price(updatePrice);
//
//		return new ResponseEntity<>(productService.findById(updatePrice.getPId()), HttpStatus.OK);
//	}
//
//	@PatchMapping("/prod")
//	public ResponseEntity<EverythingDto> updateProductPCode(@RequestBody RequestDto.UpdatePCode updatePCode) {
//		productService.barcode(updatePCode);
//
//		return new ResponseEntity<>(productService.findById(updatePCode.getPId()), HttpStatus.OK);
//	}

	/**
	 * SSE 통신
	 */
	@GetMapping(value = "/connect", produces = "text/event-stream")
	public SseEmitter sseConnection(@RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId) {
		return notificationService.connection(lastEventId);
	}
}
