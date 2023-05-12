package asap.be.config;

import asap.be.qrcode.QrcodeGeneratorService;
import asap.be.qrcode.S3UploadService;
import asap.be.repository.EmitterRepository;
import asap.be.repository.MemberRepository;
import asap.be.repository.mybatis.EmitterMybatisRepository;
import asap.be.repository.mybatis.MemberMapper;
import asap.be.repository.mybatis.MemberMybatisRepository;
import asap.be.repository.mybatis.ProductMapper;
import asap.be.repository.mybatis.ProductMybatisRepository;
import asap.be.repository.mybatis.ReleaseMapper;
import asap.be.repository.mybatis.ReleaseMybatisRepository;
import asap.be.repository.mybatis.WarehouseMapper;
import asap.be.repository.mybatis.WarehouseMybatisRepository;
import asap.be.service.MemberService;
import asap.be.service.MemberServiceImpl;
import asap.be.service.NotificationService;
import asap.be.service.NotificationServiceImpl;
import asap.be.service.ProductService;
import asap.be.service.ProductServiceImpl;
import asap.be.service.ReleaseService;
import asap.be.service.ReleaseServiceImpl;
import asap.be.service.WarehouseService;
import asap.be.service.WarehouseServiceImpl;
import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MybatisConfig {
	private final ProductMapper productMapper;
	private final WarehouseMapper warehouseMapper;
	private final ReleaseMapper releaseMapper;
	private final AmazonS3 amazonS3;
	private final MemberMapper memberMapper;

	@Bean
	public ProductService productService() {
		return new ProductServiceImpl(releaseService(), notificationService(), new QrcodeGeneratorService(new S3UploadService(amazonS3)),productRepository());
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
	public NotificationService notificationService() {
		return new NotificationServiceImpl(emitterRepository());
	}

	@Bean
	public MemberService memberService() {
		return new MemberServiceImpl(new MemberMybatisRepository(memberMapper));
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

	@Bean
	public EmitterRepository emitterRepository() {
		return new EmitterMybatisRepository();
	}
}
