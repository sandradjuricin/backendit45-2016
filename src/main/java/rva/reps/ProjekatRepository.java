package rva.reps;
import rva.jpa.Projekat;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjekatRepository  extends JpaRepository<Projekat, Integer> {
	Collection<Projekat> findByNazivContainingIgnoreCase(String naziv);

}
