package com.clc.gpm.dto.search;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchProjectDTO extends BaseSearchDTO {

    private String facultyId;

    private String level;

    private String lecturerName;

    private String lecturerId;

}
