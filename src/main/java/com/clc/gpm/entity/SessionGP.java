package com.clc.gpm.entity;

import com.clc.gpm.dao.common.annotation.Column;
import com.clc.gpm.dao.common.annotation.Table;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * The type Session gp.
 *
 * @author truon
 * @version 1.0
 * @created 05 -Nov-2017 4:08:32 PM
 */
@Getter
@Setter
@Table(name = "SESSION_GP")
@Alias("SessionGP")
public class SessionGP extends BaseEntity{

	@Column(name = "ID")
	private int id;

	@Column(name = "DURATION")
	private int duration;

	@Column(name = "LEADER_ID")
	private int leaderId;

	@Column(name = "START_DATE")
	private Date startDate;

	@Column(name = "YEAR")
	private int year;

}