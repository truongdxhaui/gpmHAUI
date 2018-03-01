package com.clc.gpm.entity;

import com.clc.gpm.dao.common.annotation.Column;
import com.clc.gpm.dao.common.annotation.PrimaryKey;
import com.clc.gpm.dao.common.annotation.Table;

/**
 * The type Classes.
 */
@Table(name = "CLASS")
public class Classes extends BaseEntity{

	@PrimaryKey(name = "ID")
    private int id;

		@Column(name = "CLASS_ID")
	private String classId;

	@Column(name = "FACULTY_ID")
	private String facultyId;

	@Column(name = "FACULTY_NAME")
	private String facultyName;

	@Column(name = "NAME")
	private String name;

	@Column(name = "INTAKE")
	private int inTake;
}