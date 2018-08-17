package com.alen.user.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;

    private String username;

    private String password;

    private String name;

    private String phone;

    private Integer createBy;

    private Date createDate;

    private Integer updateBy;

    private Date updateDate;

    private Integer delFlag;

    private Integer status;
}
