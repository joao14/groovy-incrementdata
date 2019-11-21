package com.pichincha.marketing.repository;

import com.pichincha.marketing.dto.InSoliincr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISoliincrDao extends JpaRepository<InSoliincr, Integer> {

}
