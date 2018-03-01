package com.clc.gpm.entity;

import com.clc.gpm.dao.common.annotation.Column;
import com.clc.gpm.dao.common.annotation.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "LIST_LECTURER")
public class ListLecturer {

    @Column(name = "EXAMINATION_COUNCIL_ID")
    private int examinationCouncilId;

    @Column(name = "LECTURER_ID")
    private int lecturerId;
}
