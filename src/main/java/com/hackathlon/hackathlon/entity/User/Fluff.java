package com.hackathlon.hackathlon.entity.User;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
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
