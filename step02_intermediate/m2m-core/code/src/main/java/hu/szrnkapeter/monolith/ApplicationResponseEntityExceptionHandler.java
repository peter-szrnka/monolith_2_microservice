package hu.szrnkapeter.monolith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import hu.szrnkapeter.monolith.dto.BaseResponseDto;

/**
 * Exception manager class.
 * 
 * @author Peter Szrnka
 */
@ControllerAdvice
public class ApplicationResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(ApplicationResponseEntityExceptionHandler.class);

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<BaseResponseDto> handleSomeException(final Exception e, final HandlerMethod handlerMethod) {
		BaseResponseDto response = new BaseResponseDto();
		response.setSuccess(false);
		response.setMessage(e.getMessage());
		
		LOG.error("Exception handled: {}", e.getMessage(), e);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}