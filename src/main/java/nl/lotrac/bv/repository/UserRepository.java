package nl.lotrac.bv.repository;

import nl.lotrac.bv.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User getUserByUsername(String username);

}
