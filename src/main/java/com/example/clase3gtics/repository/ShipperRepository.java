package com.example.clase3gtics.repository;

import com.example.clase3gtics.entity.Shipper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper, Integer> {

    List<Shipper> findByCompanyName(String name);

    @Query(nativeQuery = true,
            value = "select * from shippers where CompanyName = ?1")
    List<Shipper> buscarPorNombre(String name);

    @Query(nativeQuery = true,
            value = "select * from shippers where CompanyName like %?1%")
    List<Shipper> buscarPorNombreParcial(String name);


    @Transactional
    @Modifying
    @Query(nativeQuery = true,
    value = "UPDATE shippers SET phone = ?1 WHERE shipperid = ?2")
    void actualizarTelefonoShipper(String phone, int id);
}

