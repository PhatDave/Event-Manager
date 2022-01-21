package com.hackathlon.hackathlon.entity;


import com.hackathlon.hackathlon.entity.User.*;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name="registration")
public class Registration {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="registration_sequence")
    @SequenceGenerator(name="registration_sequence", allocationSize=10)
    @Setter(AccessLevel.PRIVATE)
    private Long ID;

    @Column(name="score")
    private Integer score;

    @Column(name="UUID")
    private String UUID;

    @Column(name="participation")
    private Boolean participation;

    @Column(name="kickoff")
    private Boolean kickoff;

    @Column(name="comments")
    @OneToMany(mappedBy="registration")
    private List<Comment> comments;

    @JoinColumn(name="user")
    @OneToOne
    private User user;

    @JoinColumn(name="eventID")
    @ManyToOne
    private Event event;
}
