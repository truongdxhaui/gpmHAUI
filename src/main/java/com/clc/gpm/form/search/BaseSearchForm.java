package com.clc.gpm.form.search;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseSearchForm {

    // current page
    protected Integer page = 0;
    //sort index
    protected int sortIndex;
    // sort type
    protected String sortType;
}
