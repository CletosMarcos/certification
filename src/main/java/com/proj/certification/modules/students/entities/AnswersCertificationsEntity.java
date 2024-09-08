package com.proj.certification.modules.students.entities;

import java.util.UUID;

public class AnswersCertificationsEntity {
    private UUID id;
    private UUID studentID;
    private UUID questionID;
    private UUID answerID;
    private boolean isCorrect;
}
