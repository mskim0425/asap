package asap.be.domain;

import asap.be.dto.DetailInfoDto;
import asap.be.dto.DetailInsertDto;
import asap.be.dto.DetailInsertLogsDto;
import asap.be.dto.DetailProductDto;
import asap.be.dto.DetailReleaseDto;
import asap.be.dto.EditProductDto;
import asap.be.dto.EverythingDto;
import asap.be.dto.EverythingPageDto;
import asap.be.dto.PostProductDto;
import asap.be.repository.mybatis.ProductMybatisRepository;
import asap.be.repository.mybatis.ReleaseMybatisRepository;
import asap.be.repository.mybatis.WarehouseMybatisRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@Transactional
@SpringBootTest
@Slf4j
class ProductRepositoryTest {

	@Autowired
	ProductMybatisRepository productMybatisRepository;
	@Autowired
	ReleaseMybatisRepository releaseMybatisRepository;
	@Autowired
	WarehouseMybatisRepository warehouseMybatisRepository;
	@Autowired
	PlatformTransactionManager transactionManager;
	TransactionStatus status;

	@BeforeEach
	void beforeEach() {
		//트랜잭션 시작
		status = transactionManager.getTransaction(new DefaultTransactionDefinition());
	}

	@AfterEach
	void afterEach() {
		//트랜잭션 롤백
		transactionManager.rollback(status);
	}

	@Test
	@DisplayName("상품 저장 및 입고/출고")
	void save() {
		//given
		Product product = Product.builder()
				.pName("TEST PRODUCT NAME")
				.price(10000)
				.pCode("4534554533")
				.build();

		PostProductDto productDto = PostProductDto.builder()
				.pName(product.getPName())
				.price(product.getPrice())
				.pCode(product.getPCode())
				.wId(2L)
				.pInsert(10) //입고시
//				.quantity(100) //출고시
				.build();

		PostProductDto notExist = PostProductDto.builder()
				.pName("새로운 상품")
				.price(100000)
				.pCode("testing code").wId(5L).pInsert(10000).build();

		PostProductDto same = PostProductDto.builder()
				.pName("새로운 상품")
				.price(100000)
				.pCode("testing code").wId(5L).pInsert(10000).build();


		//when
		productMybatisRepository.insertOrUpdateStock(notExist);
		EverythingDto lastProd = releaseMybatisRepository.findStockByPNameAndWId(notExist.getpName(), notExist.getwId(), notExist.getpCode()); //만개가 저장되고

		productMybatisRepository.insertOrUpdateStock(same); // 상품 최초 저장 및 입고/출고 시 원큐에 끝나여
		EverythingDto sameProd = releaseMybatisRepository.findStockByPNameAndWId(same.getpName(), same.getwId(), same.getpCode()); //만개가 한번더 저장되고

		//then
		assertThat(lastProd.getPname()).isEqualTo(notExist.getpName()); //입고저장
		assertThat(sameProd.getCnt()).isEqualTo(lastProd.getCnt() + same.getpInsert()); //재고끼리 더하기가 됐는지
//		assertThat(findProd.getCnt()).isEqualTo(productDto.getCnt()+10);
	}

	@Test
	@DisplayName("삭제 및 상품 정보 변경")
	void editProduct() {

		// given
		Product product = Product.builder()
				.pName("상품명")
				.price(10000)
				.pCode("상품바코드")
				.build();

		PostProductDto productDto = PostProductDto.builder()
				.pName(product.getPName())
				.price(product.getPrice())
				.pCode(product.getPCode())
				.wId(1L)
				.pInsert(10) //입고시
				.build();
		productMybatisRepository.insertOrUpdateStock(productDto);

		Long pId = productMybatisRepository.findPIdByPNameAndWId(productDto.getpName(), productDto.getwId());
		Long sId = productMybatisRepository.findSIdByPNameAndWId(productDto.getpName(), productDto.getwId());

		EditProductDto name = EditProductDto.builder()
				.pId(pId)
				.sId(sId)
				.pName("새로 변경할 상품명")
				.build();

		// 이름 변경
		// when
		productMybatisRepository.updateProduct(name);

		// then
		EverythingDto findProd1 = productMybatisRepository.findById(name.getPId(), name.getSId());
		assertThat(findProd1.getPname()).isEqualTo(name.getPName());

		// 가격 변경
		// when
		EditProductDto price = EditProductDto.builder()
				.pId(pId)
				.sId(sId)
				.price(5000)
				.build();

		productMybatisRepository.updateProduct(price);

		// then
		EverythingDto findProd2 = productMybatisRepository.findById(price.getPId(), price.getSId());
		assertThat(price.getPrice()).isEqualTo(findProd2.getPrice());

		// 바코드 변경
		// when
		EditProductDto barcode = EditProductDto.builder()
				.pId(pId)
				.sId(sId)
				.pCode("NEW BARCODE")
				.build();

		productMybatisRepository.updateProduct(barcode);

		// then
		EverythingDto findProd3 = productMybatisRepository.findById(barcode.getPId(), barcode.getSId());
		assertThat(barcode.getPCode()).isEqualTo(findProd3.getPcode());

		// 전체 변경
		// when
		EditProductDto all = EditProductDto.builder()
				.pId(pId)
				.sId(sId)
				.pName("또다른 상품명")
				.price(10)
				.pCode("또다른 바코드")
				.build();

		productMybatisRepository.updateProduct(all);

		// then
		EverythingDto findProd4 = productMybatisRepository.findById(all.getPId(), all.getSId());
		assertThat(findProd4.getPname()).isEqualTo(all.getPName());
		assertThat(findProd4.getPrice()).isEqualTo(all.getPrice());
		assertThat(findProd4.getPcode()).isEqualTo(all.getPCode());

		// 상태 삭제로 변경
		// when
		EditProductDto delete = EditProductDto.builder()
				.pId(pId)
				.sId(sId)
				.pStatus(0)
				.build();

		productMybatisRepository.updateProduct(delete);

		// then
		EverythingDto findProd5 = productMybatisRepository.findById(delete.getPId(), delete.getSId());
		assertThat(findProd5.getPStatus()).isEqualTo(delete.getPStatus());
	}

	@Test
	@DisplayName("아이디로 찾기")
	void findById() {
		// given
		Long pId = 5L;
		Long sId = 5L;

		// when
		EverythingDto everythingDto = productMybatisRepository.findById(pId, sId);

		// then
		assertThat(everythingDto.getPId()).isEqualTo(pId);
	}

	@Test
	@DisplayName("이름으로 찾기")
	void findByName() {
		// given
		String pName1 = "asd";
		String pName2 = "Fish";

		// when
		List<EverythingDto> wrong = productMybatisRepository.findByName(pName1);
		List<EverythingDto> right = productMybatisRepository.findByName(pName2);

		// then
		assertThat(wrong).isNullOrEmpty();
		assertThat(right).isNotNull();
	}

	@Test
	@DisplayName("전부 찾기")
	void findAll() {
		// given

		Integer lastId = null; //10-20


		List<EverythingPageDto> list = productMybatisRepository.findByAll(lastId);
		Integer nextData = list.get(0).getLastid();
		List<EverythingPageDto> byAll = productMybatisRepository.findByAll(nextData);
		// then
		assertThat(list.size()).isEqualTo(10);
		assertThat(byAll.size()).isEqualTo(10);
	}

	@Test
	@DisplayName("상세 찾기")
	void findOne() {
		// given
		Long id = 3L;

		DetailProductDto product = productMybatisRepository.findProductById(id);
		List<DetailReleaseDto> release = productMybatisRepository.detailReleaseUsingPId(id);
		List<DetailInsertDto> insert = productMybatisRepository.detailInsertUsingPId(id);

		product = DetailProductDto.builder()
				.pId(product.getPId())
				.price(product.getPrice())
				.pStatus(product.getPStatus())
				.pCode(product.getPCode())
				.pName(product.getPName())
				.cnt(insert.get(0).getCnt())
				.build();

		List<DetailInsertLogsDto> insertLogs = new ArrayList<>();

		for (DetailInsertDto insertDto : insert) {
			String[] strings = insertDto.getPInsertLog().split(",");
			for (String string : strings) {
				String[] split = string.split(" : ");
				insertLogs.add(DetailInsertLogsDto.builder()
						.wName(insertDto.getWName())
						.wLoc(insertDto.getWLoc())
						.receiveIn(split[0])
						.pInsert(Integer.parseInt(split[1]))
						.build());
			}
		}

		DetailInfoDto dtos = DetailInfoDto.builder()
				.product(product)
				.insertLogs(insertLogs)
				.releaseLogs(release)
				.build();

		// then
		assertThat(dtos.getProduct().getPId()).isEqualTo(3L);
	}

}

