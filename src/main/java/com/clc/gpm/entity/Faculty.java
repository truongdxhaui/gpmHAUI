package com.clc.gpm.entity;

import com.clc.gpm.dao.common.annotation.Column;
import com.clc.gpm.dao.common.annotation.Table;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

/**
 * The type Faculty.
 *
 * @author truon
 * @version 1.0
 * @created 05 -Nov-2017 4:08:31 PM
 */

@Setter
@Getter
@Table(name = "FACULTY")
@Alias("Faculty")
public class Faculty extends BaseEntity{

	@Column(name = "ID")
	private int id;

	@Column(name = "FACULTY_ID")
	private String facultyId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "LEADER_ID")
    private int leaderId;

    /**
     * Instantiates a new Faculty.
     *
     * @param facultyId the faculty id
     * @param name      the name
     * @param leaderId  the leader id
     */
    public Faculty(String facultyId, String name, int leaderId) {
		this.facultyId = facultyId;
		this.name = name;
		this.leaderId = leaderId;
	}
}