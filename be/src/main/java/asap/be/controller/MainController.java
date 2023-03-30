package asap.be.controller;

import asap.be.dto.ProductDto;
import asap.be.dto.RequestDto;
import asap.be.service.ProductService;
import asap.be.service.ReleaseService;
import asap.be.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto) {
		productService.save(productDto);
		releaseService.sSave(productDto);
		warehouseService.wSave(productDto);

		return new ResponseEntity<>(productService.findById(productDto.getPId()), HttpStatus.OK);
	}

	@DeleteMapping("/prod/{p-id}")
	public ResponseEntity<List<ProductDto>> deleteProduct(@PathVariable("p-id") Long pId) {
		System.out.println(pId);
		productService.delete(pId);

		return new ResponseEntity<>(productService.findByAll(), HttpStatus.OK);
	}
//
//	@PatchMapping("/prod")
//	public ResponseEntity<ProductDto> updateProductName(@RequestBody RequestDto.UpdatePName updatePName) {
//		productService.name(updatePName);
//
//		return new ResponseEntity<>(productService.findById(updatePName.getPId()), HttpStatus.OK);
//	}
//
//	@PatchMapping("/prod")
//	public ResponseEntity<ProductDto> updateProductPrice(@RequestBody RequestDto.UpdatePrice updatePrice) {
//		productService.price(updatePrice);
//
//		return new ResponseEntity<>(productService.findById(updatePrice.getPId()), HttpStatus.OK);
//	}
//
//	@PatchMapping("/prod")
//	public ResponseEntity<ProductDto> updateProductPCode(@RequestBody RequestDto.UpdatePCode updatePCode) {
//		productService.barcode(updatePCode);
//
//		return new ResponseEntity<>(productService.findById(updatePCode.getPId()), HttpStatus.OK);
//	}
}
