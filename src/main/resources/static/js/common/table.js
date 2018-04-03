function firstPagebtn() {
    document.getElementById('page').value = 1;
    $("#searchForm").submit();
}

function lastPagebtn(n) {
    document.getElementById('page').value = n;
    $("#searchForm").submit();
}

function SubmenuNextData() {
    var nextId = document.getElementById("next").value;
    next(nextId);
    return false;
}

function SubmenuPrevData() {
    var prevId = document.getElementById("prev").value;
    prev(prevId);
    return false;
}

function searchSubmit(a) {
    var n = parseInt(a.id);
    document.getElementById('page').value = n;
    $("#searchForm").submit();
}

function pagination(a) {
    var form = document.getElementById('searchForm');
    form.page.value = parseInt(a.id);
    createHiddenInput(form, "paging", true);
    $("#searchForm").submit();
}

function doSort(sortIndex) {
    if ($("#sortBy").val() == sortIndex && $("#sortType").val() == 'ASC') {
        $("#sortType").val('DESC');
    } else {
        $("#sortBy").val(sortIndex);
        $("#sortType").val('ASC');
    }
    var form = document.getElementById('searchForm');
    createHiddenInput(form, "paging", true);
    $("#searchForm").submit();
}



function nextbtn(n) {
    document.getElementById('page').value = ++n;
    $("#searchForm").submit();
}

function prevbtn(n) {
    document.getElementById('page').value = --n;
    $("#searchForm").submit();
}

function next(id) {
    if (id == -1) {
        document.getElementById("nextDetail").disabled = true;
        document.getElementById("nextDetail").style.color = "gray";
    } else {
        doRedirect(id, 'detail');
    }
}

function prev(id) {
    if (id == -1) {
        document.getElementById("prevDetail").disabled = true;
        document.getElementById("prevDetail").style.color = "gray";
    } else {
        doRedirect(id, 'detail');
    }
}

function nextDetail(id, action) {
    if (id == -1) {
        document.getElementById("nextDetail").disabled = true;
        document.getElementById("nextDetail").style.color = "gray";
    } else {
        doRedirect(id, action);
    }
}

function prevDetail(id, action) {
    if (id == -1) {
        document.getElementById("prevDetail").disabled = true;
        document.getElementById("prevDetail").style.color = "gray";
    } else {
        doRedirect(id, action);
    }
}

function SeiPagerNextPage() {
    var curPage = parseInt(document.getElementById('curPage').value);
    var cntPage = parseInt(document.getElementById('cntPage').value);
    if (curPage != cntPage)
        nextbtn(curPage);
    return false;
}
function SeiPagerLastPage() {

    var cntPage = parseInt(document.getElementById('cntPage').value);
    lastPagebtn(cntPage);
    return false;
}
function SeiPagerPrevPage() {
    var curPage = parseInt(document.getElementById('curPage').value);
    if (curPage != 1) {
        prevbtn(parseInt(curPage));
    }
    return false;
}

function SeiPagerFirstPage() {
    firstPagebtn();
    return false;
}

/* MinhLS create hidden input*/
function createHiddenInput(form, name, value) {
    var input = document.createElement('input');
    input.type = 'hidden';
    input.name = name;
    input.value = value;
    form.appendChild(input);
}
