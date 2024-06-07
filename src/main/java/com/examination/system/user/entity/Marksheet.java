package com.examination.system.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Marksheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int marksheetId;
    private String studentRoll;
    private String studentName;
    private int studentMarks;
}
