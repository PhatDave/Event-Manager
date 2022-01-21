package com.hackathlon.hackathlon.entity;

import com.hackathlon.hackathlon.entity.User.User;
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
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="user")
    @OneToMany(mappedBy="team")
    private List<User> users;

    @Column(name="mentor")
    @OneToMany(mappedBy="team")
    private List<Mentor> mentors;

    @ManyToOne
    @JoinColumn(name="eventID")
    private Event event;
}
