package lt.management.oms.repository;

import lt.management.oms.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface that extends {@link JpaRepository} for class {@link Role}.
 *
 * @author Edgaras Venzlauskas
 * @version 1.0
 */

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);


}
