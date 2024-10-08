package com.proj.certification.modules.students.repositories;

import com.proj.certification.modules.students.entities.CertificationStudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CertificationStudentRepository extends JpaRepository<CertificationStudentEntity, UUID> {

    @Query("SELECT c FROM certifications AS c INNER JOIN c.studentEntity AS std WHERE std.email = :email AND c.technology = :technology")
    List<CertificationStudentEntity> findByStudentEmailAndTechnology(String email, String technology);

    @Query("SELECT c FROM certifications AS c ORDER BY c.grade DESC LIMIT 10")
    List<CertificationStudentEntity> findTop10ByOrderGradeDesc();

}
