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

import com.mitocode.dto.SpecialtyDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Specialty;
import com.mitocode.service.SpecialtyService;

@RestController
@RequestMapping("/specialties")
public class SpecialtyController {
	
	@Autowired
	private SpecialtyService specialtyService;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping
	public ResponseEntity<List<SpecialtyDTO>> findAll() {
		List<SpecialtyDTO> specialtys = specialtyService.findAll().stream().map(p -> mapper.map(p, SpecialtyDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<>(specialtys, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SpecialtyDTO> findById(@PathVariable("id") Integer id) {
		Specialty specialty = specialtyService.findById(id);
		
		if (specialty == null) {
			throw new ModelNotFoundException("ID NOT FOUND: " + id);
		}
		
		return new ResponseEntity<>(mapper.map(specialty, SpecialtyDTO.class), HttpStatus.OK);
	}
	
	@GetMapping("/hateoas/{id}")
	public EntityModel<SpecialtyDTO> findByIdHateoas(@PathVariable("id") Integer id) {
		Specialty specialty = specialtyService.findById(id);
		
		if (specialty == null) {
			throw new ModelNotFoundException("ID NOT FOUND: " + id);
		}
		
		EntityModel<SpecialtyDTO> resource = EntityModel.of(mapper.map(specialty, SpecialtyDTO.class));
		WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
		resource.add(link1.withRel("Specialty-info1"));
		
		return resource;
	}
	
	@PostMapping
	public ResponseEntity<Specialty> save(@Valid @RequestBody SpecialtyDTO SpecialtyDto) {
		Specialty addedSpecialty = specialtyService.save(mapper.map(SpecialtyDto, Specialty.class));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(addedSpecialty.getIdSpecialty()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Specialty> update(@Valid @RequestBody SpecialtyDTO SpecialtyDto) {
		Specialty updatedSpecialty = specialtyService.update(mapper.map(SpecialtyDto, Specialty.class));
		return new ResponseEntity<>(updatedSpecialty, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		specialtyService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
