package uz.oliymahad.course.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.oliymahad.course.dto.request.FilterQueueForGroupsDTO;
import uz.oliymahad.course.dto.request.QueueDto;
import uz.oliymahad.course.dto.response.*;
import uz.oliymahad.course.entity.course.CourseEntity;
import uz.oliymahad.course.entity.quequeue.QueueEntity;
import uz.oliymahad.course.entity.quequeue.Status;
import uz.oliymahad.course.repository.CourseRepository;
import uz.oliymahad.course.repository.QueueRepository;
import uz.oliymahad.user.dto.request.UsersIDSRequest;
import uz.oliymahad.user.dto.response.UserDataResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QueueService implements BaseService<QueueDto, Long, QueueEntity, Pageable>, Response {

    private final QueueRepository queueRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

//    private final UserFeign userFeign;


    @Override
    public RestAPIResponse add(QueueDto queueDto) {
//        boolean exist = userFeign.isExist(queueDto.getUserId());
//        if (!exist) {
//            return new ApiResponse<>(USER + NOT_FOUND,false);
//        }
        Optional<CourseEntity> optionalCourse = courseRepository.findById(queueDto.getCourseId());
        if (optionalCourse.isEmpty()) {
            return new RestAPIResponse(COURSE + NOT_FOUND, false,404);
        }
        QueueEntity queueEntity = modelMapper.map(queueDto, QueueEntity.class);
        queueEntity.setCourse(optionalCourse.get());
        queueEntity.setStatus(Status.PENDING);
        queueRepository.save(queueEntity);
        return new RestAPIResponse(SUCCESSFULLY_SAVED, true,200);
    }

    @Override
    public RestAPIResponse getList(Pageable page) {
        return new RestAPIResponse(DATA_LIST, true, 200,queueRepository.findAll(page));

    }

    @Override
    public RestAPIResponse get(Long id) {
        Optional<QueueEntity> optionalQueue = queueRepository.findById(id);
        if (optionalQueue.isEmpty()) {
            return new RestAPIResponse(QUEUE + NOT_FOUND, false,404);
        }
        QueueDto queueDto = modelMapper.map(optionalQueue.get(), QueueDto.class);
        return new RestAPIResponse(QUEUE, true, 200,queueDto);
    }

    @Override
    public RestAPIResponse delete(Long id) {
        Optional<QueueEntity> optionalQueue = queueRepository.findById(id);
        if (optionalQueue.isEmpty()) {
            return new RestAPIResponse(QUEUE + NOT_FOUND, false,404);
        }
        queueRepository.delete(optionalQueue.get());
        return new RestAPIResponse(SUCCESSFULLY_DELETED, true,200);
    }

    @Override
    public RestAPIResponse edit(Long id, QueueDto queueDto) {
        Optional<QueueEntity> optionalQueue = queueRepository.findById(id);
        if (optionalQueue.isEmpty()) {
            return new RestAPIResponse(QUEUE + NOT_FOUND, false,404);
        }
        QueueEntity queueEntity = optionalQueue.get();
        if (queueDto.getAppliedDate() == null)
            queueDto.setAppliedDate(queueEntity.getAppliedDate());
        modelMapper.map(queueDto, queueEntity);
        queueRepository.save(queueEntity);
        return new RestAPIResponse(SUCCESSFULLY_UPDATED, true,200);
    }

    public RestAPIResponse getUserCourseQueue(Long userId, Long courseId) {
        List<Long> userCourseQueue = queueRepository.getUserCourseQueue(userId, courseId);
        return new RestAPIResponse(SUCCESS, true,200,userCourseQueue);
    }

    public RestAPIResponse getUsersByFilter(FilterQueueForGroupsDTO filterQueueDTO) {
        List<Long> users = queueRepository.filterByCourseStatusGenderLimitForGroups(filterQueueDTO.getCourseId(), filterQueueDTO.getStatus(), filterQueueDTO.getGender(), filterQueueDTO.getLimit());
        return new RestAPIResponse(SUCCESS, true,200, users);
    }


    public RestAPIResponse getQueueByFilter(Long userId, String gender, String status, Long courseId, String appliedDate) {
        String appliedDateAfter = null;
        if (appliedDate != null) {
            appliedDateAfter = getDayAfterDay(appliedDate);
        }
        List<QueueEntity> queueByFilter = queueRepository.getQueueByFilter(userId, gender, status, courseId);
        return new RestAPIResponse(SUCCESS, true, 200,queueByFilter);

    }


    private String getDayAfterDay(String day) {
        String sDay = day.substring(0, 10);
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(sDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long l = date.getTime() + 86_400_000;
        Date date1 = new Date(l);
        String afterDay = new SimpleDateFormat("yyyy-MM-dd").format(date1);
        return afterDay;
    }

    public List<UserDataResponse> getUserDetailsInQueue(UsersIDSRequest usersIDSRequest) {
//        return userFeign.getUsers(usersIDSRequest);
        return  null;
    }

    public List<QueueResponse> createQueueResponseForBackend(List<QueueUserDetailsDTO> list, List<QueueEntity> queues){
        List<QueueResponse> result = new ArrayList<>();
        for (QueueUserDetailsDTO queueUserDetailsDTO : list) {
            UserDataResponse userData = queueUserDetailsDTO.getUserData();
            result.add(new QueueResponse(
                    queueUserDetailsDTO.getId(),
                    queueUserDetailsDTO.getCourse().getName(),
                    userData.getId(),
                    userData.getPhoneNumber(),
                    userData.getEmail(),
                    userData.getUserRegisterDetails().getFirstName(),
                    userData.getUserRegisterDetails().getLastName(),
                    queueUserDetailsDTO.getAppliedDate(),
                    null,
                    queueUserDetailsDTO.getStatus()
            ));
        }
        return result;
    }

    public List<UserDataResponse> getUserDataFromFeign(Pageable pageable, List<QueueEntity> list){
        List<Long> userIds = new ArrayList<>();
        for (QueueEntity q : list) {
            userIds.add(q.getUserId());
        }
//        List<UserDataResponse> users = userFeign.getUsers(new UsersIDSRequest(userIds));
//        return users;
        return null;
    }

    public RestAPIResponse getQueueDetails(Pageable pageRequest) throws JsonProcessingException {
        Page<QueueEntity> queueEntityPage = queueRepository.findAll(pageRequest);

        List<UserDataResponse> usersFromFeign = getUserDataFromFeign(pageRequest,queueEntityPage.getContent());
        List<QueueUserDetailsDTO> queueUserDetailsDTOS = creatingQueueUserDetailsResponse(queueEntityPage.getContent(), usersFromFeign);
        List<QueueResponse> response = createQueueResponseForBackend(queueUserDetailsDTOS, queueEntityPage.getContent());
        QueueUserPageableResponse pageableResponse = modelMapper.map(queueEntityPage, QueueUserPageableResponse.class);
        pageableResponse.setContent(response);

//        String s = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(pageableResponse);
//        System.out.println(s);
        return new RestAPIResponse("Queue List",true,200,pageableResponse);
    }


    private List<QueueUserDetailsDTO> creatingQueueUserDetailsResponse(List<QueueEntity> queues, List<UserDataResponse> users) {
        List<QueueUserDetailsDTO> queueUserDetailsDTOS = new ArrayList<>();

        queues.forEach(queue -> {
            queueUserDetailsDTOS.add(modelMapper.map(queue, QueueUserDetailsDTO.class));
        });
        int index = 0;
        for (QueueEntity queue : queues) {
            UserDataResponse userDataResponse =
                    users.parallelStream().filter(u -> u.getId() == queue.getUserId()).findFirst().orElse(new UserDataResponse());
            queueUserDetailsDTOS.get(index++).setUserData(userDataResponse);
        }

        return queueUserDetailsDTOS;
    }

}
