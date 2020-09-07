package com.iw.cf.api.dto;

import com.iw.cf.core.dto.Composer;
import com.iw.cf.core.dto.Work;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SearchResults
implements Serializable {

    private List<Composer> composers;

    private List<Work> works;

}
