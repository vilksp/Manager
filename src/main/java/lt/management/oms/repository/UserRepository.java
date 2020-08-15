package lt.management.oms.repository;

import lt.management.oms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Repository interface that extends {@link JpaRepository} for class {@link User}.
 *
 * @author Edgaras Venzlauskas
 * @version 1.0
 */

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);
}