package uz.oliymahad.user.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSectionDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String phoneNumber;
}
