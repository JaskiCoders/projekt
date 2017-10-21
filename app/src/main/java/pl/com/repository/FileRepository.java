package pl.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.model.File;
import pl.com.model.User;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
}
