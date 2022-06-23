package uz.oliymahad.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.oliymahad.course.entity.course.CourseEntity;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity,Long> {
    boolean existsByName(String name);
    Optional<CourseEntity> findByName(String name);
    boolean existsById(String id);
}
