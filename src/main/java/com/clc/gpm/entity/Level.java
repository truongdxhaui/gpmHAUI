package com.clc.gpm.entity;

import com.clc.gpm.dao.common.annotation.Column;
import com.clc.gpm.dao.common.annotation.Table;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

/**
 * The type Level.
 */

@Getter
@Setter
@Table(name = "LEVEL")
@Alias("Level")
public class Level extends BaseEntity {

    @Column(name = "LEVEL_ID")
    private String id;

    @Column(name = "LEVEL_NAME")
    private String name;

    @Column(name = "STATUS")
    private String status;

}
