package cl.labs.conversion.repository;

import cl.labs.conversion.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
