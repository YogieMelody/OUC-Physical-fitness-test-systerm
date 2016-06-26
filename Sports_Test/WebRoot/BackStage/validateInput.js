function depInsert() {
	var insertDepartmentName = document
			.getElementsByName("insertDepartmentName")[0].value;
	if (insertDepartmentName == "") {
		alert("请勿提交空表单");
		return false;
	}
}

function depUpdate() {
	var updateDepartmentId = document.getElementsByName("updateDepartmentId")[0].value;
	if (updateDepartmentId == 0) {
		alert("请勿盲目提交");
		return false;
	}

	var newDepartmentName = document.getElementsByName("newDepartmentName")[0].value;
	if (newDepartmentName == "") {
		alert("请勿提交空表单");
		newDepartmentName.focus();
		return false;
	}

	var reg = /^[\u4e00-\u9fa5]*$/;
	var newDepartmentName = document.getElementsByName("newDepartmentName")[0];
	if (!(reg.test(newDepartmentName.value))) {
		alert("必须由中文组成");
		newDepartmentName.focus();
		return false;
	} else {
		return true;
	}
}

function notInsert() {
	var insertNoticeTitle = document.getElementsByName("insertNoticeTitle")[0].value;
	var insertNoticeContent = document.getElementsByName("insertNoticeContent")[0].value;
	if (insertNoticeContent == "") {
		alert("请填写完整");
		return false;
	}
	if (insertNoticeTitle == "") {
		alert("请填写完整");
		return false;
	}
}

function notUpdate() {
	var updateNoticeId = document.getElementsByName("updateNoticeId")[0].value;
	if (updateNoticeId == 0) {
		alert("请勿盲目提交");
		return false;
	}

	var updateNoticeTitle = document.getElementsByName("updateNoticeTitle")[0].value;
	if (updateNoticeTitle == "") {
		alert("请填写公告标题");
		return false;
	}

	var updateNoticeContent = document.getElementsByName("updateNoticeContent")[0].value;
	if (updateNoticeContent == "") {
		alert("请填写公告内容");
		return false;
	}
}

function getRightTimeInsert(val) {
	if (val == "12") {
		document.insertForm.time.length = 1;
		document.insertForm.time.options[0].value = "8:00-9:30";
		document.insertForm.time.options[0].text = "8:00-9:30";
	} else if (val == "34") {
		document.insertForm.time.length = 1;
		document.insertForm.time.options[0].value = "10:00-11:30";
		document.insertForm.time.options[0].text = "10:00-11:30";
	} else if (val == "56") {
		document.insertForm.time.length = 1;
		document.insertForm.time.options[0].value = "13:30-15:00";
		document.insertForm.time.options[0].text = "13:30-15:00";
	} else {
		document.insertForm.time.length = 1;
		document.insertForm.time.options[0].value = "15:30-17:00";
		document.insertForm.time.options[0].text = "15:30-17:00";
	}
}
function getRightTimeUpdate(val) {
	if (val == "12") {
		document.updateForm.updateTime.length = 1;
		document.updateForm.updateTime.options[0].value = "8:00-9:30";
		document.updateForm.updateTime.options[0].text = "8:00-9:30";
	} else if (val == "34") {
		document.updateForm.updateTime.length = 1;
		document.updateForm.updateTime.options[0].value = "10:00-11:30";
		document.updateForm.updateTime.options[0].text = "10:00-11:30";
	} else if (val == "56") {
		document.updateForm.updateTime.length = 1;
		document.updateForm.updateTime.options[0].value = "13:30-15:00";
		document.updateForm.updateTime.options[0].text = "13:30-15:00";
	} else {
		document.updateForm.updateTime.length = 1;
		document.updateForm.updateTime.options[0].value = "15:30-17:00";
		document.updateForm.updateTime.options[0].text = "15:30-17:00";
	}
}