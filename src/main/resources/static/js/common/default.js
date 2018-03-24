function scroll_onload(){
	document.getElementById('SeiMainBg').style.width = '97%';
	document.body.style.width = '97%';
 	window.onresize = null;
}
function SetValueCBNotClear(x_field, x_value) {
  if (x_field == null) {
    return;
  }
  if (x_field.type == 'hidden') {
    x_field.value = x_value;
    return;
  }
  var p_values = x_value.split(',');
  if (x_field.length == 1) {
    x_field.checked = false;
    for (var j=0; j<p_values.length; j++) {
      if (x_field.value == p_values[j]) { x_field.checked = true; break; }
    }
    return;
  }
  if (x_field.length) {
    for (var i=0; i<x_field.length; i++) {
      for (var j=0; j<p_values.length; j++) {
        if (x_field[i].value == p_values[j]) { x_field[i].checked = true; break; }
      }
    }
    return;
  }
  if ((x_field.type == 'checkbox' || x_field.type == 'radio') && x_field.value == x_value) {
    x_field.checked = true;
  } else {
    x_field.checked = false;
  }
}
