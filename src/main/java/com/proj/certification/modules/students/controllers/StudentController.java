package com.proj.certification.modules.students.controllers;

import com.proj.certification.modules.questions.dto.StudentCertificationAnswersDTO;
import com.proj.certification.modules.students.dto.VerifyHasCertificationDTO;
import com.proj.certification.modules.students.entities.CertificationStudentEntity;
import com.proj.certification.modules.students.useCases.StudentCertificationAnswersUseCase;
import com.proj.certification.modules.students.useCases.VerifyIfHasCertificationUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    // Using the UseCase
    @Autowired
    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

    @Autowired
    private StudentCertificationAnswersUseCase studentCertificationAnswersUseCase;

    // Verify is User has a Certification
    @PostMapping("verifyIfHasCertification")
    public String verifyIfHasCertification(@RequestBody VerifyHasCertificationDTO verifyHasCertificationDTO) {
        // email
        // technology
        if (verifyIfHasCertificationUseCase.execute(verifyHasCertificationDTO)) {
            return "User has taken this test";
        }
        return "User can make the test";
    }

    @PostMapping("/certification/answer")
    public ResponseEntity<Object> certificationAnswer(
            @RequestBody StudentCertificationAnswersDTO studentCertificationAnswersDTO) {
        try {
            var result = studentCertificationAnswersUseCase.execute(studentCertificationAnswersDTO);
            return ResponseEntity.ok().body(result);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
