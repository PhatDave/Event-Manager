package com.hackathlon.hackathlon.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name="comment")
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="comment_sequence")
    @SequenceGenerator(name="comment_sequence", allocationSize=10)
    @Setter(AccessLevel.PRIVATE)
    private Long ID;

    @Column(name="content")
    private String content;

    @Column(name="score")
    private Integer score;
}
