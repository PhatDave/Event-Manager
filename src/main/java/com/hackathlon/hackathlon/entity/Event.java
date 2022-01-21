package com.hackathlon.hackathlon.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name="event")
public class Event {
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
