package com.pichincha.marketing.repository;

import com.pichincha.marketing.dto.InBasecampa;
import com.pichincha.marketing.dto.InLogs;
import com.pichincha.marketing.dto.InLogsconversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ILogsConvDao extends JpaRepository<InLogsconversion, Integer> {

    @Query(value = "SELECT * FROM in_logsconversion l WHERE l.baca_id=:baca_id AND l.loco_identificacion=:identificacion", nativeQuery = true)
    public abstract InLogsconversion findByClient(@Param("baca_id") Integer baca_id, @Param("identificacion") String identificacion);

    @Transactional
    @Modifying
    @Query(value = "UPDATE in_logsconversion l set l.loco_pantalla=:pantalla where l.loco_id = :locoId", nativeQuery = true)
    public abstract void updateLogsConv(@Param("pantalla") String pantalla, @Param("locoId") Integer locoId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM in_logsconversion where loco_id=:locoId", nativeQuery = true)
    public abstract void deleteLogsConv(@Param("locoId") Integer locoId);
}
