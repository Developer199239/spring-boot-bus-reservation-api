package com.example.busreservation.services.impl;

import com.example.busreservation.entities.AppUsers;
import com.example.busreservation.entities.Customer;
import com.example.busreservation.models.ReservationApiException;
import com.example.busreservation.models.SignUpRequestModel;
import com.example.busreservation.models.UserInfoModel;
import com.example.busreservation.repos.AppUserRepository;
import com.example.busreservation.repos.CustomerRepository;
import com.example.busreservation.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void signupUser(SignUpRequestModel signUpRequestModel) {
        Optional<AppUsers> isExistingUser = appUserRepository.findByUserName(signUpRequestModel.getUserName());
        if (isExistingUser.isPresent()) {
            throw new ReservationApiException(HttpStatus.CONFLICT, "User already exist: " + signUpRequestModel.getUserName());
        }

        Optional<Customer> byMobileOrEmail = customerRepository
                .findByMobileOrEmail(signUpRequestModel.getMobile(), signUpRequestModel.getEmail());
        if (byMobileOrEmail.isPresent()) {
            throw new ReservationApiException(HttpStatus.CONFLICT, "Mobile or email already exist");
        }

        AppUsers appUsers = new AppUsers();
        appUsers.setUserName(signUpRequestModel.getUserName());
        appUsers.setPassword(signUpRequestModel.getPassword());
        appUsers.setRole(signUpRequestModel.getRole());

        appUserRepository.save(appUsers);

        Customer customer = new Customer();
        customer.setUserName(signUpRequestModel.getUserName());
        customer.setCustomerName(signUpRequestModel.getCustomerName());
        customer.setEmail(signUpRequestModel.getEmail());
        customer.setMobile(signUpRequestModel.getMobile());

        customerRepository.save(customer);
    }

    @Override
    public UserInfoModel getCustomerInfo(String userName) {
        AppUsers appUsers = appUserRepository.findByUserName(userName).orElseThrow(() -> new ReservationApiException(HttpStatus.NOT_FOUND, "User not found"));
        Customer customer =  customerRepository.findByUserName(userName)
                .orElseThrow(() -> new ReservationApiException(HttpStatus.NOT_FOUND, "Customer username not found"));

        UserInfoModel infoModel = new UserInfoModel();
        infoModel.setUserName(appUsers.getUserName());
        infoModel.setPassword(appUsers.getPassword());
        infoModel.setRole(appUsers.getRole());
        infoModel.setCustomerName(customer.getCustomerName());
        infoModel.setEmail(customer.getEmail());
        infoModel.setMobile(customer.getMobile());
        infoModel.setCustomerId(String.valueOf(customer.getCustomerId()));
        return  infoModel;
    }
}
