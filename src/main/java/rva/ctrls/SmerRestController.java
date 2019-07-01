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
import rva.jpa.Smer;
import rva.reps.SmerRepository;


@Api(tags={"Smer CRUD operacije"})
@CrossOrigin
@RestController
public class SmerRestController {
	
	@Autowired
	private SmerRepository smerRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@ApiOperation(value="Vraća kolekciju smerova iz baze podataka")
	@GetMapping("smer")
	public Collection<Smer> getSmerove(){
		
		return smerRepository.findAll();
	}
	
	@ApiOperation(value="Vraća jedan smer na osnovu ID iz baze podataka")
	@GetMapping("smer/{id}")
	public Smer getSmer(@PathVariable Integer id) {
	return smerRepository.getOne(id);
	}
	
	
	@ApiOperation(value="Vraća kolekciju smerova na osnovu naziva iz baze podataka")
	@GetMapping("smerNaziv/{naziv}")
	public Collection<Smer> getSmerByNaziv(@PathVariable("naziv") String naziv) {
		return smerRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	
	@ApiOperation(value="Brise smer prema ID iz baze podataka")
	@DeleteMapping("smer/{id}")
	public ResponseEntity<Smer> deleteSmer(@PathVariable("id") Integer id) {
		if (!smerRepository.existsById(id))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		smerRepository.deleteById(id);
		if (id == -100)
			jdbcTemplate.execute("INSERT INTO \"smer\"(\"id\", \"naziv\", \"oznaka\")"
					+ "VALUES(-100, 'Naziv TEST', 'Oznaka TEST');");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	

	@ApiOperation(value = "Upisuje smer u bazu podataka")
	@PostMapping("smer")
	public ResponseEntity<HttpStatus> insertsmer (@RequestBody Smer smer) {
		smerRepository.save(smer);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	

	@ApiOperation(value = "Modifikuje postojeci smer u bazi podataka")
	@PutMapping("smer")
	public ResponseEntity<HttpStatus> editsmer (@RequestBody Smer smer){
		if(smerRepository.existsById(smer.getId())) {
			smerRepository.save(smer);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);

		}
		else
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);			
	}

}
