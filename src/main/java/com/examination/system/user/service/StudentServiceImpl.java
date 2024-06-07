package com.examination.system.user.service;

import com.examination.system.admin.dao.AdminRepo;
import com.examination.system.admin.dao.AdminStudentRepo;
import com.examination.system.admin.entity.Student;
import com.examination.system.user.dao.MarksheetRepo;
import com.examination.system.user.entity.AnswerSubmission;
import com.examination.system.user.entity.Marksheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class StudentServiceImpl implements StudentService{
    @Autowired
    AdminStudentRepo adminStudentRepo;
    //Update Student Pass
 Logger logger= LoggerFactory.getLogger(StudentServiceImpl.class);
    @Transactional
    public String updatePassword(String roll, String oldPass, String newPass) {
        try {
            int f = 0;
            logger.info("Updating password for roll={} - newPass={}", roll, newPass);

            Optional<Student> optionalStudent = adminStudentRepo.findByRoll(roll);
            if (optionalStudent.isPresent()) {
                Student student = optionalStudent.get();
                if (student.getPassword().equals(oldPass)) {
                    adminStudentRepo.updateByPasswordOfStudent(newPass,roll);
                    f = 1;
                }
                logger.info("New Pass: {}", newPass);
            }

            if (f == 1) {
                return "Password Updated !!";
            } else {
                return " Old Password does not matches!!";
            }
        } catch (Exception e) {
            logger.error("Error updating password", e);
            return "Error updating password";
        }
    }
    //Login
    public ResponseEntity<?> studentLogin(String roll, String password){
        Optional<Student> student= adminStudentRepo.getStudentByNameAndPass(roll,password);
        if(student.isPresent()){
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid StudentName or Password !!");
        }

    }


    //Give Exam
    @Autowired
    AdminRepo adminRepo;
    public ResponseEntity<?>giveExam(String subject){
        if (adminRepo.getQuestionsOnlyBySubject(subject).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This Subject is not registered !!");

        }
        else {
            return new ResponseEntity<>(adminRepo.getQuestionsOnlyBySubject(subject),HttpStatus.OK);
        }
    }
    //Calculate Result
    @Autowired
    MarksheetRepo marksheetRepo;
    public AtomicInteger calculateResult(AnswerSubmission answerSubmission){
        AtomicInteger score = new AtomicInteger(0);
        HashMap<Integer,Integer>afterSubmit=answerSubmission.getQuestionIdAndAnswerId();
        afterSubmit.forEach((questionId, answerId)->{
            int ans=adminRepo.getCorrectAnswerByQuestionId(questionId);
            if(ans==answerId){
                score.getAndIncrement();
            }
        });
        logger.info("Score: {}",score);
        Marksheet marksheet=new Marksheet();
        marksheet.setStudentRoll(answerSubmission.getRoll());
        marksheet.setStudentMarks(score.get());
        marksheet.setStudentName(answerSubmission.getName());
        marksheetRepo.save(marksheet);
        return score;
    }

    //Get Marks for Student
    public ResponseEntity<?> getMarksheetForStudent(String roll){
       Optional<Marksheet>marksheet= marksheetRepo.findByStudentRoll(roll);
       if (marksheet.isPresent()){
           return new ResponseEntity<>(marksheet.get(),HttpStatus.OK);
       }
       else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("First Give Exam !!");
       }
    }
}
