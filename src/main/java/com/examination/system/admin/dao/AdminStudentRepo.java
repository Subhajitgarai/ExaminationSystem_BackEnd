package com.examination.system.admin.dao;

import com.examination.system.admin.entity.AdminDetails;
import com.examination.system.admin.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AdminStudentRepo extends JpaRepository<Student,Integer> {
    public Optional<Student> findByRoll(String roll);

    @Modifying
    @Query("update Student set password=:newPass where roll=:roll")
    public void updateByPasswordOfStudent(@Param("newPass")String newPass,@Param("roll")String roll);
    @Query("from Student a where a.roll=:roll and a.password=:password")
    public Optional<Student> getStudentByNameAndPass(@Param("roll") String roll, @Param("password") String password);
}
