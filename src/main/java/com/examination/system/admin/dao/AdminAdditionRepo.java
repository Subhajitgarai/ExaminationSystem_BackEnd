package com.examination.system.admin.dao;

import com.examination.system.admin.entity.AdminDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AdminAdditionRepo extends JpaRepository<AdminDetails,String> {
    //Retrive admin details by email and password
    @Query("from AdminDetails a where a.adminEmail=:adminEmail and a.adminPassword=:adminPassword")
    public Optional<AdminDetails> getAdminByNameAndPass(@Param("adminEmail") String adminEmail, @Param("adminPassword") String adminPassword);


}
