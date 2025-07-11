package com.paic.assessment.marut.controller;

import com.paic.assessment.marut.dto.RequestDto;
import com.paic.assessment.marut.dto.ResponseDto;
import com.paic.assessment.marut.service.CallDetailsQueryService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class QueryApiController {

	@Autowired
    private CallDetailsQueryService callDetailsQueryService;

    @GetMapping("/query")
    public ResponseEntity<?> searchCallDetails(@Valid @ModelAttribute RequestDto request) {

        if (request.getRecordDateStart() == null || request.getRecordDateStart().isBlank()) {
            return ResponseEntity.badRequest().body("Start date is mandatory");
        }
        if (request.getRecordDateEnd() == null || request.getRecordDateEnd().isBlank()) {
            return ResponseEntity.badRequest().body("End date is mandatory");
        }
        List<ResponseDto> response = callDetailsQueryService.queryRecords(request);
        return ResponseEntity.ok(response);
    }
}
