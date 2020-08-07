package hu.szrnkapeter.monolith.dto;

import lombok.Data;

@Data
public class BaseResponseDto {

	private boolean success = true;
	private String message = "OK";
}