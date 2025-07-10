package com.paic.assessment.marut.specification;

import org.springframework.data.jpa.domain.Specification;

import com.paic.assessment.marut.entity.CallDetailsRecordEntity;

import java.time.LocalDateTime;

public class CallDetailsSpecification {

    public static Specification<CallDetailsRecordEntity> recordDateBetween(LocalDateTime start, LocalDateTime end) {
        return (root, query, cb) -> cb.between(root.get("recordDate"), start, end);
    }

    public static Specification<CallDetailsRecordEntity> msisdnEquals(String msisdn) {
        return (root, query, cb) -> cb.equal(root.get("msisdn"), msisdn);
    }

    public static Specification<CallDetailsRecordEntity> imsiEquals(String imsi) {
        return (root, query, cb) -> cb.equal(root.get("imsi"), imsi);
    }

}
