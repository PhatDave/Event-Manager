package com.hackathlon.hackathlon.entity;


import com.hackathlon.hackathlon.entity.user.*;
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

    @Column(name = "score")
    private Integer score;

    @Column(name = "UUID")
    private String UUID;

    @Column(name = "participation")
    private Boolean participation;

    @Column(name = "kickoff")
    private Boolean kickoff;

    @OneToMany(mappedBy = "registration", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @JoinColumn(name = "users")
    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @JoinColumn(name = "eventID")
    @ManyToOne(cascade = CascadeType.ALL)
    private Event event;
}
