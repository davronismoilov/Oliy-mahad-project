package uz.oliymahad.user.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsersIDSRequest {
    List<Long> ids;
}
