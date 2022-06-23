package uz.oliymahad.course.repository.group;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.oliymahad.course.entity.group.GroupEntity;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
//    List<GroupEntity> findGroupEntitiesByCreatedDateBetween(Date createdDate, Date createdDate2) ;
//    List<GroupEntity> findGroupEntitiesByCreatedDate_Month(int createdDate_month);
}
