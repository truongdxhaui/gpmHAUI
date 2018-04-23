package com.clc.gpm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDTO extends BaseDTO
{
    private String taskName;

    private String perCompletion;

    private Integer taskStatus;

    private String taskUpdateTime;
}
