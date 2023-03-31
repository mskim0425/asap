package asap.be.controller;

import asap.be.aop.ForUpdate;
import asap.be.domain.Product;
import asap.be.dto.EverythingDto;
import asap.be.dto.RequestDto;
import asap.be.service.ProductService;
import asap.be.service.ReleaseService;
import asap.be.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainController {
	private final ProductService productService;
	private final ReleaseService releaseService;
	private final WarehouseService warehouseService;

	@PostMapping("/prod")
	public ResponseEntity<EverythingDto> addProduct(@RequestBody EverythingDto everythingDto) {
		productService.save(everythingDto);
		releaseService.sSave(everythingDto);
		warehouseService.wSave(everythingDto);

		return new ResponseEntity<>(productService.findById(everythingDto.getPId()), HttpStatus.OK);
	}

	@PatchMapping("/prod/{p-id}")
	public ResponseEntity<List<EverythingDto>> deleteProduct(@PathVariable("p-id") Long pId) {

		RequestDto.UpdatePStatus build = RequestDto.UpdatePStatus.builder().pStatus(0).pId(pId).build();
		productService.delete(build);

		return new ResponseEntity<>(productService.findByAll(), HttpStatus.OK);
	}

	@ForUpdate
	@PatchMapping("/prod")
	public ResponseEntity<EverythingDto> updateProductName(Product product) {

//		productService.name(updatePName);

		return new ResponseEntity<>( HttpStatus.OK);
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
}
