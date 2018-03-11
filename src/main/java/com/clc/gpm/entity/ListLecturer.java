package com.clc.gpm.entity;

import com.clc.gpm.dao.common.annotation.Column;
import com.clc.gpm.dao.common.annotation.Table;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Table(name = "LIST_LECTURER")
@Alias("ListLecturer")
public class ListLecturer {

    @Column(name = "EXAMINATION_COUNCIL_ID")
    private int examinationCouncilId;

    @Column(name = "LECTURER_ID")
    private int lecturerId;
}
