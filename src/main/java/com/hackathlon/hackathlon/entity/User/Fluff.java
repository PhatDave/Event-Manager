package com.hackathlon.hackathlon.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

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
