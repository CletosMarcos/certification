package com.proj.certification.modules.students.controllers;

import com.proj.certification.modules.students.dto.VerifyHasCertificationDTO;
import com.proj.certification.modules.students.repositories.CertificationStudentRepository;
import com.proj.certification.modules.students.useCases.VerifyIfHasCertificationUseCase;
import org.springframework.beans.factory.annotation.Autowired;
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
}
