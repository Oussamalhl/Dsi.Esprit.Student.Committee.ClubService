package dsi.esprit.tn.repository;

import dsi.esprit.tn.Models.clubFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface clubFileRepository extends JpaRepository<clubFile, Long> {

}
