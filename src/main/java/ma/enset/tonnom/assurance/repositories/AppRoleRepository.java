package ma.enset.tonnom.assurance.repositories;

import ma.enset.tonnom.assurance.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByRoleName(String roleName);
}
