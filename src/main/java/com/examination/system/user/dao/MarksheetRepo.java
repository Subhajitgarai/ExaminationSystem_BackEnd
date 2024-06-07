package com.examination.system.user.dao;

import com.examination.system.user.entity.Marksheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarksheetRepo extends JpaRepository<Marksheet,Integer> {

    public Optional<Marksheet>findByStudentRoll(String studentRoll);

}
