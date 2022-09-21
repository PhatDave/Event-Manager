package com.hackathlon.entity.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name="experience")
public class Experience {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="experience_sequence")
    @SequenceGenerator(name="experience_sequence", allocationSize=10)
    private Long ID;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="userID")
    private User user;

    @Column(name="years")
    private Integer years;

    @Column(name="repositoryUrl")
    private String repositoryUrl;

    @Column(name="summary")
    private String summary;

    @OneToMany(mappedBy="experience", cascade=CascadeType.ALL)
    private List<Skill> skills;
}
