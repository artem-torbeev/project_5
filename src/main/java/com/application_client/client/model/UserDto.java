package com.application_client.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {

    private long id;
    private String username;
    private String email;
    private String password;
    private List<String> role;
}

