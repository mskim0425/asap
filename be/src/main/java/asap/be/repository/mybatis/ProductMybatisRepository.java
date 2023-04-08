package asap.be.repository.mybatis;

import asap.be.dto.*;
import asap.be.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductMybatisRepository implements ProductRepository {

	private final ProductMapper productMapper;

	@Override
	@Transactional
	public void insertOrUpdateStock(PostProductDto dto) {
		String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		productMapper.insertOrUpdateStock(dto,today);
	}

	@Override
	public void insertOrUpdateRelease(PostProductDto dto) {
		productMapper.insertOrUpdateRelease(dto);
	}

	@Override
	public void updateProduct(EditProductDto dto) {
		productMapper.updateProduct(dto);
	}

	@Override
	public EverythingDto findById(Long pId, Long sId) {
		return productMapper.findById(pId, sId);
	}

	@Override
	public List<EverythingDto> findByName(String pName) {
		return productMapper.findByName(pName);
	}

    @Override
    public List<EverythingPageDto> findByAll(Integer lastId) {
        return productMapper.findByAll(lastId);
    }

    @Override
    public AllProductCntDto findAllCntByPName(String pName) {
        return productMapper.findAllCntByPName(pName);
    }

    @Override
    public List<DetailInfoDto> detailPageUsingPId(Long pId) {
        return productMapper.detailPageUsingPId(pId);
    }

	@Override
	public Boolean verifiedProduct(Long pId, Long sId) {
		return productMapper.verifiedProduct(pId, sId);
	}

	@Override
	public Boolean checkExistence(String pName) {
		return productMapper.checkExistence(pName);
	}

}
