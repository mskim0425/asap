package asap.be.qrcode;

import asap.be.exception.BusinessLogicException;
import asap.be.exception.ExceptionCode;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3UploadService {

    private final String bucket = "asap-image";

    private final AmazonS3 s3;

    @Transactional
    public String uploadImage(ByteArrayInputStream in){
        ObjectMetadata objMeta = new ObjectMetadata();
        String s3FileName = UUID.randomUUID() + ".png";

        try (InputStream inputStream = in) {
            objMeta.setContentLength(inputStream.available());
            objMeta.setContentType("png");

            s3.putObject(bucket, s3FileName, inputStream, objMeta);
            return s3.getUrl(bucket, s3FileName).toString();
        } catch (IOException e) {
            throw new BusinessLogicException(ExceptionCode.NEED_IMAGE);
        }

    }
}
