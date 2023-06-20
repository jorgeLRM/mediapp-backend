package com.mitocode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer>{

}
