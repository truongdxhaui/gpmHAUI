package com.clc.gpm.validator.common;

import com.clc.gpm.common.MessageConstants;
import com.clc.gpm.exception.InputCheckException;
import com.clc.gpm.utils.CheckUtil;
import com.clc.gpm.utils.StringUtil;
import com.clc.gpm.validator.annotation.*;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;


/**
 * The type Common validator.
 */
@Component("commonValidator")
public class CommonValidator {

    /**
     * Validate with annotation.
     *
     * @param field             the field
     * @param fieldVal          the field val
     * @param errorItemNameList the error item name list
     * @throws InputCheckException the input check exception
     */
    public void validateWithAnnotation(Field field, String fieldVal, List<String> errorItemNameList) throws InputCheckException {
        // FIXME NO CHECK
        Ignore annotationIgnore = field.getDeclaredAnnotation(Ignore.class);
        Annotation[] annotations = field.getDeclaredAnnotations();
        if (annotationIgnore != null || ArrayUtils.isEmpty(annotations)) {
            return;
        }

        Required annotation = field.getDeclaredAnnotation(Required.class);
        if (annotation != null && StringUtil.isNull(fieldVal)) {
            InputCheckException ex = new InputCheckException(MessageConstants.MSG_SC101,
                    new String[] { annotation.displayFieldName() });
            ex.setFieldName(field.getName());
            errorItemNameList.add(field.getName());
            throw ex;
        }

        MaxLength annotationML = field.getDeclaredAnnotation(MaxLength.class);
        if (annotationML != null) {
            String val = fieldVal == null ? fieldVal : fieldVal.trim();
            if (!StringUtil.isNull(val) && val.length() > annotationML.maxlength()) {
                InputCheckException ex = new InputCheckException(MessageConstants.MSG_2, new String[] { annotationML.displayFieldName() });
                ex.setFieldName(field.getName());
                errorItemNameList.add(field.getName());
                throw ex;
            }
        }

        HaftsizeNumber annotationHN = field.getDeclaredAnnotation(HaftsizeNumber.class);
        if (annotationHN != null) {
            if (!CheckUtil.isHaftsizeNumeric(fieldVal)) {
                InputCheckException ex = new InputCheckException(MessageConstants.MSG_SC203, new String[] { annotationHN.displayFieldName() });
                ex.setFieldName(field.getName());
                errorItemNameList.add(field.getName());
                throw ex;
            }
        }

        HalftsizeAlphabet annotationHA = field.getDeclaredAnnotation(HalftsizeAlphabet.class);
        if (annotationHA != null) {
            if (!CheckUtil.isHaftSizeAlphabet(fieldVal)) {
                InputCheckException ex = new InputCheckException(MessageConstants.MSG_SC202, new String[] { annotationHA.displayFieldName() });
                ex.setFieldName(field.getName());
                errorItemNameList.add(field.getName());
                throw ex;
            }
        }

        HalftsizeAlphabetNumber annotationHAN = field.getDeclaredAnnotation(HalftsizeAlphabetNumber.class);
        if (annotationHAN != null) {
            if (!CheckUtil.isHalfSizeAlphabetAndNumber(fieldVal)) {
                InputCheckException ex = new InputCheckException(MessageConstants.MSG_SC204, new String[] { annotationHAN.displayFieldName() });
                ex.setFieldName(field.getName());
                errorItemNameList.add(field.getName());
                throw ex;
            }
        }

        HalftsizeAlphabetNumberSymbol annotationHANS = field.getDeclaredAnnotation(HalftsizeAlphabetNumberSymbol.class);
        if (annotationHANS != null) {
            if (!CheckUtil.isHalfAlphabetNumberSymbol(fieldVal)) {
                InputCheckException ex = new InputCheckException(MessageConstants.MSG_SC205, new String[] { annotationHANS.displayFieldName() });
                ex.setFieldName(field.getName());
                errorItemNameList.add(field.getName());
                throw ex;
            }
        }

        Fullsize annotationF = field.getDeclaredAnnotation(Fullsize.class);
        if (annotationF != null) {
            if (!CheckUtil.isFullSize(fieldVal)) {
                InputCheckException ex = new InputCheckException(MessageConstants.MSG_SC201, new String[] { annotationF.displayFieldName() });
                ex.setFieldName(field.getName());
                errorItemNameList.add(field.getName());
                throw ex;
            }
        }

        Format annotationFormat = field.getDeclaredAnnotation(Format.class);
        if (annotationFormat != null) {

            if (annotationFormat.type().equals(Format.FormatType.DATE)) {
                boolean isValidDate = CheckUtil.isDateFormat(fieldVal, annotationFormat.pattern());
                if (!isValidDate) {
                    InputCheckException ex = new InputCheckException(MessageConstants.MSG_SC206,
                            new String[] { annotationFormat.displayFieldName(), annotationFormat.pattern() });
                    ex.setFieldName(field.getName());
                    errorItemNameList.add(field.getName());
                    throw ex;
                }
            }
        }

        Size annotationSize = field.getDeclaredAnnotation(Size.class);
        if (annotationSize != null) {
            if (CheckUtil.isHaftsizeNumeric(fieldVal)) {
                int val = fieldVal == null ? 0 : Integer.parseInt(fieldVal);
                if (val < annotationSize.min() || val > annotationSize.max()) {
                    InputCheckException ex = new InputCheckException(MessageConstants.MSG_9, new String[] { annotationSize.displayFieldName() });
                    ex.setFieldName(field.getName());
                    errorItemNameList.add(field.getName());
                    throw ex;
                }
            } else {
                InputCheckException ex = new InputCheckException(MessageConstants.MSG_SC203, new String[] { annotationSize.displayFieldName() });
                ex.setFieldName(field.getName());
                errorItemNameList.add(field.getName());
                throw ex;
            }
        }
    }
}