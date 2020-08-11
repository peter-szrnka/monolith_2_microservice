package hu.szrnkapeter.monolith.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * ID based response DTO.
 * 
 * @author Peter Szrnka
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class IdResponseDto extends BaseResponseDto {

	private Long id;
}