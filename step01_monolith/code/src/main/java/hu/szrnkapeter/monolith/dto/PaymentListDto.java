package hu.szrnkapeter.monolith.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class PaymentListDto extends BaseResponseDto {

	private List<PaymentDto> payments;
}