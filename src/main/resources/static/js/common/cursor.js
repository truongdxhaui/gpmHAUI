//Next, Prev for Pagging
function functionKey(event) {
    if (SeiKey.isPagerNextPage(event)) {
    	event.preventDefault(); 
        SeiPagerNextPage();
        return false;
    } else if (SeiKey.isPagerLastPage(event)) {
    	event.preventDefault(); 
        SeiPagerLastPage();
        return false;
    } else if (SeiKey.isPagerPrevPage(event)) {
    	event.preventDefault(); 
        SeiPagerPrevPage();
        return false;
    } else if (SeiKey.isPagerFirstPage(event)) {
    	event.preventDefault(); 
        SeiPagerFirstPage();
        return false;
    } if (SeiKey.isSubmitForm(event)) {
    	event.preventDefault(); 
        SeiKeyHandler(event);
        return false;
    } 
    return false;
}

//Next, Prev for detail
function nextAndPrevItemDetail(event) { 
    if(SeiKey.isSubmenuNextData(event)){
    	SubmenuNextData();
	    return false;
	} else if(SeiKey.isSubmenuPrevData(event)){
		SubmenuPrevData();
	    return false;
	}
    return false;
}

//Back 
function backListItem(){
	if(SeiKey.isSubmenuBackList(event)){
		setActionRedirect('#redirect','get',backUrl);
		return false;
	}
	return false;
}

function addEventToFocusById(){
    var lstIdFocus = new Array();
    lstIdFocus = listStrIdFocus.split(",");
    for (var i = 0; i < lstIdFocus.length; i++) {
       $('#' + lstIdFocus[i] + '').attr("onkeydown", "if(!SeikeySelected(event,this)){return false};")
    }
}

function clearAllInputKey(){
	if (event.ctrlKey && event.keyCode == 76) {
		clearAllInputTau();
        return false;
    } 
}