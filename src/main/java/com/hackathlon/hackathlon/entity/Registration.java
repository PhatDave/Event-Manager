package com.hackathlon.hackathlon.entity;


import com.hackathlon.hackathlon.entity.user.User;
import com.hackathlon.hackathlon.enums.RegistrationStatusEnum;
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
@Table(name="registration")
public class Registration {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="registration_sequence")
    @SequenceGenerator(name="registration_sequence", allocationSize=10)
    private Long ID;

    @Column(name="score")
    private Integer score;

    @Column(name="UUID")
    private String UUID;

    @Column(name="participation")
    private Boolean participation;

    @Column(name="kickoff")
    private Boolean kickoff;

    @OneToMany(mappedBy="registration", cascade=CascadeType.ALL)
    private List<Comment> comments;

    @Column(name="status")
    private RegistrationStatusEnum status;

    @JoinColumn(name="userId")
    @OneToOne(cascade=CascadeType.ALL)
    private User user;

    @JoinColumn(name="eventId")
    @ManyToOne(cascade=CascadeType.PERSIST)
    private Event event;
}
