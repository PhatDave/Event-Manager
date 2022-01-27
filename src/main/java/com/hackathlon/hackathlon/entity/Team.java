package com.hackathlon.hackathlon.entity;

import com.hackathlon.hackathlon.entity.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name="team")
public class Team {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="team_sequence")
    @SequenceGenerator(name="team_sequence", allocationSize=10)
    private Long ID;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy="team", cascade=CascadeType.ALL)
    private List<User> users;

    @OneToMany(mappedBy="team", cascade=CascadeType.ALL)
    private List<Mentor> mentors;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="eventID")
    private Event event;
}
