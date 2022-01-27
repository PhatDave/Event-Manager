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
@Table(name="education")
public class Education {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="education_sequence")
    @SequenceGenerator(name="education_sequence", allocationSize=10)
    @Setter(AccessLevel.PRIVATE)
    private Long ID;

    @Column(name="faculty")
    private String faculty;

    @Column(name="years")
    private Integer years;

    @ManyToOne
    @JoinColumn(name="userID")
    private User user;
}
