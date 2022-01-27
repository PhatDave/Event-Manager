package com.hackathlon.hackathlon.entity.User;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BasicInfo {
    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="phone")
    private String phone;
}
