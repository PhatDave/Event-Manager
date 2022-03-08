package com.hackathlon.hackathlon.entity;

import com.hackathlon.hackathlon.entity.user.*;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name="team")
public class Team {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="team_sequence")
    @SequenceGenerator(name="team_sequence", allocationSize=10)
    private Long ID;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy="team", cascade=CascadeType.PERSIST)
    private List<User> users;

    @OneToMany(mappedBy="team", cascade=CascadeType.ALL)
    private List<Mentor> mentors;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="eventID")
    private Event event;
}
