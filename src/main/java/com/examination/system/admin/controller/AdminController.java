package com.examination.system.admin.controller;

import com.examination.system.admin.entity.Questions;
import com.examination.system.admin.entity.Student;
import com.examination.system.admin.projections.ProjectionQuestion;
import com.examination.system.admin.service.ServiceInterface;
import com.examination.system.user.dao.MarksheetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AdminController {
    //For Questions and Answers
    @Autowired
    private ServiceInterface serviceInterface;

    @PostMapping("question/add")
    public ResponseEntity<Questions>addNewQuestionWithAnswer(@RequestBody Questions questions){
        serviceInterface.addQuestionAnswer(questions);
        return new ResponseEntity<>(questions, HttpStatus.CREATED);
    }
    @GetMapping("question/getquestionanswers")
    public List<Questions> getAllquestionsAnswers(){
        return serviceInterface.getAllQuestions();
    }

    @DeleteMapping("question/delete/{questionId}")
    public String deleteQuestionById(@PathVariable int questionId) {
       return serviceInterface.deleteQuestion(questionId);
    }

    @GetMapping("question/getquestionsonly")
    public List<ProjectionQuestion> getQuestionsOnly(){
        return serviceInterface.getQuestionOnly();
    }

    @PutMapping("question/update")
    public ResponseEntity<?>updateQuestions(@RequestBody Questions questions){
       return serviceInterface.updatequestionAnswer(questions);
    }

    //For Student Details
    @PostMapping("/student/add")
    public Student insertStudent(@RequestBody Student s){
       return serviceInterface.addStudent(s);
    }
    @DeleteMapping("student/delete/{studentId}")
    public ResponseEntity<?> deleteStudentById(@PathVariable int studentId){
       return serviceInterface.deleteStudentById(studentId);
    }
    @PutMapping("/student/update")
    public ResponseEntity<?> updateStudent(@RequestBody Student student){
       return serviceInterface.updateStudent(student);
    }
    @GetMapping("/student/getall")
    public List<Student> getAllStudent(){
       return serviceInterface.getAllStudent();
    }
    @GetMapping("/student/get/{studentId}")
    public ResponseEntity<?>getStudentById(@PathVariable int studentId){
       return serviceInterface.getstudentById(studentId);
    }
    @GetMapping("/student/roll/{studentRoll}")
    public ResponseEntity<?> findStudentByRoll(@PathVariable String studentRoll){
       return serviceInterface.findstudentbyroll(studentRoll);
    }
    //Get all Results
    @GetMapping("/getallmarksheet")
    public ResponseEntity<?> getAllResults(){
        return serviceInterface.allMarksheet();
    }

}
