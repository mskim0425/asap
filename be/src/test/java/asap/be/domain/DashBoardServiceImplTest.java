package asap.be.domain;

import asap.be.dto.CountryDto;
import asap.be.dto.ProductCntDto;
import asap.be.dto.DashboardDto.RankDto;
import asap.be.dto.MoneyDto;
import asap.be.dto.PostProductDto;
import asap.be.dto.YearStatusDto;
import asap.be.repository.mybatis.ProductMybatisRepository;
import asap.be.repository.mybatis.ReleaseMybatisRepository;
import asap.be.repository.mybatis.WarehouseMybatisRepository;
import asap.be.service.DashBoardService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
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

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Slf4j
@Transactional
@SpringBootTest
class DashBoardServiceImplTest {

	@Autowired
	ProductMybatisRepository productMybatisRepository;
	@Autowired
	ReleaseMybatisRepository releaseMybatisRepository;
	@Autowired
	WarehouseMybatisRepository warehouseMybatisRepository;
	@Autowired
	DashBoardService dashBoardService;
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
	@DisplayName("날짜와 함께 조회되는지")
	void first_display() { //구글 차트에있는 첫번쨰 항목
		List<MoneyDto> moneyDtos = dashBoardService.TotalProductAmount("2021-12-30", "2022-01-15");
		for (MoneyDto dto : moneyDtos) {
			log.info(dto.getReleaseat() + " " + dto.getMoney());
		}

		Assertions.assertThat(moneyDtos.size()).isEqualTo(17);
	}

	@Test
	@DisplayName("날짜, 입고량, 출고량 조회")
	void cntProduct() {
		// given
		String pName = "Meat";
		Long pId = 2L;

		// when
		List<ProductCntDto> list = dashBoardService.CntProduct(pName);

		for (ProductCntDto dto : list)
			log.info(dto.getDate() + " " + dto.getInsertCnt() + " " + dto.getReleaseCnt());

		// then
		assertThat(list.size()).isEqualTo(21);
	}

	@Test
	@DisplayName("일별 입고량/출고량 TOP 10")
	void top10() {
		RankDto rankDto = dashBoardService.ProductCntRank();
	}


	@DisplayName("연도 주입시 1월-12월까지 출력여부")
	void sec_display() {
		List<YearStatusDto> monthlyStockSummary = dashBoardService.getMonthlyStockSummary("2023");
		for (YearStatusDto dto : monthlyStockSummary) {
			log.info("{}월 재고량: {} | 총 입고량: {} | 총 출고량: {}", dto.getMonth(), dto.getAllQuantity(), dto.getAllInsert(), dto.getAllReleaseAt());
		}

		Assertions.assertThat(monthlyStockSummary.size()).isEqualTo(12);
	}

	@Test
	@DisplayName("모든 나라별 상품 개수 구하기")
	void third_display() {
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
				.wId(1L)
				.pInsert(10)
				.build();

		//when
		productMybatisRepository.insertOrUpdateStock(productDto); // 상품 최초 저장 및 입고 시 원큐에 끝나여

		List<CountryDto> countryProductStauts = dashBoardService.getCountryProductStatus();
		for (CountryDto dto : countryProductStauts) {
			log.info("{} 에 있는 창고에 {}", dto.getCountryName(), dto.getProductCnt());
		}

		Assertions.assertThat(countryProductStauts.contains("Mozambique"));
	}



}