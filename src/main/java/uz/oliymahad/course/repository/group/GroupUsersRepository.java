package uz.oliymahad.course.repository.group;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.oliymahad.course.entity.group.GroupUsersEntity;


public interface GroupUsersRepository extends JpaRepository<GroupUsersEntity, Long> {
}
