package com.hackathlon.hackathlon.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name="event")
public class Event {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="event_sequence")
    @SequenceGenerator(name="event_sequence", allocationSize=10)
    @Setter(AccessLevel.PRIVATE)
    private Long ID;

    @Column(name="name", unique=true)
    private String name;

    @Column(name="registerNotBefore")
    private Date registerNotBefore;

    @Column(name="registerNotAfter")
    private Date registerNotAfter;

    @Column(name="confirmNotAfter")
    private Date confirmNotAfter;

    @Column(name="startDate")
    private Date startDate;

    @Column(name="maxParticipants")
    private Integer maxParticipants;

    @Column(name="weeks")
    private Integer weeks;

    @Column(name="teams")
    @OneToMany(mappedBy="event")
    private List<Team> teams;

    @Column(name="registrations")
    @OneToMany(mappedBy="event")
    private List<Registration> registrations;
}
