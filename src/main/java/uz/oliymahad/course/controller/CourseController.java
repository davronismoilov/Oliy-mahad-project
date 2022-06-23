package uz.oliymahad.course.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.oliymahad.course.dto.request.CourseDto;
import uz.oliymahad.course.dto.response.RestAPIResponse;
import uz.oliymahad.course.service.CourseService;

import static uz.oliymahad.course.controller.BaseController.API;


@RestController
@RequestMapping(API+"/course")
@RequiredArgsConstructor
public class CourseController implements BaseController{

    private final CourseService courseService;

    @PostMapping(ADD)
    public ResponseEntity<?> addCourse (@RequestBody CourseDto courseDto) {
        RestAPIResponse apiResponse = courseService.add(courseDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping(LIST)
    public ResponseEntity<?> getCourseList (
            @RequestBody Pageable pageable
    ) {
        return ResponseEntity.ok(courseService.getList(pageable));
    }

    @GetMapping(GET+LIST)
    public RestAPIResponse getCourses (Pageable pageable) {
        return courseService.getLists(pageable);
    }

    @GetMapping(GET + "/{id}")
    public ResponseEntity<?> getCourse (@PathVariable Long id) {
        RestAPIResponse apiResponse = courseService.get(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @GetMapping(GET + "{name}")
    public ResponseEntity<?> getCourseByName (@RequestParam(name = "name") String name) {
        RestAPIResponse apiResponse = courseService.getByName(name);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @PutMapping(UPDATE+"/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody CourseDto courseDto) {
        RestAPIResponse apiResponse = courseService.edit(id, courseDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @DeleteMapping(DELETE+"/{id}")
    public ResponseEntity<?> deleteCourse (@PathVariable Long id) {
        RestAPIResponse apiResponse = courseService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND).body(apiResponse);
    }
}
