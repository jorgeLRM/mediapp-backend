package com.mitocode.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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

import com.mitocode.dto.PatientDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Patient;
import com.mitocode.service.PatientService;

@RestController
@RequestMapping("/patients")
//@CrossOrigin(origins = "*")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping
	public ResponseEntity<List<PatientDTO>> findAll() {
		List<PatientDTO> patients = patientService.findAll().stream().map(p -> mapper.map(p, PatientDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<>(patients, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PatientDTO> findById(@PathVariable("id") Integer id) {
		Patient patient = patientService.findById(id);
		
		if (patient == null) {
			throw new ModelNotFoundException("ID NOT FOUND: " + id);
		}
		
		return new ResponseEntity<>(mapper.map(patient, PatientDTO.class), HttpStatus.OK);
	}
	
	@GetMapping("/hateoas/{id}")
	public EntityModel<PatientDTO> findByIdHateoas(@PathVariable("id") Integer id) {
		Patient patient = patientService.findById(id);
		
		if (patient == null) {
			throw new ModelNotFoundException("ID NOT FOUND: " + id);
		}
		
		EntityModel<PatientDTO> resource = EntityModel.of(mapper.map(patient, PatientDTO.class));
		WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
		resource.add(link1.withRel("patient-info1"));
		
		return resource;
	}
	
	@PostMapping
	public ResponseEntity<Patient> save(@Valid @RequestBody PatientDTO patientDto) {
		Patient addedPatient = patientService.save(mapper.map(patientDto, Patient.class));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(addedPatient.getIdPatient()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Patient> update(@Valid @RequestBody PatientDTO patientDto) {
		Patient updatedPatient = patientService.update(mapper.map(patientDto, Patient.class));
		return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		patientService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
