package uz.oliymahad.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.oliymahad.user.dto.request.UsersIDSRequest;
import uz.oliymahad.user.dto.response.UserDataResponse;
import uz.oliymahad.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/feign")
@RequiredArgsConstructor
public class FeignController {

    private final UserService userService;
    @PostMapping()
    public List<UserDataResponse> getUserByIds(
            @RequestBody UsersIDSRequest idsRequest
    ){
        return userService.getUsersByIds(idsRequest);
    }
}
