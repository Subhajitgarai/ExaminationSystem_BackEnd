package com.examination.system.user.entity;

import lombok.*;

import java.util.HashMap;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AnswerSubmission {
    private String roll;
    private String name;
    private HashMap<Integer,Integer>questionIdAndAnswerId;
}
