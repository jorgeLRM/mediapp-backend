package com.mitocode.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mitocode.model.Patient;
import com.mitocode.service.PatientService;

@RestController
@RequestMapping("/patients")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	@GetMapping
	public ResponseEntity<List<Patient>> findAll() {
		List<Patient> patients = patientService.findAll();
		return new ResponseEntity<>(patients, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Patient> findById(@PathVariable("id") Integer id) {
		Patient patient = patientService.findById(id);
		return new ResponseEntity<>(patient, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Patient> save(@RequestBody Patient patient) {
		Patient addedPatient = patientService.save(patient);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(addedPatient.getIdPatient()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Patient> update(@RequestBody Patient patient) {
		Patient updatedPatient = patientService.update(patient);
		return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		patientService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
