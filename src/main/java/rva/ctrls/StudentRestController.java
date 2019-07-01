package rva.ctrls;
import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import rva.jpa.Grupa;
import rva.jpa.Student;
import rva.reps.StudentRepository;
import rva.reps.ProjekatRepository;
import rva.reps.GrupaRepository;

@CrossOrigin
@Api(tags={"Student CRUD operacije"})
@RestController
public class StudentRestController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private ProjekatRepository projekatRepository;
	
	@Autowired
	private GrupaRepository grupaRepository;

	
	@ApiOperation(value="Vraća kolekciju studenata iz baze podataka")
	@GetMapping("/student")
	public Collection<Student> getStudente(){
		
		return studentRepository.findAll();
	}
	
	@ApiOperation(value="Vraća jednog studenta na osnovu ID iz baze podataka")
	@GetMapping("/student/{id}")
	public Student getStudent(@PathVariable Integer id) {
	return studentRepository.getOne(id);
	}
	
	@ApiOperation(value="Vraća kolekcija studenata na osnovu projekta na kome rade")
	@GetMapping(value = "studentiNaProjektu/{id}")
	public Collection<Student> projekatStudentId(@PathVariable("id") int id){
		Projekat p = projekatRepository.getOne(id);
		return studentRepository.findByProjekat(p);
	}
	

	@ApiOperation(value="Vraća kolekcija studenata na osnovu grupe")
	@GetMapping(value = "studentiGrupa/{id}")
	public Collection<Student> grupaStudentId(@PathVariable("id") int id){
		Grupa g = grupaRepository.getOne(id);
		return studentRepository.findByGrupa(g);
	}
	
	
	
	@ApiOperation(value="Vraća kolekciju studenata osnovu imena iz baze podataka")
	@GetMapping("studentIme/{ime}")
	public Collection<Student> getStudentByIme(@PathVariable("ime") String ime) {
		return studentRepository.findByImeContainingIgnoreCase(ime);
	}
	
	@ApiOperation(value="Vraća kolekciju studenata na osnovu prezimena iz baze podataka")
	@GetMapping("studentPrezime/{prezime}")
	public Collection<Student> getStudentByPrezime(@PathVariable("prezime") String prezime) {
		return studentRepository.findByPrezimeContainingIgnoreCase(prezime);
	}
	
	@ApiOperation(value="Brise studenata na osnovu ID iz baze podataka")
	@DeleteMapping("student/{id}")
	public ResponseEntity<Student> deleteStudent(@PathVariable("id") Integer id) {
		if (!studentRepository.existsById(id))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		studentRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value="Upisuje studenta novog u bazu podataka")
	@PostMapping("student")
	public ResponseEntity<HttpStatus> insertStudent (@RequestBody Student student) {
		studentRepository.save(student);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	

	@ApiOperation(value = "Modifikuje postojeceg studenta u bazi podataka")
	@PutMapping("student")
	public ResponseEntity<HttpStatus> editStudent (@RequestBody Student student){
		if(studentRepository.existsById(student.getId())) {
			studentRepository.save(student);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);

		}
		else
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);			
	}

	

}
