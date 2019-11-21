package com.pichincha.marketing.repository;

import com.pichincha.marketing.dto.InBasecampa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IBasecampaDao  extends JpaRepository<InBasecampa, Integer> {

    @Query(value = "SELECT * FROM in_basecampa b WHERE b.baca_id=:baca_id", nativeQuery = true)
    public abstract InBasecampa findByBaseCampana(@Param("baca_id") Integer baca_id);
}
