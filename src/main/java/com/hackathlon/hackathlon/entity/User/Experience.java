package com.hackathlon.hackathlon.entity.User;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name="experience")
public class Experience {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="experience_sequence")
    @SequenceGenerator(name="experience_sequence", allocationSize=10)
    @Setter(AccessLevel.PRIVATE)
    private Long ID;

    @Column(name="name")
    private String name;

    @Column(name="years")
    private Integer years;

    @ManyToOne
    @JoinColumn(name="userID")
    private User user;
}
