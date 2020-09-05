package com.iw.cf.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Composer
implements Serializable {

    private Long id;
    private String name;
    private String completeName;
    private Long eraId;
    private Date birthDate;
    private Date deathDate;
    private Boolean popular;
    private Boolean recommended;
    private Long imageId;
}
