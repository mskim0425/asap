package asap.be.repository.mybatis;

import asap.be.dto.*;
import asap.be.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductMybatisRepository implements ProductRepository {

	private final ProductMapper productMapper;

	@Override
	@Transactional
	public void insertOrUpdateStock(PostProductDto dto) {
		productMapper.insertOrUpdateStock(dto);
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
    public AllProductCntDto findAllCntByPId(Long pId) {
        return productMapper.findAllCntByPId(pId);
    }

    @Override
    public List<DetailInfoDto> detailPageUsingPId(Long pId) {
        return productMapper.detailPageUsingPId(pId);
    }

}
