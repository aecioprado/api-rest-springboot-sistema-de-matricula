package gestao.matriculas.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gestao.matriculas.domain.Materia;
import gestao.matriculas.service.IMateriaService;

/**
 * @author aécio
 *
 */

// A controller disponibiliza a aplicacao ao client
// É um Bean
@RestController

// RequestMapping define o end-point de acesso (mapeamento da requisição)
@RequestMapping("/materias")
public class MateriaController {
	
	// Injeção de dependência gerenciada pela Springboot
	@Autowired
	private IMateriaService materiaService;
	

	// O METODO listarMateriais() VAI RETORNAR UMA LISTA DE TODAS AS MATERIAS JA CADASTRADAS
	// ResponseEntity = Resposta-Entidade é uma extensão da classe HttpEntity (pesquisar)
	// HttpEntity representa uma request ou responde de uma entity podendo definir header e body
	// o metodo listarMaterias() é do tipo "resposta-entidade", que
	// deve retornar uma lista de objetos MateriaEntity(a entidade)
	// o ResponseEntity vai adicionar um Status code ao corpo da resposta.
	
	// MÉTODO GET (OBTER)
	@GetMapping
	public ResponseEntity<List<Materia>> listarMaterias() {
		return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.listarMaterias());
	}

	@GetMapping("usuario")
	public String usuarioLogado() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		}
		return authentication.getName();
	}

	// MÉTODO GET (OBTER)
	// MÉTODO consultarPorId() VAI RETORNAR UM OBJETO MATERIA JA CADASTRADO DE ACORDO COM O ID PASSADO COMO PARAMETRO NA REQUISICAO
	// END-PONT (/{id}), SENDO ID O PARAMETRO DA REQUISICAO 
	@GetMapping("/{id}")
	public ResponseEntity<Materia> consultarMateriaPorId(@PathVariable Long id) {
		// @PathVariable indica que o parametro do método esta obrigado/limitado a vir do caminho padrao estabelecido pela uri mapeada
		return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.consultarPorId(id));
	}

	// MÉTODO POST (POSTAR/SALVAR)
	// O MÉTODO POST É COMUMENTE USADO PARA SALVAR/PERSISTIR UMA REQUISICAO 
	// O MÉTODO salvarMateria() recebe como corpo da requisicao (RequestBody) um objeto materia do tipo MateriaEntity
	
	@PostMapping 
	public ResponseEntity<Boolean> salvarMateria(@RequestBody Materia materia) { //RequestBody -> corpo da requisição
		
			return ResponseEntity.status(HttpStatus.CREATED).body(this.materiaService.salvarMateria(materia)); // corrigir o status
	}

	// MÉTODO DELETE (EXCLUIR)
	// O MÉTODO DEVE EXCLUIR UMA MATERIA JA CADASTRADA CONFORME O ID PASSADO COMO PARAMETRO NA URI
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> excluirMateria(@PathVariable Long id) {
		
			return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.excluirMateria(id));
		
	}
	
	// MÉTODO PUT (ATUALIZAR)
	// O MÉTODO PUT DEVE ATUALIZAR UM OBJETO MATERIA DO TIPO MATERIAENTITY,
	// ESSE OBJETO SERA IDENTIFICADO PELA ID E PREVIAMENTE MANIPULADO PELA
	// CLASSE DE SERVICO (CRIANDO UMA INSTANCIA NOVA DE MATERIA) QUE VAI ATUALIZAR TODOS OS ATRIBUTOS DEFINIDOS PELO USUARIO NA REQUISICAO
	
	@PutMapping
	public ResponseEntity<Boolean> atualizarMateria(@RequestBody Materia materia) {																				// materiaEntity
		
			return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.atualizar(materia));

	}

}
