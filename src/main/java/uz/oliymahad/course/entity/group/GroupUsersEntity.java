package uz.oliymahad.course.entity.group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.oliymahad.course.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "group_users_entity")
public class GroupUsersEntity extends BaseEntity {

    @Column(name = "user_id")
    private Long userId ;

    @ManyToOne
    private GroupEntity group ;

}
