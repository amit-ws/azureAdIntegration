package com.whiteSwanSecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Data
public class UserDetails {
    private String id;
    private String displayName;
    private String givenName;
    private String surname;
    private String mail;
}
