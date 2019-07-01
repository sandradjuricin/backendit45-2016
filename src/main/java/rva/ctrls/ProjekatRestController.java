package rva.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rva.jpa.Projekat;
import rva.reps.ProjekatRepository;

@Api(tags={"Projekat CRUD operacije"})
@CrossOrigin
@RestController
public class ProjekatRestController {
	
	@Autowired
	private ProjekatRepository projekatRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@ApiOperation(value="Vraća kolekciju projekata iz baze podataka")
	@GetMapping("projekat")
	public Collection<Projekat> getProjekte(){
		
		return projekatRepository.findAll();
	}
	
	@ApiOperation(value="Vraća studenata na osnovu njegovog ID iz baze podataka")
	@GetMapping("projekat/{id}")
	public Projekat getProjekat(@PathVariable Integer id) {
	return projekatRepository.getOne(id);
	}
	
	@ApiOperation(value="Vraća kolekciju projekata na osnovu naziva iz baze podataka")
	@GetMapping("projekatNaziv/{naziv}")
	public Collection<Projekat> getProjekatByNaziv(@PathVariable("naziv") String naziv) {
		return projekatRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@ApiOperation(value="Brise projekat na osnovu ID iz baze podataka")
	@DeleteMapping("projekat/{id}")
	public ResponseEntity<HttpStatus> deleteProjekat(@PathVariable Integer id) {
		if (projekatRepository.existsById(id)) {
			projekatRepository.deleteById(id);
			if(id == -100)
				jdbcTemplate.execute("INSERT INTO \"projekat\"(\"id\", \"naziv\", \"oznaka\", \"opis\")"
						+ "VALUES(-100, 'Naziv TEST', 'Oznaka TEST', 'Opis TEST')");

			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
	
	

	@ApiOperation(value="Upisuje novi projekat u bazu podataka")
	@PostMapping("projekat")
	public ResponseEntity<HttpStatus> insertProjekat (@RequestBody Projekat projekat) {
		projekatRepository.save(projekat);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	

	@ApiOperation(value = "Modifikuje postojeci projekat u bazi podataka")
	@PutMapping("projekat")
	public ResponseEntity<HttpStatus> editProjekat (@RequestBody Projekat projekat){
		if(projekatRepository.existsById(projekat.getId())) {
			projekatRepository.save(projekat);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);

		}
		else
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);			
	}

}
