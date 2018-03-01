package com.clc.gpm.entity;

import com.clc.gpm.dao.common.annotation.Column;
import com.clc.gpm.dao.common.annotation.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Task.
 *
 * @author truon
 * @version 1.0
 * @created 05 -Nov-2017 4:08:31 PM
 */
@Table(name = "TASK")
@Getter
@Setter
public class Task extends BaseEntity {

	@Column(name = "ID")
    private int id;

	@Column(name = "COMMENT")
	private String comment;

	@Column(name = "DURATION")
	private int duration;

	@Column(name = "GRADUATION_THESIS_ID")
	private int graduationThesisId;

	@Column(name = "LINK_TASK")
	private String linkTask;

	@Column(name = "NAME")
	private String name;

	@Column(name = "PERCENT_COMPLETION")
	private int percentCompletion = 0;

	@Column(name = "PROPOSER_ID")
	private int proposerId;

	@Column(name = "REQUIREMENT")
	private String requirement;

	@Column(name = "RESUILT")
	private String resuilt;

	@Column(name = "START_DATE")
	private String startDate;

	@Column(name = "STATUS")
	private int status;

	@Column(name = "TYPE_TASK")
	private int typeTask;
}