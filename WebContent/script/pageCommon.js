
//******************************************************
/**
* 初使化窗口时，根据父窗口传的值设置窗口标题
*/
//******************************************************
function setTopTitle() {
	if (document.homeForm.topTitle.value != "") {
		top.document.title = document.homeForm.topTitle.value;
	}
}

function showAlert() {
	if (document.homeForm.alertMsg.value != "") {
		alert(document.homeForm.alertMsg.value);
		document.homeForm.alertMsg.value = "";
	}
}

function openNewUrl() {
	if (document.homeForm.newUrl.value != "") {
		openUrl(document.homeForm.newUrl.value);
		document.homeForm.newUrl.value = "";
	}
}

function openUrl(urlStr) {
	window.open(urlStr, 'newWindow',
			   'toolbar=yes,location=yes,directories=yes,status=yes,menubar=yes,scrollbars=yes,resizable=yes');
}

function openNewWindow() {
	if (document.homeForm.newWindow.value != "") {
	    var varOption = "toolbar=no,location=no,status=yes,menubar=no,resizable=yes,scrollbars=yes,width=" + screen.availWidth + ",height=" + screen.availHeight + ",left=0,top=0";
	    window.open(document.homeForm.newWindow.value, 'autoOpenWin', varOption);
	    document.homeForm.newWindow.value = "";
	}
}

function showConfirm() {
	if (document.homeForm.confirmMsg.value != "") {
		if (confirm(document.homeForm.confirmMsg.value)) {
    			document.homeForm.submit();
		}
		document.homeForm.confirmMsg.value = "";
	}
}


function writeCookie(str) {
	var expdate = new Date();
	SetCookie("oaDesktop", str, expdate, "/");
}

function SetCookie (nameValue, valueValue) {
	var argv = SetCookie.arguments;
	var argc = SetCookie.arguments.length;
	var expires = (argc > 2) ? argv[2] : null;
	var path = (argc > 3) ? argv[3] : null;
	var domain = (argc > 4) ? argv[4] : null;
	var secure = (argc > 5) ? argv[5] : false;
	document.cookie = nameValue + "=" + escape (valueValue) +
		((expires == null) ? "" : ("; expires=" + expires.toGMTString())) +
		((path == null) ? "" : ("; path=" + path)) +
		((domain == null) ? "" : ("; domain=" + domain)) +
		((secure == true) ? "; secure" : "");
}

function printDOC() {
	var hkey_root,hkey_path,hkey_key;
	hkey_root="HKEY_CURRENT_USER";
	hkey_path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
	try{
	  var RegWsh = new ActiveXObject("WScript.Shell") ;
	  hkey_key="header" ;
	  RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"") ;
	  hkey_key="footer" ;
	  RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"") ;
	  }
	 catch(e){}
	window.print();
}

function privewDOC() {
	var hkey_root,hkey_path,hkey_key;
	hkey_root="HKEY_CURRENT_USER";
	hkey_path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
	try{
	  var RegWsh = new ActiveXObject("WScript.Shell") ;
	  hkey_key="header" ;
	  RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"") ;
	  hkey_key="footer" ;
	  RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"") ;
	  }
	 catch(e){}
	var obj = document.all.WebBrowser;
	
 	obj.ExecWB(7,1);
 	
}


function getFrameHeight() {
	var obj = document.all("msgFrame");
	if (obj == null || obj.style == undefined) return true;
	var formHeight = document.body.clientHeight;
	obj.style.height = formHeight - 210;
}

window.onresize = getFrameHeight

var hrefBaseValue = null;

//去除主机地址
function removeLocationAddress(html) {
	var hostAdd = window.location.protocol + "//" + window.location.host;
	var findSite = html.indexOf(hostAdd);
	while(findSite > -1) {
		html = html.substring(0, findSite) + html.substring(findSite + hostAdd.length);
		findSite = html.indexOf(hostAdd);
	}
	return html;
}
// ==============================================




//******************************************************
/**
* 删除/修改 数据前的检查与提示
*/
//******************************************************
function delConfirm( message ){
    if(message == null){
        message = "您确定要删除本记录吗？";
    }
    return window.confirm(message);
}

function beforeDelete( linkElement ) {
	if(!checkSelectedData(true)){ // 可以选择多条数据
		return false;	
	}
	
	// 得到选中数据的value值，并拼接成请求参数字符串，然后替换相应的<a>的href值，加上相应的id值的请求参数
	generateParamStrAndReplaceHref(linkElement, "id", getSelectedDataValues());
	
	return confirm("确实要删除数据吗？");
}
function beforeEdit( linkElement ){
	if(!checkSelectedData(false)){ // 只能选择一条数据
		return false;	
	}
	
	// 得到选中数据的value值，并拼接成请求参数字符串，然后替换相应的<a>的href值，加上相应的id值的请求参数
	generateParamStrAndReplaceHref(linkElement, "id", getSelectedDataValues());
	
	return true;
}
var beforeOpSingal = beforeEdit; // 一个函数，两个名称
/* 
* 检查是否选择了数据
* @param bAllowSelectMultiple 布尔型，指定是否允许选中多条数据
*/
function checkSelectedData( bAllowSelectMultiple ){
	var count = $("#TableData").find("input[type=checkbox]:checked").size();
	if(count == 0){
		alert("请选择要操作的数据");
		return false;	
	}
	if(!bAllowSelectMultiple && count > 1){
		alert("此项操作只能选择一条数据");
		return false;
	}
	return true;
}
// 返回选中的数据的value值数组
function getSelectedDataValues(){
	var $selectedData = $("#TableData").find("input[type=checkbox]:checked");
	var valueArray = new Array();
	$selectedData.each(function(){
		valueArray.push(this.value);						
	});
	return valueArray;
}
/*
* 根据参数值数组，拼接成请求参数字符串，并替换相应link的href属性为新拼接的url。
* 所使用的基地址为linkElement指定的href属性值
* @param linkElement 相应的<a>元素对象
* @param paramName 参数名
* @param paramValueArrya 参数值数组
*/
function generateParamStrAndReplaceHref(linkElement, paramName, paramValueArray){ 
	// 生成参数字符串
	var paramStr = "";
	for(var i =0; i < paramValueArray.length; i++){
		paramStr += ("&" + paramName + "=" + paramValueArray[i]);
	}
	
	// 如果url中没有'?'，就把第一个参数前的'&'改为'?'
	var linkUrl = linkElement.href;
	if(linkUrl.indexOf("?") == -1){ 
		paramStr = paramStr.replace(/^&/, "?");
	}
	
	// 再修改删除链接的url，加上这段参数
	linkElement.href = linkUrl + paramStr;
}

//******************************************************
/**
* 全择/全不选 所有
*/
//******************************************************
function checkAll( selectedValue ) {
	if( document.all.dataItem == null ){ // 页面中没有数据
		return; 
	}
	var count = document.all.dataItem.length; 
	if(count == null){ // 页面中只有一个数据时（document.all.dataItem就是name="dataItem"的这个checkbox对象，不是数组）
		document.all.dataItem.checked = selectedValue;
	}
	else{ // 页面中有多个数据时（document.all.dataItem就是name="dataItem"的checkbox对象的数组）
		for (var i = 0; i < count; i++) {
			document.all.dataItem[i].checked = selectedValue;
		}
	}
	
	// 让上下两个全选框的状态一致
	document.all.selectAll[0].checked = selectedValue;
	document.all.selectAll[1].checked = selectedValue;
}


//******************************************************
/**
* 单击数据行选中并高亮
* 在页面中的checkbox中要设置onClick="window.event.cancelBubble=true"。是希望在单击了checkbox后，不再继续向上触发事件（向上一层层的触发onClick事件）。否则将要调用下面这个方法，就会只选中一个数据了。
*/
//******************************************************
var $lastCheckedTr = null;
var lastCheckedTrClassName;
function content_onclick( oTr ) {
    if(true){
        return; 
    }

    // 取消所有已选中的checkbox（数据项和全选框）
	$("input[name=selectAll]").attr("checked", false);
	$("input[name=dataItem]").attr("checked", false);
	
	// 还原上次选中的checkbox行的class
	if($lastCheckedTr != null){ 
		$lastCheckedTr.attr("class", lastCheckedTrClassName);
	}
	// 再记录这次选中行的信息（以备下次还原用）
	$lastCheckedTr = $(oTr);
	lastCheckedTrClassName = $lastCheckedTr.attr("class");
	
	// 再选中当前checkbox
    var $cb = $(oTr).find("input[type=checkbox]");
	$cb.attr("checked", true);
	
	// 改变当前选中行的样式（高亮）
	$lastCheckedTr.attr("class", "selectLine");
}



//******************************************************
/**
* 输入框内的提示文字显示与设置
* 注：代码$(inputElement).attr("showText")处，没有使用inputElement.showText。因为后者在firefox下不能运行，所以采用jQuery框架处理。
*/
//******************************************************
function inputAreaClick(inputElement, showText) {
	if(showText == null){
		showText = $(inputElement).attr("showText");
	}
	if (inputElement.value == showText) {
		inputElement.value = "";
	}
	inputElement.select;
}

function inputAreaBlur(inputElement, showText) {
	if (inputElement.value == "") {
		if(showText == null){
			showText = $(inputElement).attr("showText");	
		}
		inputElement.value = showText;
	}
}

//******************************************************
/**
* 页面加载完成后要执行的操作
*/
//******************************************************
$(function(){
	
	// 让主窗口的title与当前操作的（右侧的）窗口的title一致
	parent.document.title = "JYU OA - " + document.title;
	
//	// 在文本框中显示提示语，提示语由 showText 属性指定
//	$("input[type=text][showText]").each(function(){
//		this.value = $(this).attr("showText");
//	});

	// 设置所有的文本框在选中时，选中文本框中的文本
	$("input[type=text]").each(function(){;
		// this.onFocus = function() { this.select(); }; // 用这行代码不起作用!?
		$(this).focus(function(){ // 改用jQuery实现
			this.select();
		});
	});

	// 
});


/***********************************************************************************
 *** 在模态窗口中使用<base target="_self">，不然点击超链接会打开一个新的IE窗口。
 *** 在模态窗口中使用window.dialogArguments来获取传递的参数。
 ***********************************************************************************/

/**
 * 打开显示HTML的模态对话框。被打开后就会始终保持输入焦点。除非对话框被关闭，否则用户无法切换到主窗口。
 * @param url
 */
function myShowModalDialog(url, width, height) {
    var arguments = window;

    if (!width) width = 350;
    if (!height) height = 350;
    var left = (screen.width - width) / 2;
    var top = (screen.height - height) / 2;
    var features = "" //
        // + "dialogLeft:" + left + ";"//           左边距
        // + "dialogTop:" + top + ";"//             上连距
            + "dialogWidth:" + width + "px;"//   宽度
            + "dialogHeight:" + height + "px;"// 高度
            + "center: yes;"//                    是否居中
            + "resizable: yes;"//                是否可以改变大小
            + "scroll: yes;"//                    当内容超过窗口大小时是否显示滚动条
            + "status: yes;"//                    是否显示状态栏

    window.showModalDialog(url, arguments, features);
}
/**
 * 打开显示HTML的非模态对话框。被打开后，用户可以随机切换输入焦点。
 * @param url
 */
function myShowModelessDialog(url, width, height) {
    var arguments = window;

    if (!width) width = 350;
    if (!height) height = 350;
    var left = (screen.width - width) / 2;
    var top = (screen.height - height) / 2;
    var features = "" //
        // + "dialogLeft:" + left + ";"//           左边距
        // + "dialogTop:" + top + ";"//             上连距
            + "dialogWidth:" + width + "px;"//   宽度
            + "dialogHeight:" + height + "px;"// 高度
            + "center: yes;"//                    是否居中
            + "resizable: yes;"//                是否可以改变大小
            + "scroll: yes;"//                    当内容超过窗口大小时是否显示滚动条
            + "status: yes;"//                    是否显示状态栏

    window.showModelessDialog(url, arguments, features);
}

/**
 * 打开新窗口，如果指定了大小，则窗口显示在屏幕的中央
 *
 * url        String  被加载的HTML文档的 URL 地址
 * name       String  打开的窗口的名字
 * width      Number  窗口的宽度
 * height     Number  窗口的高度
 * scrollbars Boolean 是否有滚动条
 */
function myOpenWindow(url, width, height, name, scrollbars) {
    var left = (screen.width - width) / 2;
    var top = (screen.height - height) / 2;

    var features = "left=" + left
            + ",top=" + top
            + ",width=" + width
            + ",height=" + height
            + ",scrollbars=" + (scrollbars ? "yes" : "no")
            + ",center=yes"
            + ",resizable=yes"
            + ",status=yes";
    var openwin = window.open(url, name, features);
    openwin.focus();
    return openwin;
}
