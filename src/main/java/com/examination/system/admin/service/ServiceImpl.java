package com.examination.system.admin.service;

import com.examination.system.admin.dao.AdminAdditionRepo;
import com.examination.system.admin.dao.AdminRepo;
import com.examination.system.admin.dao.AdminStudentRepo;
import com.examination.system.admin.entity.AdminDetails;
import com.examination.system.admin.entity.Questions;
import com.examination.system.admin.entity.Student;
import com.examination.system.admin.projections.ProjectionQuestion;
import com.examination.system.user.dao.MarksheetRepo;
import com.examination.system.user.entity.Marksheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ServiceImpl implements ServiceInterface {
    //Question Answers Operations
    @Autowired
    private AdminRepo adminRepo;

    public Questions addQuestionAnswer(Questions questions) {
        return adminRepo.save(questions);

    }

    public List<Questions> getAllQuestions() {
        return adminRepo.findAll();
    }

    public String deleteQuestion(int questionId) {
        Optional<Questions> questions = adminRepo.findById(questionId);
        if (questions.isEmpty()) {
            return "Invalid QuestionId";
        } else {
            adminRepo.deleteById(questionId);
            return "Deleted Question [questionId = " + questionId + "]";
        }

    }

    public List<ProjectionQuestion> getQuestionOnly() {
        return adminRepo.getQuestionsOnly();
    }

    public ResponseEntity<?> updatequestionAnswer(Questions q) {
        Optional<Questions> questions = adminRepo.findById(q.getQuestionId());
        if (questions.isPresent()) {
            adminRepo.save(q);
            return new ResponseEntity<>(q, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question with this id = " + q.getQuestionId() + " not Exits Pls Create at first then try updating !!");


        }

    }

    //Students Operations
    @Autowired
    private AdminStudentRepo adminStudentRepo;
    public Student addStudent(Student student){
        return adminStudentRepo.save(student);
    }
    public ResponseEntity<?> deleteStudentById(int studentId){
        Optional<Student> student=adminStudentRepo.findById(studentId);
        if(student.isPresent()){
            adminStudentRepo.deleteById(studentId);
           return ResponseEntity.status(HttpStatus.OK).body("Student [studentId= "+studentId+" Deleted !! ");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student id Does not exists !!");
        }
    }
    public List<Student>getAllStudent(){
       return adminStudentRepo.findAll();
    }

    public ResponseEntity<?> findstudentbyroll(String roll){
        Optional<Student>student=adminStudentRepo.findByRoll(roll);
        if(student.isPresent()){
            return new ResponseEntity<>(student,HttpStatus.OK);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student Not Found");
        }
    }

    public ResponseEntity<?> updateStudent(Student s) {
        Optional<Student> student = adminStudentRepo.findById(s.getStudentId());
        if (student.isPresent()) {
            adminStudentRepo.save(s);
            return new ResponseEntity<>(s, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with this id = " + s.getStudentId() + " not Exits Pls Create at first then try updating !!");


        }

    }
   public ResponseEntity<?>getstudentById(int studentId){
        Optional<Student> student=adminStudentRepo.findById(studentId);
        if(student.isPresent()){
            return new ResponseEntity<>(student,HttpStatus.OK);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Student Present in the database that matches the studentId "+studentId);
        }
   }


   //Adding a new admin in database
    @Autowired
    AdminAdditionRepo adminAdditionRepo;
    public AdminDetails addAdmin(AdminDetails adminDetails){
        return adminAdditionRepo.save(adminDetails);
    }

    public List<AdminDetails> getAllAdmin(){
        return adminAdditionRepo.findAll();
    }

    //AdminLogin
    public ResponseEntity<?> adminLogin(String adminEmail, String adminPassword){
       Optional<AdminDetails> adminDetails= adminAdditionRepo.getAdminByNameAndPass(adminEmail,adminPassword);
       if(adminDetails.isPresent()){
           return new ResponseEntity<>(adminDetails,HttpStatus.OK);
       }
       else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid AdminName or Password !!");
       }

    }
    //getall marksheets
    @Autowired
    MarksheetRepo marksheetRepo;
    public ResponseEntity<?> allMarksheet(){
        if (marksheetRepo.findAll() .isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Marksheets Present !!");
        }
        else {
            return new ResponseEntity<>(marksheetRepo.findAll(),HttpStatus.OK);
        }
    }


}
