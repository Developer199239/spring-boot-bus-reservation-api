package com.example.busreservation.services;
import com.example.busreservation.entities.Customer;
import com.example.busreservation.models.SignUpRequestModel;

public interface AppUserService {
    void signupUser(SignUpRequestModel signUpRequestModel);
    Customer getCustomerInfo(String userName);
}
