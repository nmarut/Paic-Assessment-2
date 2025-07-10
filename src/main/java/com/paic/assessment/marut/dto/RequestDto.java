package com.paic.assessment.marut.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {
	
	@NotBlank(message = "Record Start Date is Mandatory.")
    private String recordDateStart;
	
	@NotBlank(message = "Record Start Date is Mandatory.")
    private String recordDateEnd;
    private String msisdn;
    private String imsi;
}
