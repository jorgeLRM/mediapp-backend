package com.mitocode.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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

import com.mitocode.dto.ConsultDTO;
import com.mitocode.dto.ConsultListExamDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Consult;
import com.mitocode.model.Exam;
import com.mitocode.service.ConsultService;

@RestController
@RequestMapping("/consults")
public class ConsultController {
	
	@Autowired
	private ConsultService consultService;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping
	public ResponseEntity<List<ConsultDTO>> findAll() {
		List<ConsultDTO> consults = consultService.findAll().stream().map(p -> mapper.map(p, ConsultDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<>(consults, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ConsultDTO> findById(@PathVariable("id") Integer id) {
		Consult consult = consultService.findById(id);
		
		if (consult == null) {
			throw new ModelNotFoundException("ID NOT FOUND: " + id);
		}
		
		return new ResponseEntity<>(mapper.map(consult, ConsultDTO.class), HttpStatus.OK);
	}
	
	@GetMapping("/hateoas/{id}")
	public EntityModel<ConsultDTO> findByIdHateoas(@PathVariable("id") Integer id) {
		Consult consult = consultService.findById(id);
		
		if (consult == null) {
			throw new ModelNotFoundException("ID NOT FOUND: " + id);
		}
		
		EntityModel<ConsultDTO> resource = EntityModel.of(mapper.map(consult, ConsultDTO.class));
		WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
		resource.add(link1.withRel("Consult-info1"));
		
		return resource;
	}
	
	@PostMapping
	public ResponseEntity<Consult> save(@Valid @RequestBody ConsultListExamDTO consultExamsDto) {
		Consult consult = mapper.map(consultExamsDto.getConsult(), Consult.class);
		//List<Exam> exams = consultExamsDto.getExams().stream().map(e -> mapper.map(e, Exam.class)).collect(Collectors.toList());
		List<Exam> exams = mapper.map(consultExamsDto.getExams(), new TypeToken<List<Exam>>(){}.getType());
		
		Consult addedConsult = consultService.saveTransactional(consult, exams);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(addedConsult.getIdConsult()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Consult> update(@Valid @RequestBody ConsultDTO consultDto) {
		Consult updatedConsult = consultService.update(mapper.map(consultDto, Consult.class));
		return new ResponseEntity<>(updatedConsult, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		consultService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
