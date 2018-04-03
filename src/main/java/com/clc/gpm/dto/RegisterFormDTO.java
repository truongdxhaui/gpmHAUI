package com.clc.gpm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterFormDTO extends BaseDTO{

    private String studentId;

    private String firstName;

    private String lastName;

    private int projectId;

    private String projectName;

    private String groupName;

    private String content;

    private String level;

    private String requirement;

    private String reason;

    private String description;

    private String firstnameLecturer;

    private String lastnameLecturer;

    private String specialize;

    private String lecturerId;

    private String userId;

    private String type;

    private String teamId;

    public void setLecturerInfo(UserDTO userDTO){
        this.firstnameLecturer = userDTO.getFirstnameLecturer();
        this.lastnameLecturer = userDTO.getLastnameLecturer();
        this.specialize = userDTO.getSpecialize();
        this.lecturerId = userDTO.getLecturerId();
    }

}
