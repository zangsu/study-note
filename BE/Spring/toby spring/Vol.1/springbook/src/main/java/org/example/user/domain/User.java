package org.example.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    String id;
    String name;
    String password;
    Level level;
    int login;
    int recommend;
}
