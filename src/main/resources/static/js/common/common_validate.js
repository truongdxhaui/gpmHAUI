var errorObjectList = [];

function validateRequiredInput(errorObjectList) {

    $('.requiredInput').each(function() {
        validateForEachRequireInput($(this), errorObjectList);
    });

}

function validateForEachRequireInput(itemInput, errorObjectList) {
    var itemName = itemInput.attr("id");

    var itemValue;

    // radio gender
    if (itemName != null) {
        itemName = itemName.replace('Hidden', '');
    }

    // Check exist error item validate
    if (isExistErrorItem(itemName, errorObjectList)) {
        return;
    }

    if (itemInput.attr("type") == "checkbox") {
        itemValue = itemInput.is(':checked') + "";
    } else if (itemInput.attr("type") == "radio") {
        var radio = $('input[name=' + itemInput.attr("id") + ']:checked');
        itemValue = radio.val();
    } else {
        itemValue = itemInput.val();
    }
    var errorObj = checkRequiredInput(itemName, itemValue);

    if (errorObj != null) {
        errorObjectList.push(errorObj);
    }
}

function validateNumericInput(errorObjectList) {
    $('.numericInput').each(function() {
        validateNumericForEachInput($(this), errorObjectList);
    });
}

function validateNumericForEachInput(itemInput, errorObjectList) {
    var itemValue;
    var itemName = itemInput.attr("id");

    if (isExistErrorItem(itemName, errorObjectList)) {
        return;
    }

    itemValue = itemInput.val();

    var errorObj = checkHaftsizeNumeric(itemName, itemValue);

    if (errorObj != null) {
        errorObjectList.push(errorObj);
    }
}

function validateHalftsizeAlphabetInputUppercase(errorObjectList) {
	var test = $('.halftsizeAlphabetInputUppercase');
    $('.halftsizeAlphabetInputUppercase').each(function() {
    	validateHalftsizeAlphabetForEachInputUppercase($(this), errorObjectList);
    });
}

function validateHalftsizeAlphabetInput(errorObjectList) {
	var test = $('.halftsizeAlphabetInput');
    $('.halftsizeAlphabetInput').each(function() {
        validateHalftsizeAlphabetForEachInput($(this), errorObjectList);
    });
}

function validateHalftsizeAlphabetForEachInputUppercase(itemInput, errorObjectList) {

    var itemValue;
    var itemName = itemInput.attr("id");

    // Check exist error item validate
    if (isExistErrorItem(itemName, errorObjectList)) {
        return;
    }

    itemValue = itemInput.val();

    var errorObj = checkHaftsizeAlphabetUppercase(itemName, itemValue);

    if (errorObj != null) {
        errorObjectList.push(errorObj);
    }
}

function validateHalftsizeAlphabetForEachInput(itemInput, errorObjectList) {

    var itemValue;
    var itemName = itemInput.attr("id");

    // Check exist error item validate
    if (isExistErrorItem(itemName, errorObjectList)) {
        return;
    }

    itemValue = itemInput.val();

    var errorObj = checkHaftsizeAlphabet(itemName, itemValue);

    if (errorObj != null) {
        errorObjectList.push(errorObj);
    }
}

function validateFullsizeInput(errorObjectList) {
    $('.fullsizeInput').each(function() {
        validateFullsizeForEachInput($(this), errorObjectList);
    });
}

function validateFullsizeForEachInput(itemInput, errorObjectList) {

    var itemValue;
    var itemName = itemInput.attr("id");

    // Check exist error item validate
    if (isExistErrorItem(itemName, errorObjectList)) {
        return;
    }

    itemValue = itemInput.val();

    var errorObj = checkFullSize(itemName, itemValue);

    if (errorObj != null) {
        errorObjectList.push(errorObj);
    }
}

function validateHalftsizeAlphabetNumberInput(errorObjectList) {
    $('.halftsizeAlphabetNumber').each(function() {
        validateHalftsizeAlphabetNumberForEachInput($(this), errorObjectList);
    });
}

function validateHalftsizeAlphabetNumberForEachInput(itemInput, errorObjectList) {

    var itemValue;
    var itemName = itemInput.attr("id");

    // Check exist error item validate
    if (isExistErrorItem(itemName, errorObjectList)) {
        return;
    }

    itemValue = itemInput.val();

    var errorObj = checkHaftsizeAlphabetNumber(itemName, itemValue);

    if (errorObj != null) {
        errorObjectList.push(errorObj);
    }
}

function validateHalftsizeAlphabetNumberSymbolInput(errorObjectList) {
    $('.halftsizeAlphabetNumberSymbol').each(function() {
        validateHalftsizeAlphabetNumberSymbolForEachInput($(this), errorObjectList);
    });
}

function validateHalftsizeAlphabetNumberSymbolForEachInput(itemInput, errorObjectList) {
    
    var itemValue;
    var itemName = itemInput.attr("id");

    // Check exist error item validate
    if (isExistErrorItem(itemName, errorObjectList)) {
        return;
    }

    itemValue = itemInput.val();

    var errorObj = checkHaftSizeAlphabetNumberSymbols(itemName, itemValue);

    if (errorObj != null) {
        errorObjectList.push(errorObj);
    }
    
}

function validateDateFormatInput(errorObjectList) {
    $('.dateFormatInput').each(function() {
        validateDateFormatInputForEachInput($(this), errorObjectList);
    });
}

function validateDateFormatInputForEachInput(itemInput, errorObjectList) {

    var itemValue;
    var itemName = itemInput.attr("id");

    // Check exist error item validate
    if (isExistErrorItem(itemName, errorObjectList)) {
        return;
    }

    itemValue = itemInput.val();

    var errorObj = isValidDate(itemName, itemValue);

    if (errorObj != null) {
        errorObjectList.push(errorObj);
    }
}


function validateDoubleForEachInput(itemInput, errorObjectList) {
    var itemValue;
    var itemName = itemInput.attr("name");

    if (isExistErrorItem(itemName, errorObjectList)) {
        return;
    }

    itemValue = itemInput.val();

    var errorObj = checkHaftsizeDouble(itemName, itemValue);

    if (errorObj != null) {
        errorObjectList.push(errorObj);
    }
}

function validateDoubleInput(errorObjectList) {
    $('.doubleInput').each(function() {
        validateDoubleForEachInput($(this), errorObjectList);
    });
}
function validateEmailInput(errorObjectList) {
    $('.emailFormatInput').each(function() {
        validateEmailInputForEachInput($(this), errorObjectList);
    });
}

function validateEmailInputForEachInput(itemInput, errorObjectList) {

    var itemValue;
    var itemName = itemInput.attr("id");

    // Check exist error item validate
    if (isExistErrorItem(itemName, errorObjectList)) {
        return;
    }

    itemValue = itemInput.val();

    var errorObj = checkEmailFormat(itemName, itemValue);

    if (errorObj != null) {
        errorObjectList.push(errorObj);
    }
}

function checkEmailFormat(itemName, itemValue) {
    var errorObj = null;
    var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

    if (itemValue != null && itemValue.toString().length > 0 &&
        !re.test(itemValue)) {
        errorObj = new Object();
        errorObj.errorId = itemName;

        errorObj.errorMessage = "Email address inputed is invalid.";

    }
    return errorObj;
}

function isExistErrorItem(itemName, errorObjectList) {

    // Check exist error item validate
    if (itemName == null) {
        return true;
    } else {
        if (errorObjectList != null) {
            for (var i = 0; i < errorObjectList.length; i++) {
                if (itemName == errorObjectList[i].errorId) {
                    return true;
                }
            }
        }
    }

    return false;
}

function checkRequiredInput(itemName, itemValue) {
    var errorObj = null;
    if ((itemValue == null || itemValue.toString().length == 0) &&
        itemName != null) {
        errorObj = new Object();
        errorObj.errorId = itemName;
        errorObj.errorMessage = "必須項目です。";
    }
    return errorObj;
}

function checkHaftsizeNumeric(itemName, itemValue) {
    var errorObj = null;
    var isNumericRegex = /^(\d*)$/;;
    if (itemValue != null && itemValue.toString().length > 0 &&
        !isNumericRegex.test(itemValue)) {
        errorObj = new Object();
        errorObj.errorId = itemName;
        errorObj.errorMessage = "半角（数）のみを入力して下さい。"; // "SC203";
    }
    return errorObj;
}

function checkHaftsizeDouble(itemName, itemValue) {
    var errorObj = null;
    var isNumericRegex = /^\d+.?\d*$/;
    if (itemValue != null && itemValue.toString().length > 0 &&
        !isNumericRegex.test(itemValue)) {
        errorObj = new Object();
        errorObj.errorId = itemName;
        errorObj.errorMessage = "半角（ダブル）のみを入力して下さい。"; // "SC203";
    }
    return errorObj;
}

function checkHaftsizeAlphabet(itemName, itemValue) {
    var errorObj = null;
    var isRegex = /^([a-zA-Z]*)$/;
    if (itemValue != null && itemValue.toString().length > 0 &&
        !isRegex.test(itemValue)) {
        errorObj = new Object();
        errorObj.errorId = itemName;
        errorObj.errorMessage = "半角（英）のみを入力して下さい。"; // "SC202";

    }
    return errorObj;
}

function checkHaftsizeAlphabetUppercase(itemName, itemValue) {
    var errorObj = null;
    var isRegex = /^([A-Z]*)$/;
    if (itemValue != null && itemValue.toString().length > 0 &&
        !isRegex.test(itemValue)) {
        errorObj = new Object();
        errorObj.errorId = itemName;
        errorObj.errorMessage = "半角（英）のみを入力して下さい。"; // "SC202";

    }
    return errorObj;
}

function checkHaftsizeAlphabetNumber(itemName, itemValue) {
    var errorObj = null;
    var isRegex = /^[0-9a-zA-Z]+$/; // /^[a-z0-9]+$/
    if (itemValue != null && itemValue.toString().length > 0 &&
        !isRegex.test(itemValue)) {
        errorObj = new Object();
        errorObj.errorId = itemName;
        errorObj.errorMessage = "半角（英/数）のみを入力して下さい。"; // "SC204";

    }
    return errorObj;
}

function checkHaftSizeAlphabetNumberSymbols(itemName, itemValue) {
    var errorObj = null;
    var isRegex = /^[0-9a-zA-Z\-\/\~\!\@\#\$\%\^\&\*\(\)\_\+\`\-\=\'\"\.\,\<\>\?\;]+$/;
    if (itemValue != null && itemValue.toString().length > 0 &&
        !isRegex.test(itemValue)) {
        errorObj = new Object();
        errorObj.errorId = itemName;
        errorObj.errorMessage = "半角（英/数/記号）のみを入力して下さい。"; // "SC205";

    }
    return errorObj;
}

function checkFullSize(itemName, itemValue) {
    var errorObj = null;
    var isRegex = /[\u3000-\u303F]|[\u3040-\u309F]|[\u30A0-\u30FF]|[\uFF00-\uFFEF]|[\u4E00-\u9FAF]|[\u2605-\u2606]|[\u2190-\u2195]|\u203B/g;
    if (!isRegex.test(itemValue)) {
        errorObj = new Object();
        errorObj.errorId = itemName;
        errorObj.errorMessage = "全角のみを入力して下さい。"; // "SC201";

    }
    return errorObj;
}

// Format Date string : YYYY/MM/DD
function isValidDate(itemName, itemValue) {
    var errorObj = null;
    // Parse the date parts to integers
    if(itemValue != ""){
        // First check for the pattern
        if (!/^\d{4}\/\d{1,2}\/\d{1,2}$/.test(itemValue)){
            errorObj = new Object();
            errorObj.errorId = itemName;
            errorObj.errorMessage = "日付が不正です。"; // "SC206";
        }
        
        var parts = itemValue.split("/");
        var day = parseInt(parts[2], 10);
        var month = parseInt(parts[1], 10);
        var year = parseInt(parts[0], 10);

        // Check the ranges of month and year
        if (year < 1000 || year > 3000 || month == 0 || month > 12){
            errorObj = new Object();
            errorObj.errorId = itemName;
            errorObj.errorMessage = "日付が不正です。"; // "SC206";
        }
            

        var monthLength = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

        // Adjust for leap years
        if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))
            monthLength[1] = 29;

        // Check the range of the day
        if (!(day > 0 && day <= monthLength[month - 1])) {
            errorObj = new Object();
            errorObj.errorId = itemName;
            errorObj.errorMessage = "日付が不正です。"; // "SC206";

        }
    }
    
    return errorObj;
};

function hiddenAllErrorMessage() {
    $("label[id$='_err_msg']").each(function() {
        var label = document.getElementById(this.id);
        if (label) {
            label.setAttribute("class", "hidden");
            label.innerText = "";
        }
    });
    $("label[class='error']").each(function() {
        var label = document.getElementById(this.id);
        if (label) {
            label.setAttribute("class", "hidden");
            label.innerText = "";
        }
    });
}

function showAllErrorMessage() {
    if (errorObjectList != null && errorObjectList.length > 0) {    
        $.each(errorObjectList, showErrorMessage);

        $("#totalError_err_msg").text("エラーが " + errorObjectList.length + " 個あります。修正して下さい。");
        $('#totalError_err_msg').attr('class', 'error');
    }
}

function showErrorMessage(i, o) {
    var label = document.getElementById(o.errorId + "_err_msg");
    if (label) {
        label.setAttribute("class", "error");
        label.innerText = o.errorMessage;
    }
}

function validateDataInput() {
    //hidden all Error message before validate
    hiddenAllErrorMessage();
    //reset list error object 
    errorObjectList = [];
    
    //validate data form
    validateRequiredInput(errorObjectList);
    validateNumericInput(errorObjectList);
    validateHalftsizeAlphabetInput(errorObjectList);
    validateFullsizeInput(errorObjectList);
    validateHalftsizeAlphabetNumberInput(errorObjectList);
    validateHalftsizeAlphabetNumberSymbolInput(errorObjectList);
    validateDateFormatInput(errorObjectList);
    validateEmailInput(errorObjectList);
    
    //show error for input
    showAllErrorMessage();
    return (!(errorObjectList != null && errorObjectList.length > 0))
}