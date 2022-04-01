package com.hackathlon.hackathlon.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name="comment")
public class Comment {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="comment_sequence")
    @SequenceGenerator(name="comment_sequence", allocationSize=10)
    @Setter(AccessLevel.PRIVATE)
    private Long ID;

    @Column(name="content")
    private String content;

    @Column(name="score")
    private Integer score;

    @OnDelete(action=OnDeleteAction.NO_ACTION)
    @JoinColumn(name="registrationID")
    @ManyToOne(cascade=CascadeType.PERSIST)
    private Registration registration;
}
