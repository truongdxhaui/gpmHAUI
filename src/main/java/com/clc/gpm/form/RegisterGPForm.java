package com.clc.gpm.form;

import com.clc.gpm.validator.annotation.Required;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterGPForm extends AppForm {

    private String studentId;

    private String firstName;

    private String lastName;

    private int projectId;

    private String projectName;

    private String groupName;

    private String content;

    private String level;

    private String requirement;

    @Required(displayFieldName = "reason")
    private String reason;

    @Required(displayFieldName = "description")
    private String description;

    private String firstnameLecturer;

    private String lastnameLecturer;

    private String specialize;

    private String lecturerId;

    private String userId;

    private String type;

    private String teamId;

    private String refresh;
}
