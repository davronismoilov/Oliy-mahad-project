package uz.oliymahad.user.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.oliymahad.user.model.entity.RoleEntity;
import uz.oliymahad.user.model.entity.UserRegisterDetails;
import uz.oliymahad.user.model.enums.EAuthProvider;

import java.sql.Timestamp;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDataResponse {

    private Long id;

    private String username;

    private String phoneNumber;

    private String email;

    private EAuthProvider provider;

    private String imageUrl;

    private Set<RoleEntity> roles;

    private UserRegisterDetails userRegisterDetails;

    private Timestamp createdAt;

}
