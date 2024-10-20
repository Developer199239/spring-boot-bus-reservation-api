package com.example.busreservation.services;
import com.example.busreservation.models.SignUpRequestModel;
import com.example.busreservation.models.UserInfoModel;

public interface AppUserService {
    void signupUser(SignUpRequestModel signUpRequestModel);
    UserInfoModel getCustomerInfo(String userName);
}
