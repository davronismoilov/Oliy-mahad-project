package uz.oliymahad.user.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.oliymahad.user.audit.Auditable;
import uz.oliymahad.user.model.enums.EGender;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user_register_details",
        indexes = {
                @Index(name = "firstName", columnList = "firstName DESC"),
                @Index(name = "middleName", columnList = "middleName DESC"),
                @Index(name = "lastName", columnList = "lastName DESC"),
                @Index(name = "birthDate", columnList = "birthDate DESC"),
        }
)
public class UserRegisterDetails extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    //    @Column(nullable = false, length = 50)
    private String firstName;

    //    @Column(nullable = false, length = 50)
    private String middleName;

    //    @Column(nullable = false, length = 50)
    private String lastName;

    //    @Column(nullable = false)
    //    @Enumerated(EnumType.STRING)
    private EGender gender;

    //    @Column(nullable = false, unique = true)
    private String passport;

    //    @Column(nullable = false)
    private Date birthDate;

}
