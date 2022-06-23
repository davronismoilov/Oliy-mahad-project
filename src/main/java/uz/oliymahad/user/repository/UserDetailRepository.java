package uz.oliymahad.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.oliymahad.user.model.entity.UserEntity;
import uz.oliymahad.user.model.entity.UserRegisterDetails;

import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<UserRegisterDetails, Long> {
    Optional<UserRegisterDetails> findByUser(UserEntity user);
    Optional<UserRegisterDetails> findByUserId(Long id);

}
