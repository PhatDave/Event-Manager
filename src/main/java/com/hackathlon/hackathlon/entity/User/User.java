package com.hackathlon.hackathlon.entity.User;

import com.hackathlon.hackathlon.entity.*;
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
    @Setter(AccessLevel.PRIVATE)
    private Long ID;

    @Embedded
    BasicInfo basicInfo;

    @Embedded
    Fluff fluff;

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    private List<Education> educations;

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    private List<Experience> experiences;

    @JoinColumn(name="teamID")
    @ManyToOne
    private Team team;

    @JoinColumn(name="registration")
    @OneToOne
    private Registration registration;

    @Column(name="weeks")
    @ManyToMany(mappedBy="user")
    private List<Week> weeks;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Team user;

}