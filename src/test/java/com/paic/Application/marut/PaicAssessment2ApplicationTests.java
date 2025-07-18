package com.paic.Application.marut;

import com.paic.assessment.marut.dto.RequestDto;
import com.paic.assessment.marut.dto.ResponseDto;
import com.paic.assessment.marut.service.CallDetailsQueryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class PaicAssessment2ApplicationTests {

	@Autowired
	private CallDetailsQueryService callDetailsQueryService;

	@Test
	void testQueryServiceReturnsRecords() {
		RequestDto request = new RequestDto();
		request.setRecordDateStart("2023-08-18 10:00:00");
		request.setRecordDateEnd("2023-08-18 10:31:00");
		request.setMsisdn("573208843477");

		List<ResponseDto> result = callDetailsQueryService.queryRecords(request);
		assertNotNull(result);
		assertFalse(result.isEmpty(), "Expected at least one result");
	}

}
