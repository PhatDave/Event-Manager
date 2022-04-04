package com.hackathlon.hackathlon.entity.user;

import com.hackathlon.hackathlon.entity.Registration;
import com.hackathlon.hackathlon.entity.Team;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_sequence")
    @SequenceGenerator(name="user_sequence", allocationSize=10)
    private Long ID;

    @Embedded
    BasicInfo basicInfo;

    @Embedded
    Fluff fluff;

    @OneToOne(mappedBy="user", cascade=CascadeType.ALL)
    private Education education;

    @OneToOne(mappedBy="user", cascade=CascadeType.ALL)
    private Experience experience;

    @JoinColumn(name="teamID")
    @ManyToOne(cascade=CascadeType.PERSIST)
    private Team team;

    @JoinColumn(name="registration")
    @OneToOne(cascade=CascadeType.ALL)
    private Registration registration;

    @Column(name="weeks")
    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    private List<Week> weeks;

}