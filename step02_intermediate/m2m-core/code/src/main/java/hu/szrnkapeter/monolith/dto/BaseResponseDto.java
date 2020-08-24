package hu.szrnkapeter.monolith.dto;

import lombok.Data;

/**
 * Base response based DTO.
 * 
 * @author Peter Szrnka
 */
@Data
public class BaseResponseDto {

	private boolean success = true;
	private String message = "OK";
}