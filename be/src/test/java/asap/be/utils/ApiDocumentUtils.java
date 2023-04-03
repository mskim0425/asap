package asap.be.utils;

import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

/**
 * 문서 이쁘게 써주는 기능
 */
public interface ApiDocumentUtils {
	static OperationRequestPreprocessor getDocumentRequest() {
		return preprocessRequest(prettyPrint());
	}

	static OperationResponsePreprocessor getDocumentResponse() {
		return preprocessResponse(prettyPrint());
	}
}
