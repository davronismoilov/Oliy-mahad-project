package uz.oliymahad.user.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.oliymahad.user.repository.UserRepository;


@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String jwtSubj) throws UsernameNotFoundException {

    return jwtSubj.contains("@") ?
            userRepository.findByEmail(jwtSubj).orElseThrow(
                    () -> new UsernameNotFoundException("User not found with - " + jwtSubj)) :
            userRepository.findByPhoneNumber(jwtSubj).orElseThrow(
                    () -> new UsernameNotFoundException("User not found with - " + jwtSubj));
  }

}
