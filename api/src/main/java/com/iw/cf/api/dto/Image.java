package com.iw.cf.api.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Image
implements Serializable {

    private Long id;
    private byte[] data;
}
