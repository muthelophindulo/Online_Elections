package com.Eelections.Admin;

import com.Eelections.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //core user identity
    @Column(nullable = false,unique = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String adminNo; //used as username

    @Column(nullable = false,unique = false)
    private String idNo;

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

}
