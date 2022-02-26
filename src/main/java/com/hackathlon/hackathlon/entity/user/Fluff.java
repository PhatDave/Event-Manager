package com.hackathlon.hackathlon.entity.user;

import lombok.*;

import javax.persistence.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fluff {
    @Column(name="summary")
    private String summary;

    @Column(name="motivation")
    private String motivation;

    @Column(name="preferredOS")
    private String preferredOS;

    @Column(name="tShirt")
    private String tShirt;
}
