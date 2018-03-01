package com.clc.gpm.validator.common;

import com.clc.gpm.form.AppForm;
import org.springframework.stereotype.Service;


/**
 * The type Simple validator.
 */
@Service
public class SimpleValidator extends AppValidator {

    /**
     * Validate field
     * 
     * @param field String 
     * @param fieldValue String
     * @param errors BindingResult
     */
    public void validateField(String field, String fieldValue,
           BindingResult errors) {
    }

    /** validateLogic
     *  @param clazz AppForm
     *  @param errors BindingResult
     */
    public void validateLogic(AppForm clazz, BindingResult errors) {
        System.out.println("validateLogic!!! ");
    }

}
