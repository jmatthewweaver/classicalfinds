package com.iw.cf.core.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PerformanceVideo
implements Serializable {
    private Long id;
    private Long performanceId;
    private Integer sequence;
    private String videoId;
}
