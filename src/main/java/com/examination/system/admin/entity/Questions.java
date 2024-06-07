package com.examination.system.admin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Exam_Questions")
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionId;
    private String subject;
    private String questionContent;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "answeId",name = "answerId")
    private Answers answers;
}
