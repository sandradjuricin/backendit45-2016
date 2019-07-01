package rva.reps;

import rva.jpa.Grupa;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;


public interface GrupaRepository  extends JpaRepository<Grupa, Integer> {
	Collection<Grupa> findByOznakaContainingIgnoreCase(String oznaka);

}
