package com.gdsc.backend.service;

import com.gdsc.backend.domain.AcquiredCertification;
import com.gdsc.backend.domain.Certification;
import com.gdsc.backend.domain.CertificationReview;
import com.gdsc.backend.domain.SiteUser;
import com.gdsc.backend.dto.*;
import com.gdsc.backend.repository.AcquiredCertificationRepository;
import com.gdsc.backend.repository.CertificationRepository;
import com.gdsc.backend.repository.CertificationReviewRepository;
import com.gdsc.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor //final or @NotNull이 붙은 필드의 생성자 추가
@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final AcquiredCertificationRepository acquiredCertificationRepository;
    @Autowired
    CertificationRepository CertificationRepository;
    @Autowired
    private CertificationRepository certificationRepository;
    @Autowired
    private CertificationReviewRepository certificationReviewRepository;


    public SiteUser save(AddUserRequest request){
        return userRepository.save(request.toEntity());
    }

    @Transactional
    public ModifyUserResponse modifyUser(Long userId, ModifyUserInfoRequest request) {
        Optional<SiteUser> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            SiteUser user = optionalUser.get();
            user.setEmail(request.getEmail());
            user.setName(request.getName());
            user.setNickname(request.getNickname());
            user.setBirthDate(request.getBirthDate());
            user.setProfileImage(request.getProfileImage());
            userRepository.save(user);
            return new ModifyUserResponse(true, "User information updated successfully.");
        } else {
            return new ModifyUserResponse(false, "User not found.");
        }
    }

    @Transactional
    public AddUserCertificationResponse addCertification(AddUserCertificationRequest requestDTO) {
        AcquiredCertification userCertification = new AcquiredCertification();
        Certification certification = certificationRepository.getById(requestDTO.getCertificationId());

        userCertification.setSiteUser(requestDTO.getSiteUser());
        userCertification.setAcquiredCertificationID(requestDTO.getCertificationId());
        userCertification.setExamDate(requestDTO.getExamDate());
        userCertification.setExpireDate(requestDTO.getExpireDate());

        userCertification = acquiredCertificationRepository.save(userCertification);

        return new AddUserCertificationResponse(true, userCertification.getCertification().toString(), "Certification added successfully.");
    }

    @Transactional
    public CertificationReview addCertificationReview(AddCertificationReivewRequest request) {
        CertificationReview review = new CertificationReview();
        review.setContent(request.getContent());
        review.setRating(request.getRating());
        review.setCreatedAt(request.getCreatedAt());
        review.setSiteUser(request.getSiteUser());
        review.setCertification(request.getCertification());

        return certificationReviewRepository.save(review);
    }



    @Transactional
    public boolean deleteCertification(Long certificationId) {
        if (certificationRepository.existsById(certificationId)) {
            certificationRepository.deleteById(certificationId);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public boolean modifyUserCertification(Long userId, Long acquiredCertificationId, ModifyUserCertificationRequest request) {
        Optional<AcquiredCertification> optionalUserCertification = acquiredCertificationRepository.findById(acquiredCertificationId);

        if (optionalUserCertification.isPresent()) {
            AcquiredCertification userCertification = optionalUserCertification.get();

            // Assuming we have a method to check if the certification belongs to the user
            if (!userCertification.getSiteUser().getUserID().equals(userId)) {
                return false;
            }

            userCertification.setExamDate(request.getExamDate());
            userCertification.setExpireDate(request.getExpireDate());
            userCertification.setStudyPeriod(request.getStudyPeriod());

            acquiredCertificationRepository.save(userCertification);
            return true;
        } else {
            return false;
        }
    }
}