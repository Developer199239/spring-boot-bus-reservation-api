package com.example.busreservation.models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestModel {
    private String userName;
    private String password;
    private String role;
    private String customerName;
    private String email;
    private String mobile;
}