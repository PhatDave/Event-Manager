package com.hackathlon.hackathlon.entity;

import com.hackathlon.hackathlon.enums.EventStatusEnum;
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
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="event_sequence")
    @SequenceGenerator(name="event_sequence", allocationSize=10)
    @Setter(AccessLevel.PRIVATE)
    private Long ID;

    @Column(name="name", unique=true)
    private String name;

    @Column(name="registrationsNotBefore")
    private Date registrationsNotBefore;

    @Column(name="registrationsNotAfter")
    private Date registrationsNotAfter;

    @Column(name="confirmationNotAfter")
    private Date confirmationNotAfter;

    @Column(name="startDate")
    private Date startDate;

    @Column(name="maxParticipants")
    private Integer maxParticipants;

    @Column(name="weeks")
    private Integer weeks;

    @Column(name="status")
    private EventStatusEnum status;

    @OneToMany(mappedBy="event", cascade= {CascadeType.ALL})
    private List<Team> teams;

    @OneToMany(mappedBy="event", cascade= {CascadeType.ALL})
    private List<Registration> registrations;
}
