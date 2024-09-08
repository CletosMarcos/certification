package com.proj.certification.modules.students.entities;

import java.util.List;
import java.util.UUID;

public class CertificationStudentEntity {
    private UUID id;
    private UUID studentID;
    private int grade;
    private List<AnswersCertificationsEntity> answersCertificationsEntity;
}
