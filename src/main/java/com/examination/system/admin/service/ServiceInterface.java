package com.examination.system.admin.service;
import com.examination.system.admin.entity.AdminDetails;
import com.examination.system.admin.entity.Questions;
import com.examination.system.admin.entity.Student;
import com.examination.system.admin.projections.ProjectionQuestion;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ServiceInterface {
    //For Question Answers
    //add Question with Answer
    public Questions addQuestionAnswer(Questions questions);
    //Get All Question with Answer Details
    public List<Questions> getAllQuestions();
    // Delete A Question By Question Id
    public String deleteQuestion(int questionId);
    //Get Only Question Details without answer By Projection Interface
    public List<ProjectionQuestion> getQuestionOnly();
    // Update the questions details
    public ResponseEntity<?> updatequestionAnswer(Questions q);

    //For Student
    // Add new Student in the Database
    public Student addStudent(Student student);
    //Delete a student by StudentId
    public ResponseEntity<?> deleteStudentById(int studentId);
    //Get All Student Details
    public List<Student>getAllStudent();
    //Update Student Details
    public ResponseEntity<?> updateStudent(Student s);
    //Getting Student by StudentId
    public ResponseEntity<?>getstudentById(int studentId);
    //Finding Student By roll
    public ResponseEntity<?> findstudentbyroll(String roll);

    //For Adding new admin in database
    //Adding New admin to the database
    public AdminDetails addAdmin(AdminDetails adminDetails);
    //Get all admin details
    public List<AdminDetails> getAllAdmin();
    //During logn by validating Email and password
    public ResponseEntity<?> adminLogin(String adminEmail, String adminPassword);
    //Get all marksheet
    public ResponseEntity<?> allMarksheet();

}
