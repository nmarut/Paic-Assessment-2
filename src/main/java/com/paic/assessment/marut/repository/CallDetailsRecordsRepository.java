package com.paic.assessment.marut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.paic.assessment.marut.entity.CallDetailsRecordEntity;

public interface CallDetailsRecordsRepository extends JpaRepository<CallDetailsRecordEntity,Integer>,JpaSpecificationExecutor<CallDetailsRecordEntity> {

}
