package com.clc.gpm.dto.search;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRegisterFormDTO extends BaseSearchDTO {

    private String teamId;

    private String projectName;

    private String createTime;

    private String lecturerId;


}
