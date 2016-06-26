/*
 * 判断用户使用的浏览器 因为ie，360和chrome，firefox下的window.location.href解读不一样
 */
function judge() {
	var browser = navigator.userAgent;
	// alert(browser);
	if (browser.indexOf("Chrome") != -1 || browser.indexOf("Firefox") != -1) {
//		alert("是firefox  和 chrome");
		return 1;
	} else {
//		alert("是ie");    //或者360
		return 0;
	}
}