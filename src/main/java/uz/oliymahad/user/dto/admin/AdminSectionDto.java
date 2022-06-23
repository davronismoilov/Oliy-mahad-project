package uz.oliymahad.user.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminSectionDto {
    private boolean delete;
    private boolean info;
    private boolean update;
    private List<String> headers;
    private Object body;
}
