package com.rasmoo.cliente.escola.controller;

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

import com.rasmoo.cliente.escola.entity.MateriaEntity;
import com.rasmoo.cliente.escola.service.IMateriaService;

/**
 * @author aécio
 *
 */
@RestController
@RequestMapping("/materia")
public class MateriaController {

	@Autowired
	private IMateriaService materiaService;

	// MÉTODO LISTAR TODOS
	@GetMapping
	public ResponseEntity<List<MateriaEntity>> listarMaterias() {
		return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.listarMaterias());
	}

	// MÉTODO CONSULTA POR ID
	@GetMapping("/{id}")
	public ResponseEntity<MateriaEntity> consultarMateria(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.consultaPorId(id));
	}

	// MÉTODO SALVAR
	@PostMapping
	public ResponseEntity<Boolean> cadastrarMateria(@RequestBody MateriaEntity materia) {
		
			return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.cadastar(materia)); // corrigir o status
	}

	// MÉTODO DELETAR
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deletarMateria(@PathVariable Long id) {
		
			return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.excluir(id));
		
	}
	
	// MÉTODO ATUALIZAR
	@PutMapping
	public ResponseEntity<Boolean> atualizarMateria(@RequestBody MateriaEntity materia) {																				// materiaEntity
		
			return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.atualizar(materia));

	}

}
