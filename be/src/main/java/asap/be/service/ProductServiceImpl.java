package asap.be.service;

import asap.be.domain.notification.NotificationType;
import asap.be.dto.*;
import asap.be.exception.BusinessLogicException;
import asap.be.exception.ExceptionCode;
import asap.be.repository.mybatis.ProductMybatisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static asap.be.config.CacheConstant.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	private final ReleaseService releaseService;
	private final NotificationService notificationService;
	private final ProductMybatisRepository productMybatisRepository;

	@Override
	@Transactional
	@CacheEvict(cacheNames = {MONTHLY_SUMMARY, SIX_VALUE, RANK_PRODUCT}, allEntries = true)
	public void insertOrUpdateStock(PostProductDto dto) {

		StringBuffer sb = new StringBuffer();
		dto.addDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))); // 실 배포시 적용하면 당일 총 입고량 올라감 히히

		productMybatisRepository.insertOrUpdateStock(dto);

		if (dto.getQuantity() != 0) {
			verifiedProductByName(dto.getPName(), dto.getWId());
			verifiedQuantity(dto);
		}

		productMybatisRepository.insertOrUpdateRelease(dto);

		EverythingDto everythingDto = releaseService.findStockByPNameAndWId(dto.getPName(), dto.getWId(), dto.getPCode());

		if (everythingDto != null && dto.getPInsert() != 0) {
			sb.append(dto.getPName()).append(" ").append("+").append(dto.getPInsert()).append(" 입고");
			notificationService.send("입고 알림!", sb.toString(), NotificationType.RECEIVE);
		}

		if (everythingDto != null && everythingDto.getCnt() >= dto.getQuantity() && dto.getQuantity() != 0) {
			sb.append(dto.getPName()).append(" ").append("-").append(dto.getQuantity()).append(" 출고");
			notificationService.send("출고 알림!", sb.toString(), NotificationType.RELEASE);
		}
	}

	@Override
	@Transactional
	public void updateProduct(EditProductDto dto) {
		verifiedProduct(dto.getPId(), dto.getSId());
		productMybatisRepository.updateProduct(dto);
	}

	@Override
	@Transactional(readOnly = true)
	public EverythingDto findById(Long pId, Long sId) {
		return productMybatisRepository.findById(pId, sId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EverythingDto> findByName(String pName) {
		return productMybatisRepository.findByName(pName);
	}

    @Override
    @Transactional(readOnly = true)
    public List<EverythingPageDto> findByAll(Integer lastId) {
        return productMybatisRepository.findByAll(lastId);
    }

	@Override
	public AllProductCntDto findAllCntByPName(String pName) {
		return productMybatisRepository.findAllCntByPName(pName);
	}

    @Override
    public DetailInfoDto detailPageUsingPId(Long pId) {
		DetailProductDto product = productMybatisRepository.findProductById(pId);
		List<DetailReleaseDto> release = productMybatisRepository.detailReleaseUsingPId(pId);
		List<DetailInsertDto> insert = productMybatisRepository.detailInsertUsingPId(pId);

		product = DetailProductDto.builder()
				.pId(product.getPId())
				.price(product.getPrice())
				.pStatus(product.getPStatus())
				.pCode(product.getPCode())
				.pName(product.getPName())
				.cnt(insert.get(0).getCnt())
				.warehouses(productMybatisRepository.findProductWarehouseById(product.getPId()))
				.build();

		List<DetailInsertLogsDto> insertLogs = new ArrayList<>();
		insertLogLoop(insert, insertLogs);

		return DetailInfoDto.builder()
				.product(product)
				.insertLogs(insertLogs)
				.releaseLogs(release)
				.build();
    }

	@Override
	public Long findPIdByPNameAndWId(String pName, Long wId) {
		return productMybatisRepository.findPIdByPNameAndWId(pName, wId);
	}

	@Override
	public DetailInfoDto editDetailPage(Long pId, EditProductDto dto) {
		productMybatisRepository.updateProduct(dto);

		return detailPageUsingPId(pId);
	}

	@Override
	public Long findByUUID(String uuid) {
		return productMybatisRepository.findByUUID(uuid);

	}

	@Override
	public void saveS3ImageUrl(String imageURL, Long pId) {
		productMybatisRepository.saveS3ImageUrl(imageURL, pId);
	}

	private void verifiedProduct(Long pId, Long sId) {
		if (productMybatisRepository.verifiedProduct(pId, sId))
			throw new BusinessLogicException(ExceptionCode.PRODUCT_NOT_EXISTS);
	}

	private void verifiedProductByName(String pName, Long wId) {
		if (productMybatisRepository.checkExistence(pName))
			throw new BusinessLogicException(ExceptionCode.PRODUCT_NOT_EXISTS);
	}

	private void verifiedQuantity(PostProductDto dto) {
		if (releaseService.getCnt(dto) < dto.getQuantity())
			throw new BusinessLogicException(ExceptionCode.OVER_QUANTITY_THAN_STOCK);
	}

	private static void insertLogLoop(List<DetailInsertDto> insert, List<DetailInsertLogsDto> insertLogs) {
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
	}
}
