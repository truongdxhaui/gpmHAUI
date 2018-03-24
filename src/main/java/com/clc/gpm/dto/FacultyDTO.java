package com.clc.gpm.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
public class FacultyDTO implements Serializable {

    private static final long serialVersionUID = 1420635747715993129L;

    private int id;

    private String facultyId;

    private String name;

    private int leaderId;

    protected LocalDateTime updateTime;

    private String firstName;

    private String lastName;

    public String getLeaderName(){
        return firstName + " " + lastName;
    }
}
