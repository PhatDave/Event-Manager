package com.hackathlon.hackathlon.entity.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name="skill")
public class Skill {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="skill_sequence")
    @SequenceGenerator(name="skill_sequence", allocationSize=10)
    @Setter(AccessLevel.PRIVATE)
    private Long ID;

    @Column(name="name")
    private String name;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="experienceID")
    private Experience experience;

    @Override
    public String toString() {
        return this.name;
    }
}
