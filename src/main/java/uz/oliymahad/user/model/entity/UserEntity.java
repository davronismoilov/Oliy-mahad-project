package uz.oliymahad.user.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.oliymahad.user.audit.Auditable;
import uz.oliymahad.user.model.enums.EAuthProvider;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class UserEntity extends Auditable<String> implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;


    private String username;

    @Column(unique = true)
    private String phoneNumber;

//    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private EAuthProvider provider;

    private String providerId;

    @Column(unique = true)
    private String imageUrl;

//    @Column(nullable = false)
    private Boolean emailVerified = false;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<RoleEntity> roles;


    @OneToOne(fetch = FetchType.EAGER)
    private UserRegisterDetails userRegisterDetails;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getUsername() {
        return this.phoneNumber != null ? this.phoneNumber : this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserEntity(String password, String phoneNumber, Set<RoleEntity> roles) {
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
    }


}

