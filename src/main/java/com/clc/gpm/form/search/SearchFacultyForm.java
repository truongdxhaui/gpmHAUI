package com.clc.gpm.form.search;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchFacultyForm {
    // current page
    private Integer page = 0;
    //sort index
    private int sortIndex;
    // sort type
    private String sortType;

}
