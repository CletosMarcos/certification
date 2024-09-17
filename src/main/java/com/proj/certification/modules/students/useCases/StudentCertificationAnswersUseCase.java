package com.proj.certification.modules.students.useCases;

import com.proj.certification.modules.questions.dto.StudentCertificationAnswersDTO;
import com.proj.certification.modules.questions.entities.QuestionEntity;
import com.proj.certification.modules.questions.repositories.QuestionRepository;
//import com.proj.certification.modules.students.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentCertificationAnswersUseCase {

/*    @Autowired
    private StudentRepository studentRepository;*/

    @Autowired
    private QuestionRepository questionRepository;

    public StudentCertificationAnswersDTO execute(StudentCertificationAnswersDTO studentCertificationAnswersDTO) {

/*    Verify if Student Exists
        var student = studentRepository.findByEmail(studentCertificationAnswersDTO.getEmail());
        if (student.isEmpty()) {
            throw new Exception("Incorrect user email.");
        }*/
//    Retrieve the alternatives for the questions
//          - Correct or Incorrect
        List<QuestionEntity> questionsEntity = questionRepository.findByTechnology(studentCertificationAnswersDTO.getTechnology());

        studentCertificationAnswersDTO.getQuestionsAnswersDTO()
                .stream().forEach(questionAnswer -> {
                    var question = questionsEntity.stream().filter(quest -> quest.getId().equals(questionAnswer.getQuestionID()))
                            .findFirst().get();

                    var findCorrectAlternative = question.getAlternatives().stream().filter(alternative -> alternative.isCorrect()).findFirst().get();

                    if (findCorrectAlternative.getId().equals(questionAnswer.getAlternativeID())) {
                        questionAnswer.setCorrect(true);
                    }else {
                        questionAnswer.setCorrect(false);
                    }
                });
        return studentCertificationAnswersDTO;
//    Save the Information of the Certification taken
    }
}
