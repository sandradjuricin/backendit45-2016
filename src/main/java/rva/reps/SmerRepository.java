package rva.reps;

import rva.jpa.Smer;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SmerRepository  extends JpaRepository<Smer, Integer> {
	Collection<Smer> findByNazivContainingIgnoreCase(String naziv);

}
