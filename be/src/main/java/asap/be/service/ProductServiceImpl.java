package asap.be.service;

import asap.be.domain.notification.NotificationType;
import asap.be.dto.EditProductDto;
import asap.be.dto.EverythingDto;
import asap.be.dto.PostProductDto;
import asap.be.repository.mybatis.ProductMybatisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ReleaseService releaseService;
    private final NotificationService notificationService;
    private final ProductMybatisRepository productMybatisRepository;

    @Override
    @Transactional
    public void insertOrUpdateStock(PostProductDto dto) {
        productMybatisRepository.insertOrUpdateStock(dto);

        EverythingDto everythingDto = releaseService.findStockByPNameAndWId(dto.getPName(), dto.getWId());
        StringBuffer sb = new StringBuffer();

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
    public List<EverythingDto> findByAll() {
        return productMybatisRepository.findByAll();
    }

}
