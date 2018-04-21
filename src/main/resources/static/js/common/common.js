
/* create post form element*/
function createPostFormSubmit(action) {
    var form = document.createElement('form');
    form.name = "formRedirect";
    form.action = action;
    form.method = 'POST';

    document.body.appendChild(form);

    return form;
}

/* MinhLS create hidden input*/
function createHiddenInput(form, name, value) {
    var input = document.createElement('input');
    input.type = 'hidden';
    input.name = name;

    input.value = value;
    form.appendChild(input);
}

// MinhLS
function doRedirect(action, name, val) {
    var form = createPostFormSubmit(action);
    createHiddenInput(form, name, val);
    form.submit();
}