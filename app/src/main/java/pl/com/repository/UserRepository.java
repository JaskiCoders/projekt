package pl.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByLogin(String login);
}
