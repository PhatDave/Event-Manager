package com.hackathlon.hackathlon.entity.user;

import com.hackathlon.hackathlon.entity.enums.WeekStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name="week")
public class Week {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="week_sequence")
    @SequenceGenerator(name="week_sequence", allocationSize=10)
    private Long ID;

    @Column(name="status")
    private WeekStatus status;

    @Column(name="comment")
    private String comment;

    @Column(name="weekNumber")
    private Integer weekNumber;

    @JoinColumn(name="userId")
    @ManyToOne(cascade=CascadeType.PERSIST)
    private User user;
}
