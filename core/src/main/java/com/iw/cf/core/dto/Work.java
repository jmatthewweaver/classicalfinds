package com.iw.cf.core.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Work
implements Serializable {
    private Long id;
    private String title;
    private String subtitle;
    private Boolean popular;
    private Boolean recommended;
    private Long genreId;
}
