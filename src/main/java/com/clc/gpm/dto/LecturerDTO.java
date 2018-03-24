package com.clc.gpm.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LecturerDTO extends BaseDTO {

    private String lecturerId;

    private String firstName;

    private String lastName;

    private String facultyName;

    private String positionName;

    private String description;

    private int projectNum;

}
