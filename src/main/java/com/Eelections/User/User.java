package com.Eelections.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //core user identity
    @Column(nullable = false,unique = false)
    private String name;

    @Column(nullable = false,unique = false)
    private String idNo; //id will be used as a username

    @Column(nullable = true)
    private String nationality;

    @Column(nullable = false)
    private String address;

    //communication details
    @Column
    private String cellNumber;

    @Column
    private String email;

    //security
    @Column
    private String password;

    @Column
    private String Role;

    //Todo: add the photo of the user

    //voting details
    @Column(nullable = false)
    private boolean Voted;

}
