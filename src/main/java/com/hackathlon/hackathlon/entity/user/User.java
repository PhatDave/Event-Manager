package com.hackathlon.hackathlon.entity.user;

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

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Education education;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Experience experience;

    @JoinColumn(name="teamID")
    @ManyToOne(cascade = CascadeType.ALL)
    private Team team;

    @JoinColumn(name="registration")
    @OneToOne(cascade = CascadeType.ALL)
    private Registration registration;

    @Column(name="weeks")
    @ManyToMany(mappedBy="user", cascade=CascadeType.ALL)
    private List<Week> weeks;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Team user;

}