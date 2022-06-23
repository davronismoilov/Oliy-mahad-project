package uz.oliymahad.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataPageRequest {
    private Integer page;

    private Integer size;

    private String direction;

    private String[] properties;
}
