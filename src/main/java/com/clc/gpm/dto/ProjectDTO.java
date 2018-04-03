package com.clc.gpm.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Project dto.
 */
@Getter
@Setter
public class ProjectDTO extends BaseDTO {

    private int id;

    private String name;

    private String description;

    private String lecturerId;

    private String levelName;

    private int status;
}
