package com.paic.assessment.marut.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ResponseDto {
	
	
    private LocalDateTime recordDate;
    private String msisdn;
    private String imsi;
}
