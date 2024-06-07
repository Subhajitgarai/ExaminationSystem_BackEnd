package com.examination.system.admin.dao;

import com.examination.system.admin.entity.Questions;
import com.examination.system.admin.projections.ProjectionQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepo extends JpaRepository<Questions,Integer> {
    @Query("select q.questionId as questionId, q.questionContent as questionContent, q.optionA as optionA, q.optionB as optionB, q.optionC as optionC, q.optionD as optionD from Questions q")
    public List<ProjectionQuestion> getQuestionsOnly();

    @Query("select q.questionId as questionId, q.questionContent as questionContent, q.optionA as optionA, q.optionB as optionB, q.optionC as optionC, q.optionD as optionD from Questions q where q.subject=:subject")
    public List<ProjectionQuestion> getQuestionsOnlyBySubject(@Param("subject")String subject);

    @Query("select q.answers.answeId from Questions q where q.questionId=:questionId")
    public int getCorrectAnswerByQuestionId(@Param("questionId")int questionId);


}
