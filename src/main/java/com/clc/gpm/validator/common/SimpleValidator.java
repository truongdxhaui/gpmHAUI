package com.clc.gpm.validator.common;

import com.clc.gpm.form.AppForm;
import org.springframework.stereotype.Service;

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
     * @param clazz AppForm
     */
    public void validateField(AppForm clazz, String field, String fieldValue,
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
