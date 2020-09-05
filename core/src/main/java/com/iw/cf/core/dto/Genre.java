package com.iw.cf.core.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Genre
implements Serializable {

    private Long id;
    private String name;
}
