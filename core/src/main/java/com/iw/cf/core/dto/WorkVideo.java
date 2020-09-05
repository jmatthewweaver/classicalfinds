package com.iw.cf.core.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class WorkVideo
implements Serializable {

    private Long id;
    private Long workId;
    private String title;
    private String description;
    private String videoId;
    private Integer sortOrder;
}
