package com.proj.certification.modules.questions.dto;

import java.util.List;

import com.proj.certification.modules.students.dto.QuestionAnswerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentCertificationAnswersDTO {

    private String email;
    private String technology;
    private List<QuestionAnswerDTO> questionsAnswersDTO;
}
