package pl.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.model.storage.File;

@Repository
public interface FileRepository extends JpaRepository<File, String> {
}
