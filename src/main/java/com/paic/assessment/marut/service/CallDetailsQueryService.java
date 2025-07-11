package com.paic.assessment.marut.service;

import com.paic.assessment.marut.dto.RequestDto;
import com.paic.assessment.marut.dto.ResponseDto;
import com.paic.assessment.marut.entity.CallDetailsRecordEntity;
import com.paic.assessment.marut.repository.CallDetailsRecordsRepository;
import com.paic.assessment.marut.specification.CallDetailsSpecification;

import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CallDetailsQueryService {

	
	@Autowired
    private CallDetailsRecordsRepository callDetailsRecordsrepository;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public List<ResponseDto> queryRecords(RequestDto request) {


        if (request.getRecordDateStart() == null || request.getRecordDateStart().isBlank() ||
                request.getRecordDateEnd() == null || request.getRecordDateEnd().isBlank()) {

            log.warn("Start date and end date are mandatory.");
            throw new IllegalArgumentException("Start date and end date are mandatory. Please provide both values.");
        }

        LocalDateTime startDate = LocalDateTime.parse(request.getRecordDateStart(), formatter);
        LocalDateTime endDate = LocalDateTime.parse(request.getRecordDateEnd(), formatter);

        log.info("Inside Call Details Query Service");
        Specification<CallDetailsRecordEntity> specification = Specification.where(
                CallDetailsSpecification.recordDateBetween(startDate, endDate)
        );

        if (request.getMsisdn() != null && !request.getMsisdn().isBlank()) {
            specification = specification.and(CallDetailsSpecification.msisdnEquals(request.getMsisdn()));
        }

        if (request.getImsi() != null && !request.getImsi().isBlank()) {
            specification = specification.and(CallDetailsSpecification.imsiEquals(request.getImsi()));
        }

        List<CallDetailsRecordEntity> results = callDetailsRecordsrepository.findAll(specification);

        if(results.isEmpty()){
            log.info("No call detail records found for the Dates Provided.");
            return Collections.emptyList();
        }

        return results.stream()
                .map(e -> new ResponseDto(e.getRecordDate(), e.getMsisdn(), e.getImsi()))
                .collect(Collectors.toList());
    }
}
