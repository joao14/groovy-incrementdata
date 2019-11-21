package com.pichincha.marketing.repository;

import com.pichincha.marketing.dto.InLogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILogsDao extends JpaRepository<InLogs, Integer> {

}
