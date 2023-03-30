package asap.be.config;

import asap.be.repository.mybatis.ProductMapper;
import asap.be.repository.mybatis.ProductMybatisRepository;
import asap.be.repository.mybatis.ReleaseMapper;
import asap.be.repository.mybatis.ReleaseMybatisRepository;
import asap.be.repository.mybatis.WarehouseMapper;
import asap.be.repository.mybatis.WarehouseMybatisRepository;
import asap.be.service.ProductService;
import asap.be.service.ProductServiceImpl;
import asap.be.service.ReleaseService;
import asap.be.service.ReleaseServiceImpl;
import asap.be.service.WarehouseService;
import asap.be.service.WarehouseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MybatisConfig {
	private final ProductMapper productMapper;
	private final WarehouseMapper warehouseMapper;
	private final ReleaseMapper releaseMapper;

	@Bean
	public ProductService productService() {
		return new ProductServiceImpl(productRepository());
	}

	@Bean
	public ReleaseService releaseService() {
		return new ReleaseServiceImpl(releaseRepository());
	}

	@Bean
	public WarehouseService warehouseService() {
		return new WarehouseServiceImpl(warehouseRepository());
	}

	@Bean
	public ProductMybatisRepository productRepository() {
		return new ProductMybatisRepository(productMapper);
	}

	@Bean
	public WarehouseMybatisRepository warehouseRepository() {
		return new WarehouseMybatisRepository(warehouseMapper);
	}

	@Bean
	public ReleaseMybatisRepository releaseRepository() {
		return new ReleaseMybatisRepository(releaseMapper);
	}
}
