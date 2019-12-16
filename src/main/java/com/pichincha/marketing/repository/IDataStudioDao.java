package com.pichincha.marketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pichincha.marketing.dto.InDatastudio;

@Repository
public interface IDataStudioDao extends JpaRepository<InDatastudio, Integer> {
}
