package asap.be.qrcode;

import asap.be.service.ProductServiceImpl;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
public class QrcodeController {

    private final QrcodeGeneratorService qrcodeGeneratorService;
    private final ProductServiceImpl productService;

    //qrCode 생성 -> 50만개데이터 -> UUID -> {{url}}/api/find-one?pId=3

    @GetMapping("/api/{uuid}")
    public ResponseEntity generateQRCode(@PathVariable("uuid")String uuid, HttpServletRequest request) throws WriterException, IOException {
        String original = request.getRequestURL().toString(); // https//:soonerthebetter/api/{uuid}
        String url = original.replace(request.getRequestURI(), "");


        Long pId = productService.findByUUID(uuid);
        url += "/api/find-one?pId=" + String.valueOf(pId);

        String imageURL = qrcodeGeneratorService.generateQRcodeImageURL(url, 150, 150);

        productService.saveS3ImageUrl(imageURL,pId);

        return new ResponseEntity<>(imageURL, HttpStatus.CREATED);
    }

}
