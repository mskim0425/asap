package asap.be.domain;

import asap.be.exception.ErrorResponse;
import asap.be.exception.ExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Slf4j
@Transactional
@SpringBootTest
public class ExceptionTest {

	@Test
	void testGetStatus() {
		ErrorResponse response = new ErrorResponse(404, "Not Found");
		assertEquals(404, response.getStatus());
	}

	@Test
	void testGetMessage() {
		ErrorResponse response = new ErrorResponse(404, "Not Found");
		assertEquals("Not Found", response.getMessage());
	}

	@Test
	void testGetFieldErrors() {
		List<ErrorResponse.FieldError> fieldErrors = Arrays.asList(
				new ErrorResponse.FieldError("field1", "rejectedValue1", "Error Message 1"),
				new ErrorResponse.FieldError("field2", "rejectedValue2", "Error Message 2")
		);
		ErrorResponse response = new ErrorResponse(400, "Bad Request", fieldErrors, null);
		assertEquals(fieldErrors, response.getFieldErrors());
	}

	@Test
	void testGetViolationErrors() {
		List<ErrorResponse.ConstraintViolationError> violationErrors = Arrays.asList(
				new ErrorResponse.ConstraintViolationError("field1", "rejectedValue1", "Error Message 1"),
				new ErrorResponse.ConstraintViolationError("field2", "rejectedValue2", "Error Message 2")
		);
		ErrorResponse response = new ErrorResponse(400, "Bad Request", null, violationErrors);
		assertEquals(violationErrors, response.getViolationErrors());
		assertThat(violationErrors.get(0).getPropertyPath()).isEqualTo(violationErrors.get(0).getPropertyPath());
		assertThat(violationErrors.get(0).getRejectedValue()).isEqualTo(violationErrors.get(0).getRejectedValue());
		assertThat(violationErrors.get(0).getReason()).isEqualTo(violationErrors.get(0).getReason());
	}

	@Test
	void testGetFieldAndViolationErrors() {
		List<ErrorResponse.FieldError> fieldErrors = Arrays.asList(
				new ErrorResponse.FieldError("field1", "rejectedValue1", "Error Message 1"),
				new ErrorResponse.FieldError("field2", "rejectedValue2", "Error Message 2")
		);

		List<ErrorResponse.ConstraintViolationError> violationErrors = Arrays.asList(
				new ErrorResponse.ConstraintViolationError("field1", "rejectedValue1", "Error Message 1"),
				new ErrorResponse.ConstraintViolationError("field2", "rejectedValue2", "Error Message 2")
		);

		ErrorResponse errorResponse = new ErrorResponse(fieldErrors, violationErrors);
		assertThat(errorResponse.getFieldErrors()).isEqualTo(fieldErrors);
		assertThat(errorResponse.getViolationErrors()).isEqualTo(violationErrors);
	}

	@Test
	void errorTest1() {
		// given
		BindingResult bindingResult = new BeanPropertyBindingResult(new Object(), "object");
		bindingResult.addError(new FieldError("object", "field", "Error message"));
		int status = HttpStatus.BAD_REQUEST.value();
		String message = "Bad Request";

		// when
		ErrorResponse response = ErrorResponse.of(bindingResult, status, message);

		// then
		assertEquals(status, response.getStatus());
		assertEquals(message, response.getMessage());
		assertNotNull(response.getFieldErrors());
		assertNull(response.getViolationErrors());

		List<ErrorResponse.FieldError> fieldErrors = response.getFieldErrors();
		assertEquals(1, fieldErrors.size());

		ErrorResponse.FieldError error = fieldErrors.get(0);
		assertEquals("field", error.getField());
		assertEquals("Error message", error.getReason());
		assertThat(error.getRejectedValue()).isEqualTo(error.getRejectedValue());
	}

	@Test
	void errorTest2() {
		// given
		Set<ConstraintViolation<?>> violations = new HashSet<>();
		ConstraintViolation<String> violation = mock(ConstraintViolation.class);
		when(violation.getPropertyPath()).thenReturn(PathImpl.createPathFromString("field"));
		when(violation.getInvalidValue()).thenReturn("value");
		when(violation.getMessage()).thenReturn("message");
		violations.add(violation);

		int status = HttpStatus.BAD_REQUEST.value();
		String message = "Bad Request";

		// when
		ErrorResponse response = ErrorResponse.of(violations, status, message);

		// then
		assertThat(response.getStatus()).isEqualTo(status);
		assertThat(response.getMessage()).isEqualTo(message);
	}

	@Test
	void errorTest3() {
		// given

		// when
		ErrorResponse response = ErrorResponse.of(ExceptionCode.PRODUCT_NOT_EXISTS);

		// then
		assertThat(response.getStatus()).isEqualTo(ExceptionCode.PRODUCT_NOT_EXISTS.getStatus());
		assertThat(response.getMessage()).isEqualTo("Product not exists");
		assertThat(response.getFieldErrors()).isNull();
		assertThat(response.getViolationErrors()).isNull();
	}

	@Test
	void errorTest4() {
		// given
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;

		// when
		ErrorResponse response = ErrorResponse.of(httpStatus);

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
		assertThat(response.getMessage()).isEqualTo(HttpStatus.NOT_FOUND.getReasonPhrase());
		assertThat(response.getFieldErrors()).isNull();
		assertThat(response.getViolationErrors()).isNull();
	}

	@Test
	void errorTest5() {
		// given
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		String message = "Bad Request";

		// when
		ErrorResponse response = ErrorResponse.of(httpStatus, message);

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
		assertThat(response.getMessage()).isEqualTo(message);
		assertThat(response.getFieldErrors()).isNull();
		assertThat(response.getViolationErrors()).isNull();
	}
}
