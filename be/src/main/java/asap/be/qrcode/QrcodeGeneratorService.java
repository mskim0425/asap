package asap.be.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class QrcodeGeneratorService {
    //바코드를 생성하는거
    //바코드 옵션설정
    //바코드 생성
    private final S3UploadService s3service;

    @Transactional
    public String generateQRcodeImageURL(String url, int width, int height) throws WriterException, IOException {
        QRCodeWriter qr = new QRCodeWriter();
        BitMatrix matrix = qr.encode(url, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(matrix);
        ImageIO.write(qrImage, "png", out);
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());

        return s3service.uploadImage(in);

    }

}
