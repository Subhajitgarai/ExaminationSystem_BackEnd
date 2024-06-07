package com.examination.system.user.controller;

import com.examination.system.admin.projections.ProjectionQuestion;
import com.examination.system.user.entity.AnswerSubmission;
import com.examination.system.user.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PutMapping("/updatePass")
    public String updatePasswordOfStudent(@RequestParam String roll, @RequestParam String oldPass,@RequestParam String newPass){
       return studentService.updatePassword(roll,oldPass,newPass);
    }
    @GetMapping("/test")
    public String testing(){
        return "Test !!";
    }
    @GetMapping("/login")
    public ResponseEntity<?>studentLoginByRollAndPass(@RequestParam String roll,@RequestParam String password){
        return studentService.studentLogin(roll, password);

    }
    //GetQuestionBy Subject name
    @GetMapping("/getquestion/{subject}")
    public ResponseEntity<?> getQuestionsBySubject(@PathVariable String subject){
       return studentService.giveExam(subject);
    }

    @GetMapping("/calculate")
    public AtomicInteger calculateScore(@RequestBody AnswerSubmission answerSubmission){
        return studentService.calculateResult(answerSubmission);
    }
    @GetMapping("/marksheet/{roll}")
    public ResponseEntity<?>getMarksheetForStudent(@PathVariable String roll){
        return studentService.getMarksheetForStudent(roll);
    }
}
