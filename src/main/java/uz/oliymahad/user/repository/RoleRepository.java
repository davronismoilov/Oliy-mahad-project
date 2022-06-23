package uz.oliymahad.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.oliymahad.user.model.entity.RoleEntity;
import uz.oliymahad.user.model.enums.ERole;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    Optional<RoleEntity> findByRoleName(ERole eRole);
}
