package com.paic.assessment.marut.service;

import com.paic.assessment.marut.dto.RequestDto;
import com.paic.assessment.marut.dto.ResponseDto;
import com.paic.assessment.marut.entity.CallDetailsRecordEntity;
import com.paic.assessment.marut.repository.CallDetailsRecordsRepository;
import com.paic.assessment.marut.specification.CallDetailsSpecification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CallDetailsQueryService {

	
	@Autowired
    private CallDetailsRecordsRepository callDetailsRecordsrepository;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public List<ResponseDto> queryRecords(RequestDto request) {

        LocalDateTime startDate = LocalDateTime.parse(request.getRecordDateStart(), formatter);
        LocalDateTime endDate = LocalDateTime.parse(request.getRecordDateEnd(), formatter);

        Specification<CallDetailsRecordEntity> spec = Specification.where(
                CallDetailsSpecification.recordDateBetween(startDate, endDate)
        );

        if (request.getMsisdn() != null && !request.getMsisdn().isBlank()) {
            spec = spec.and(CallDetailsSpecification.msisdnEquals(request.getMsisdn()));
        }

        if (request.getImsi() != null && !request.getImsi().isBlank()) {
            spec = spec.and(CallDetailsSpecification.imsiEquals(request.getImsi()));
        }

        List<CallDetailsRecordEntity> results = callDetailsRecordsrepository.findAll(spec);

        return results.stream()
                .map(e -> new ResponseDto(e.getRecordDate(), e.getMsisdn(), e.getImsi()))
                .collect(Collectors.toList());
    }
}
