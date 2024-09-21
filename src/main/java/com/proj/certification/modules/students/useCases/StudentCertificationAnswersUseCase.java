package com.proj.certification.modules.students.useCases;

import com.proj.certification.modules.questions.dto.StudentCertificationAnswersDTO;
import com.proj.certification.modules.questions.entities.QuestionEntity;
import com.proj.certification.modules.questions.repositories.QuestionRepository;
import com.proj.certification.modules.students.dto.VerifyHasCertificationDTO;
import com.proj.certification.modules.students.entities.AnswersCertificationsEntity;
import com.proj.certification.modules.students.entities.CertificationStudentEntity;
import com.proj.certification.modules.students.entities.StudentEntity;
import com.proj.certification.modules.students.repositories.CertificationStudentRepository;
import com.proj.certification.modules.students.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StudentCertificationAnswersUseCase {

    @Autowired
    CertificationStudentRepository certificationStudentRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

    public CertificationStudentEntity execute(StudentCertificationAnswersDTO studentCertificationAnswersDTO) throws Exception {

        var hasCertification = this.verifyIfHasCertificationUseCase.execute(new VerifyHasCertificationDTO(studentCertificationAnswersDTO.getEmail(), studentCertificationAnswersDTO.getTechnology()));

        if (hasCertification) {
            throw new Exception("Student already has Certification");
        }

//    Retrieve the alternatives for the questions
//          - Correct or Incorrect
        List<QuestionEntity> questionsEntity = questionRepository.findByTechnology(studentCertificationAnswersDTO.getTechnology());
        List<AnswersCertificationsEntity> answersCertifications = new ArrayList<>();
        AtomicInteger correctAnswers = new AtomicInteger(0);


        studentCertificationAnswersDTO.getQuestionsAnswersDTO()
                .stream().forEach(questionAnswer -> {
                    var question = questionsEntity.stream().filter(quest -> quest.getId().equals(questionAnswer.getQuestionID()))
                            .findFirst().get();

                    var findCorrectAlternative = question.getAlternatives().stream().filter(alternative -> alternative.isCorrect()).findFirst().get();

                    if (findCorrectAlternative.getId().equals(questionAnswer.getAlternativeID())) {
                        questionAnswer.setCorrect(true);
                        correctAnswers.incrementAndGet();
                    }else {
                        questionAnswer.setCorrect(false);
                    }

                    var answersCertificationsEntity = AnswersCertificationsEntity.builder()
                            .answerID(questionAnswer.getAlternativeID())
                            .questionID(questionAnswer.getQuestionID())
                            .isCorrect(questionAnswer.isCorrect()).build();

                    answersCertifications.add(answersCertificationsEntity);
                });

//    Verify by email if Student Exists
        var student = studentRepository.findByEmail(studentCertificationAnswersDTO.getEmail());
        UUID studentID;
        if (student.isEmpty()) {
            var studentCreated = StudentEntity.builder().email(studentCertificationAnswersDTO.getEmail()).build();
            studentRepository.save(studentCreated);
            studentID = studentCreated.getId();
        } else {
            studentID = student.get().getId();
        }


        CertificationStudentEntity certificationStudentEntity = CertificationStudentEntity.builder()
                .technology(studentCertificationAnswersDTO.getTechnology())
                .studentID(studentID)
                .grade(correctAnswers.get())
                .build();

        var certificationStudentCreated = certificationStudentRepository.save(certificationStudentEntity);

        answersCertifications.stream().forEach(answerCertification -> {
            answerCertification.setCertificationID(certificationStudentEntity.getId());
            answerCertification.setCertificationStudentEntity(certificationStudentEntity);
        });

        certificationStudentEntity.setAnswersCertificationsEntity(answersCertifications);
        certificationStudentRepository.save(certificationStudentEntity);

        return certificationStudentCreated;

//    Save the Information of the Certification taken edit

    }
}
