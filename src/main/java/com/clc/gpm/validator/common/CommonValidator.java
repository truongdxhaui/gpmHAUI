package com.clc.gpm.validator.common;

import com.clc.gpm.common.Constants;
import com.clc.gpm.exception.InputCheckException;
import com.clc.gpm.utils.CheckUtil;
import com.clc.gpm.utils.StringUtil;
import com.clc.gpm.validator.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;

@Component("commonValidator")
public class CommonValidator {

    @Autowired
    protected StringUtil strUtil;

    /**
     * checkUtil
     */
    @Autowired
    protected CheckUtil checkUtil;


    public void validateWithAnnotation(Field field, String fieldVal,
            List<String> errorItemNameList) throws Exception {
        String val = fieldVal == null ? fieldVal : fieldVal.trim();

        // Check require
        if (field.isAnnotationPresent(Required.class)
                && strUtil.isNull(fieldVal)) {
            InputCheckException ex = new InputCheckException(
                    Constants.MSG_RIQUIRED_INPUT_ERR,
                    new String[] { field.getAnnotation(Required.class)
                            .displayFieldName() });
            ex.setFieldName(field.getName());
            errorItemNameList.add(field.getName());
            throw ex;
        }

        // Check length
        if (field.isAnnotationPresent(MaxLength.class)) {
            MaxLength anno = field.getAnnotation(MaxLength.class);
            if (!strUtil.isNull(val) && val.length() > anno.maxlength()) {
                InputCheckException ex = new InputCheckException(
                        Constants.MSG_LENGTH_INPUT_ERR,
                        new String[] { anno.displayFieldName() });
                ex.setFieldName(field.getName());
                errorItemNameList.add(field.getName());
                throw ex;
            }
        }

        // Check number halfsize : 0-9
        if (field.isAnnotationPresent(HaftsizeNumber.class)) {
            HaftsizeNumber anno = field.getAnnotation(HaftsizeNumber.class);
            if (!checkUtil.isHaftsizeNumeric(fieldVal)) {
                InputCheckException ex = new InputCheckException(
                        Constants.MSG_HALFSIZE_NUMBER_INPUT_ERR,
                        new String[] { anno.displayFieldName() });
                ex.setFieldName(field.getName());
                errorItemNameList.add(field.getName());
                throw ex;
            }
        }

        // Check alphabet haftsize : a-z, A-Z
        if (field.isAnnotationPresent(HalftsizeAlphabet.class)) {
            HalftsizeAlphabet anno = field
                    .getAnnotation(HalftsizeAlphabet.class);
            if (!checkUtil.isHaftSizeAlphabet(fieldVal)) {
                InputCheckException ex = new InputCheckException(
                        Constants.MSG_HALFSIZE_ALPHABET_INPUT_ERR,
                        new String[] { anno.displayFieldName() });
                ex.setFieldName(field.getName());
                errorItemNameList.add(field.getName());
                throw ex;
            }
        }

        // Check alphabet, number haftsize : a-z, A-Z
        if (field.isAnnotationPresent(HalftsizeAlphabetNumber.class)) {
            HalftsizeAlphabetNumber anno = field
                    .getAnnotation(HalftsizeAlphabetNumber.class);
            if (!checkUtil.isHalfSizeAlphabetAndNumber(fieldVal)) {
                InputCheckException ex = new InputCheckException(
                        Constants.MSG_HALFSIZE_ALPHABET_NUMBER_INPUT_ERR,
                        new String[] { anno.displayFieldName() });
                ex.setFieldName(field.getName());
                errorItemNameList.add(field.getName());
                throw ex;
            }
        }

        // Check alphabet, number, symbol haftsize : a-z, A-Z,...
        if (field.isAnnotationPresent(HalftsizeAlphabetNumberSymbol.class)) {
            HalftsizeAlphabetNumberSymbol anno = field
                    .getAnnotation(HalftsizeAlphabetNumberSymbol.class);
            if (!checkUtil.isHalfAlphabetNumberSymbol(fieldVal)) {
                InputCheckException ex = new InputCheckException(
                        Constants.MSG_HALFSIZE_ALPHABET_NUMBER_SYMBOL_INPUT_ERR,
                        new String[] { anno.displayFieldName() });
                ex.setFieldName(field.getName());
                errorItemNameList.add(field.getName());
                throw ex;
            }
        }

        // Check Fullsize : a-z, A-Z
        if (field.isAnnotationPresent(Fullsize.class)) {
            Fullsize anno = field.getAnnotation(Fullsize.class);
            if (!checkUtil.isFullSize(fieldVal)) {
                InputCheckException ex = new InputCheckException(
                        Constants.MSG_FULLSIZE_INPUT_ERR,
                        new String[] { anno.displayFieldName() });
                ex.setFieldName(field.getName());
                errorItemNameList.add(field.getName());
                throw ex;
            }
        }

        // Check date format
        // Format validate
        if (field.isAnnotationPresent(Format.class)) {
            Format anno = field.getAnnotation(Format.class);

            // Check date format
            if (anno.type().equals(Format.FormatType.DATE)) {
                boolean isValidDate = checkUtil.isDateFormat(fieldVal,
                        anno.pattern());
                if (!isValidDate) {
                    InputCheckException ex = new InputCheckException(
                            Constants.MSG_DATE_FORMAT_INPUT_ERR, new String[] {
                                    anno.displayFieldName(), anno.pattern() });
                    ex.setFieldName(field.getName());
                    errorItemNameList.add(field.getName());
                    throw ex;
                }
            }
        }
    }

}
