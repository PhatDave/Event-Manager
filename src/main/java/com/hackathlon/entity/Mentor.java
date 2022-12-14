package com.hackathlon.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name="mentors")
public class Mentor {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="mentor_sequence")
    @SequenceGenerator(name="mentor_sequence", allocationSize=10)
    private Long ID;

    @Column(name="email")
    private String email;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="teamID")
    private Team team;

}
