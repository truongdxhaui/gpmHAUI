package com.tau.validator.common;

import org.springframework.stereotype.Service;

import com.tau.forms.AppForm;

/**
 * <p>ファイル名 : SimpleValidator</p>
 * <p>説明 : User validate class</p>
 * @author bp.truong.pq
 * @since 2017/11/25
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
