package uz.oliymahad.course.entity.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.oliymahad.course.entity.BaseEntity;

import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class CourseEntity extends BaseEntity {

    private String name;

    private String description;

    private double price;

    private float duration;
}
