package com.iw.cf.core.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Performance
implements Serializable {
    private Long id;
    private Long workId;
    private Long performerId;
}
