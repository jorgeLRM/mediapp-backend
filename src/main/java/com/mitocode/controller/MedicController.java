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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.mitocode.dto.MedicDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Medic;
import com.mitocode.service.MedicService;

@RestController
@RequestMapping("/medics")
@CrossOrigin(origins = "*")
public class MedicController {
	
	@Autowired
	private MedicService MedicService;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping
	public ResponseEntity<List<MedicDTO>> findAll() {
		List<MedicDTO> medics = MedicService.findAll().stream().map(p -> mapper.map(p, MedicDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<>(medics, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MedicDTO> findById(@PathVariable("id") Integer id) {
		Medic medic = MedicService.findById(id);
		
		if (medic == null) {
			throw new ModelNotFoundException("ID NOT FOUND: " + id);
		}
		
		return new ResponseEntity<>(mapper.map(medic, MedicDTO.class), HttpStatus.OK);
	}
	
	@GetMapping("/hateoas/{id}")
	public EntityModel<MedicDTO> findByIdHateoas(@PathVariable("id") Integer id) {
		Medic medic = MedicService.findById(id);
		
		if (medic == null) {
			throw new ModelNotFoundException("ID NOT FOUND: " + id);
		}
		
		EntityModel<MedicDTO> resource = EntityModel.of(mapper.map(medic, MedicDTO.class));
		WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
		resource.add(link1.withRel("Medic-info1"));
		
		return resource;
	}
	
	@PostMapping
	public ResponseEntity<Medic> save(@Valid @RequestBody MedicDTO medicDto) {
		Medic addedMedic = MedicService.save(mapper.map(medicDto, Medic.class));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(addedMedic.getIdMedic()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Medic> update(@Valid @RequestBody MedicDTO medicDto) {
		Medic updatedMedic = MedicService.update(mapper.map(medicDto, Medic.class));
		return new ResponseEntity<>(updatedMedic, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		MedicService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
