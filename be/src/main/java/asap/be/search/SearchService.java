package asap.be.search;

import asap.be.dto.EverythingPageDto;
import asap.be.repository.mybatis.ProductMybatisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final ProductMybatisRepository productMybatisRepository;

    public List<EverythingPageDto> search(Integer lastId, String pName, String order) {
        return productMybatisRepository.search(lastId,pName, order);
    }
}
