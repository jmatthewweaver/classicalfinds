package com.iw.cf.api.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Performer
implements Serializable {
    private Long id;
    private String name;
    private Long imageId;
}
