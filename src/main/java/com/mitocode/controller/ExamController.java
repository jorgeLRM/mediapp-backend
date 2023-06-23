package com.mitocode.controller;

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
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.mitocode.dto.ExamDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Exam;
import com.mitocode.service.ExamService;

@RestController
@RequestMapping("/exams")
public class ExamController {
	
	@Autowired
	private ExamService examService;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping
	public ResponseEntity<List<ExamDTO>> findAll() {
		List<ExamDTO> exams = examService.findAll().stream().map(p -> mapper.map(p, ExamDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<>(exams, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ExamDTO> findById(@PathVariable("id") Integer id) {
		Exam exam = examService.findById(id);
		
		if (exam == null) {
			throw new ModelNotFoundException("ID NOT FOUND: " + id);
		}
		
		return new ResponseEntity<>(mapper.map(exam, ExamDTO.class), HttpStatus.OK);
	}
	
	@GetMapping("/hateoas/{id}")
	public EntityModel<ExamDTO> findByIdHateoas(@PathVariable("id") Integer id) {
		Exam exam = examService.findById(id);
		
		if (exam == null) {
			throw new ModelNotFoundException("ID NOT FOUND: " + id);
		}
		
		EntityModel<ExamDTO> resource = EntityModel.of(mapper.map(exam, ExamDTO.class));
		WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
		resource.add(link1.withRel("Exam-info1"));
		
		return resource;
	}
	
	@PostMapping
	public ResponseEntity<Exam> save(@Valid @RequestBody ExamDTO ExamDto) {
		Exam addedExam = examService.save(mapper.map(ExamDto, Exam.class));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(addedExam.getIdExam()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Exam> update(@Valid @RequestBody ExamDTO ExamDto) {
		Exam updatedExam = examService.update(mapper.map(ExamDto, Exam.class));
		return new ResponseEntity<>(updatedExam, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		examService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
