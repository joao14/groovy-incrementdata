package com.pichincha.marketing.repository;

import com.pichincha.marketing.dto.InCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteDao extends JpaRepository<InCliente, Integer> {

    @Query(value = "SELECT * FROM in_cliente c WHERE c.clie_hashiden=:hash AND c.clie_estado='1'", nativeQuery = true)
    public abstract InCliente findByCliente(@Param("hash") String hash); 

    @Query(value = "SELECT count(c.clie_id) as num FROM in_cliente c INNER JOIN in_soliincr s ON c.clie_id=s.clie_id WHERE c.clie_hashiden=:hash AND c.clie_estado='1';", nativeQuery = true)
    public abstract Integer findByClienteIncrementByAccept(@Param("hash") String hash);

}
