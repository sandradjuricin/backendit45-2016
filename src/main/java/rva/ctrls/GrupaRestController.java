package rva.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import io.swagger.annotations.ApiOperation;
import rva.jpa.Grupa;
import rva.reps.GrupaRepository;


//@Api(tags={"Grupa CRUD operacije"})
@CrossOrigin
@RestController
public class GrupaRestController {

	@Autowired
	private GrupaRepository grupaRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@ApiOperation(value="Vraca kolekciju grupi iz baze")
	@GetMapping("grupa")
	public Collection<Grupa> getGrupe(){
		return grupaRepository.findAll();
	}
	
	@ApiOperation(value="Vraca odredjenu grupu iz baze")
	@GetMapping("grupa/{id}")
	public Grupa getGrupa(@PathVariable Integer id) {
	return grupaRepository.getOne(id);
	}
	
	
	@ApiOperation(value="Vraća kolekciju grupa na osnovu oznake iz baze podataka")
	@GetMapping("grupaOznaka/{oznaka}")
	public Collection<Grupa> getGrupaByOznaka(@PathVariable("oznaka") String oznaka) {
		return grupaRepository.findByOznakaContainingIgnoreCase(oznaka);
	}
	
	@ApiOperation(value="Brise odredjenu grupu iz baze")
	@Transactional
	@DeleteMapping("grupa/{id}")
	public ResponseEntity<Grupa> deleteGrupa(@PathVariable("id") Integer id) {
		if (!grupaRepository.existsById(id))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		grupaRepository.deleteById(id);
		jdbcTemplate.execute("delete from student where grupa = " + id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value="Dodaje grupu u bazu")
	@PostMapping("grupa")
	public ResponseEntity<HttpStatus> insertGrupa (@RequestBody Grupa grupa) {
	
		grupaRepository.save(grupa);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	

	@ApiOperation(value="Edituje grupu iz baze")
	@PutMapping("grupa")
	public ResponseEntity<HttpStatus> editGrupa (@RequestBody Grupa grupa){
		if(grupaRepository.existsById(grupa.getId())) {
			grupaRepository.save(grupa);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);

		}
		else
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);			
	}

}