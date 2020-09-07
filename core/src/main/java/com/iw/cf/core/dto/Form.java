package com.iw.cf.core.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Form
implements Serializable {

    private Long id;
    private String name;
}
