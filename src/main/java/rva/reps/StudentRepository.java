package rva.reps;
import rva.jpa.Student;
import rva.jpa.Projekat;
import rva.jpa.Grupa;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;



public interface StudentRepository extends JpaRepository<Student, Integer> {
	Collection<Student> findByProjekat(Projekat p);
	Collection<Student> findByGrupa(Grupa g);

	Collection<Student> findByImeContainingIgnoreCase(String ime);
	Collection<Student> findByPrezimeContainingIgnoreCase(String prezime);


	

	
}
