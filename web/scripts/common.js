function isDate(s) {
	return /\d{4}\-\d{2}\-\d{2}\s\d{2}:\d{2}:\d{2}/g.test(s);
}

function isSimpleDate(s) {
	return /\d{4}\-\d{2}\-\d{2}/g.test(s);
}

function reverseCheckbox(boxname) {
	var checkboxes = document.getElementsByName(boxname);
	for(var i=0; i<checkboxes.length; i++) {
		if(checkboxes[i].checked) {
			checkboxes[i].checked=false;
		} else {
			checkboxes[i].checked=true;
		}
	}
}

function checkAll(boxname) {
	var checkboxes = document.getElementsByName(boxname);
	for(var i=0; i<checkboxes.length; i++) {
		checkboxes[i].checked=true;
	}
}