package uz.oliymahad.course.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilterQueueForGroupsDTO {
    private Long courseId;
    private String status;
    private String gender;
    private Long limit;

}
