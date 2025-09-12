package com.Payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    int id;
    String username;
    String firstName;
    String lastName;
    String email;
    String password;
    String phone;
    int userStatus=0;
}
