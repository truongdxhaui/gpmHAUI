package com.clc.gpm.validator.common;

import com.clc.gpm.exception.BaseException;
import com.clc.gpm.form.AppForm;
import com.clc.gpm.utils.CheckUtil;
import com.clc.gpm.utils.StringUtil;
import com.clc.gpm.validator.annotation.Ignore;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public abstract class AppValidator {

    private static final String HAS_ERROR = "] has error: ";

    private static final String FIELD_STRING_END = "] is ignore validate.";

    private static final String FIELD_STRING_BEGIN = "Field [";

    protected Log logger;

    @Autowired
    protected StringUtil strUtil;

    @Autowired
    protected CheckUtil checkUtil;

    @Autowired
    protected CommonValidator commonValidator;

    private List<String> errorItemNameList;

    @PostConstruct
    public void init() {

        // initial logger
        this.logger = LogFactory.getLog(this.getClass());
    }


    public abstract void validateField(String fieldName, String fieldValue,
            BindingResult errors);


    public abstract void validateLogic(AppForm clazz, BindingResult errors);

    public void validate(Object targetObj, BindingResult errors,
            String... items) {

        String property = "";
        String fieldVal = "";
        Class<? extends Object> clazz = targetObj.getClass();

        Field[] fields = clazz.getDeclaredFields();
        AppForm appForm = AppForm.class.cast(targetObj);
        errorItemNameList = new ArrayList<String>();

        for (Field field : fields) {

            property = field.getName();

            if (field.isAnnotationPresent(Ignore.class)) {
                logger.debug(FIELD_STRING_BEGIN + property + FIELD_STRING_END);
                continue;
            }

            // Check property List type
            if ("java.util.List".equals(field.getType().getName())) {

                List<?> objVal = new ArrayList<Object>();
                try {
                    // set value of property List
                    Method method = targetObj.getClass()
                            .getMethod("get"
                                    + property.substring(0, 1).toUpperCase()
                                    + property.substring(1), new Class[] {});
                    objVal = (List<?>) method.invoke(targetObj,
                            new Object[] {});
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.warn(FIELD_STRING_BEGIN + property + "] no getter.");
                }

                if (objVal != null) {
                    for (Object obj : objVal) {
                        if (!(obj == null || obj instanceof String
                                || obj instanceof Number)) {
                            validate(obj, errors, property);
                        } else {
                            validateItem(field, (String) obj, property, errors);
                        }
                    }
                }
            } else {

                try {
                    field.setAccessible(true);
                    fieldVal = strUtil.convertNull(field.get(targetObj));
                } catch (Exception e) {
                    logger.debug("Can not to get data for Field [" + property
                            + "].");
                    e.printStackTrace();
                }

                validateItem(field, fieldVal, property, errors);
            }

        }

        // Validate logic
        if (!errors.hasErrors()) {
            validateLogic(appForm, errors);
        }
    }

    private void validateItem(Field field, String fieldVal, String property,
            BindingResult errors) {

        try {

            // Validate wit annotation
            // TODO : validate annotation by order ?
            commonValidator.validateWithAnnotation(field, fieldVal,
                    errorItemNameList);

            if (!strUtil.isNull(fieldVal)) {
                validateField(property, fieldVal, errors);
            }
        } catch (Throwable ex) {
            if (ex instanceof BaseException) {
                logger.debug(FIELD_STRING_BEGIN + property + HAS_ERROR
                        + ((BaseException) ex).getMessageId());
            } else {
                logger.debug(FIELD_STRING_BEGIN + property + HAS_ERROR
                        + ex.getMessage());
            }
            errors.addError(ex);
        }
    }


    public List<String> getErrorItemNameList() {
        return errorItemNameList;
    }


    public void setErrorItemNameList(List<String> errorItemNameList) {
        this.errorItemNameList = errorItemNameList;
    }

}
