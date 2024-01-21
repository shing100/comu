package com.shing100.community.module.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter @ToString
public class UserProfileDto {
    @NotNull
    @Email
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 8, max = 50)
    private String password;

    @NotNull
    @Size(min = 2, max = 50)
    private String username;

    private String firstName;
    private String lastName;

    private String bio;

    private String image;
}
