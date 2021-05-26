package nl.lotrac.bv.repository;

import nl.lotrac.bv.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, String> {
}
