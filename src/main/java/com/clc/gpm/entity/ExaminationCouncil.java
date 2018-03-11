package com.clc.gpm.entity;

import com.clc.gpm.dao.common.annotation.Column;
import com.clc.gpm.dao.common.annotation.Table;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * The type Examination council.
 *
 * @author truon
 * @version 1.0
 * @created 05 -Nov-2017 4:08:31 PM
 */

@Getter
@Setter
@Table(name = "EXAMINATION_COUNCIL")
@Alias("ExaminationCouncil")
public class ExaminationCouncil extends BaseEntity{

    @Column(name = "ID")
    private int id;

    @Column(name = "LIST_LECTURER_ID")
    private int listLecturerId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;
}