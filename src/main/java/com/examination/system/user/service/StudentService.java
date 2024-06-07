package com.examination.system.user.service;

import com.examination.system.user.entity.AnswerSubmission;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public interface StudentService {
    //Update Password for Student
    String updatePassword(String studentRoll,String oldPass,String newPass);
    //Student Login Checking
    public ResponseEntity<?> studentLogin(String roll, String password);
    //Give Exam and Get Question of your desired subject
    public ResponseEntity<?>giveExam(String subject);
    //After Submission Calculate Score
    public AtomicInteger calculateResult(AnswerSubmission answerSubmission);
    //Get Result
    public ResponseEntity<?> getMarksheetForStudent(String roll);
}
