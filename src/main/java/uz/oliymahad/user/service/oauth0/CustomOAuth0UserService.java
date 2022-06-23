package uz.oliymahad.user.service.oauth0;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.oliymahad.user.dto.request.UserLoginRequest;
import uz.oliymahad.user.dto.request.UserRegisterRequest;
import uz.oliymahad.user.exception.custom_ex_model.UserAlreadyRegisteredException;
import uz.oliymahad.user.exception.custom_ex_model.UserInvalidPasswordException;
import uz.oliymahad.user.exception.custom_ex_model.UserNotFoundException;
import uz.oliymahad.user.model.entity.RoleEntity;
import uz.oliymahad.user.model.entity.UserEntity;
import uz.oliymahad.user.model.enums.ERole;
import uz.oliymahad.user.repository.UserRepository;
import uz.oliymahad.user.security.jwt.JWTokenProvider;
import uz.oliymahad.user.security.jwt.UserDetailsServiceImpl;
import uz.oliymahad.user.security.jwt.payload.response.JWTokenResponse;
import uz.oliymahad.user.service.SectionService;

import java.util.HashSet;

import static org.springframework.http.HttpStatus.OK;

@Service
@RequiredArgsConstructor
public class CustomOAuth0UserService {

    private final static Logger logger = LoggerFactory.getLogger(CustomOAuth0UserService.class);
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsService;
    private final ModelMapper modelMapper;
    private final JWTokenProvider jwTokenProvider;
    private final SectionService sectionService;

    public JWTokenResponse registerUser(UserRegisterRequest userRegisterRequest)
            throws UserAlreadyRegisteredException {
        String[] tokens = new String[2];
        UserEntity user = new UserEntity();
        try {
            userRegisterRequest.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
            user.setRoles(new HashSet<>() {{
                add(new RoleEntity(1, ERole.ROLE_USER));
            }});
            modelMapper.map(userRegisterRequest, user);
            UserEntity entity = repository.save(user);
            tokens = jwTokenProvider.generateJwtTokens(entity);

        } catch (
                IllegalArgumentException | ClassCastException | IllegalStateException |
                InvalidDataAccessApiUsageException e
        ) {
            logger.error(e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw new UserAlreadyRegisteredException(
                    "User already registered with : " + userRegisterRequest.getPhoneNumber()
            );
        }

        return new JWTokenResponse(OK.value(), OK.name(), tokens[0], tokens[1]);
    }

    public JWTokenResponse loginUser(UserLoginRequest userLoginRequest) {
        UserEntity user = repository.findByPhoneNumber(userLoginRequest.getPhoneNumber())
                .orElseThrow(
                        () -> new UserNotFoundException(
                                "User not found with : " + userLoginRequest.getPhoneNumber()
                        )
                );

        if (passwordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())) {

            String[] tokens = jwTokenProvider.generateJwtTokens(user);

            return new JWTokenResponse(OK.value(), OK.name(), tokens[0], tokens[1]);

        } else {

            throw new UserInvalidPasswordException("Wrong password");

        }


    }

    public String[] validateRefreshToken(String jwtRefreshToken) throws RuntimeException {
        Jws<Claims> jws = jwTokenProvider.validateJwtRefreshToken(jwtRefreshToken);
        UserEntity user = (UserEntity) userDetailsService.loadUserByUsername(jws.getBody().getSubject());
        return new String[]{jwTokenProvider.generateAccessToken(user), jwtRefreshToken};
    }
}
