package net.codejava.apik;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.codejava.user.ERole;
import net.codejava.user.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	 Optional<Role> findByName(ERole name);
	 Role findByName(String name);

}
