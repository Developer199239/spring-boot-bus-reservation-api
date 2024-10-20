package com.example.busreservation.services.impl;

import com.example.busreservation.entities.AppUsers;
import com.example.busreservation.models.ReservationApiException;
import com.example.busreservation.models.SignUpRequestModel;
import com.example.busreservation.models.UserInfoModel;
import com.example.busreservation.repos.AppUserRepository;
import com.example.busreservation.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public void signupUser(SignUpRequestModel signUpRequestModel) {
        Optional<AppUsers> isExistingUser = appUserRepository.findByUserName(signUpRequestModel.getUserName());
        if (isExistingUser.isPresent()) {
            throw new ReservationApiException(HttpStatus.CONFLICT, "User already exist: " + signUpRequestModel.getUserName());
        }

        AppUsers appUsers = new AppUsers();
        appUsers.setUserName(signUpRequestModel.getUserName());
        appUsers.setPassword(signUpRequestModel.getPassword());
        appUsers.setRole(signUpRequestModel.getRole());
        appUsers.setCustomerName(signUpRequestModel.getCustomerName());
        appUsers.setEmail(signUpRequestModel.getEmail());
        appUsers.setMobile(signUpRequestModel.getMobile());
        appUserRepository.save(appUsers);
    }

    @Override
    public UserInfoModel getCustomerInfo(String userName) {
        AppUsers appUsers = appUserRepository.findByUserName(userName).orElseThrow(() -> new ReservationApiException(HttpStatus.NOT_FOUND, "User not found"));

        UserInfoModel infoModel = new UserInfoModel();
        infoModel.setUserName(appUsers.getUserName());
        infoModel.setPassword(appUsers.getPassword());
        infoModel.setRole(appUsers.getRole());
        infoModel.setCustomerName(appUsers.getCustomerName());
        infoModel.setEmail(appUsers.getEmail());
        infoModel.setMobile(appUsers.getMobile());
        infoModel.setUserId(String.valueOf(appUsers.getId()));
        return  infoModel;
    }
}
