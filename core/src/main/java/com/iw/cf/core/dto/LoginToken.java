package com.iw.cf.core.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class LoginToken
implements Serializable {

    private Long id;
    private Long userId;
    private String token;
    private Date expiration;
}
