package uz.oliymahad.course.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.oliymahad.course.dto.group.GroupRequestDto;
import uz.oliymahad.course.dto.response.RestAPIResponse;
import uz.oliymahad.course.service.GroupService;

import static uz.oliymahad.course.controller.BaseController.API;


@RestController
@RequestMapping(API + "/course/group")
@RequiredArgsConstructor
public class GroupController implements BaseController{

    private final GroupService groupService ;

    @PostMapping("/add")
    public ResponseEntity<?> add (@RequestBody GroupRequestDto groupRequestDto) {
        RestAPIResponse apiResponse = groupService.addGroup(groupRequestDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping("/getAllGroups")
    public ResponseEntity<?> getAllGroups () {
        RestAPIResponse apiResponse = groupService.getAllGroups();
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping("/getGroups")
    public RestAPIResponse getGroupPage(Pageable pageable) {
        return groupService.getGroupPage(pageable);
    }

}
