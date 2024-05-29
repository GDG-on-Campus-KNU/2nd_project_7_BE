package com.gdsc.backend.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SiteUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 1씩 증가
    @Column(name = "UserID", updatable = false)
    private Long UserID;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @Column(name = "birthDate", nullable = false)
    private LocalDate birthDate;

    @Column(name = "profileImage")
    private String profileImage;

    @OneToMany(mappedBy = "siteUser")
    private List<CertificationReview> certificationReviews = new ArrayList<>();

    @OneToMany(mappedBy = "siteUser")
    private List<CertificationFavorite> certificationFavorites = new ArrayList<>();

    @OneToMany(mappedBy = "siteUser")
    private List<Studypost> studyposts = new ArrayList<>();

    @OneToMany(mappedBy = "siteUser")
    private List<StudypostComment> studypostcomments = new ArrayList<>();

    @OneToMany(mappedBy = "siteUser")
    private List<StudypostFavorite> studypostfavorites = new ArrayList<>();

    @Builder
    public SiteUser(String email, String password, String name, String nickname, LocalDate birthDate, String profileImage){
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.birthDate = birthDate;
        this.profileImage = profileImage;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("user"));
    }

    //사용자 ID 반환(고유값)
    @Override
    public String getUsername(){
        return email;
    }

    // 사용자 패스워드 반환
    @Override
    public String getPassword(){
        return password;
    }

    // 계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    // 계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    // 패스워드 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    //계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled(){
        return true;
    }


    // ===== 정보 수정 ===== //


}
