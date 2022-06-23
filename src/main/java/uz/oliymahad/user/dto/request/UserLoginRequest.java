package uz.oliymahad.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.oliymahad.user.annotation.phone_num_constraint.PhoneNumber;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequest {


    @NotBlank @PhoneNumber
    private String phoneNumber;

    @NotBlank @Size(min = 4, max = 16) String password;
}
