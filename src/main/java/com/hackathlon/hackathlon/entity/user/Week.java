package com.hackathlon.hackathlon.entity.user;

import com.hackathlon.hackathlon.entity.enums.*;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name="week")
public class Week {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="week_sequence")
    @SequenceGenerator(name="week_sequence", allocationSize=10)
    @Setter(AccessLevel.PRIVATE)
    private Long ID;

    @Column(name="status")
    private WeekStatus status;

    @Column(name="comment")
    private String comment;

    @Column(name="weekNumber")
    private Integer weekNumber;

    @JoinColumn(name="users")
    @ManyToOne
    private User user;
}
