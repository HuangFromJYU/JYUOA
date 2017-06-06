/**
 * 加载js脚本文件
 * @param url
 */
function loadScript(url){
    document.write('<script type="text/javascript" src="' + url + '" charset="utf-8"></script>');
}

/**
 * 加载css样式文件
 * @param url
 */
function loadStyle(url){
    document.write('<link rel="stylesheet" type="text/css" href="' + url + '" charset="utf-8">');
}

/***********************************************************************************
 ***  获取应用的根路径
 ***  这个方法应在 loadScript 与 loadStyle 之前声明，因为他们要用到这个方法
 ***********************************************************************************/
function getBasePath(webAppFeatures) {
    if(webAppFeatures == null){
		webAppFeatures = '/pages';
	}

    var BasePath;
    if (document.location.protocol == 'file:')
    {
        BasePath = decodeURIComponent(document.location.pathname.substr(1));
        BasePath = BasePath.replace(/\\/gi,'/');

        // The way to address local files is different according to the OS.
        // In Windows it is file:// but in MacOs it is file:/// so let's get it automatically
        var sFullProtocol = document.location.href.match(/^(file\:\/{2,3})/)[1] ;

        // BasePath = sFullProtocol + BasePath.substring(0, BasePath.lastIndexOf('/') + 1);
        BasePath = sFullProtocol + BasePath.substring(0,BasePath.lastIndexOf(webAppFeatures) + 1);
    }
    else {
        BasePath = document.location.protocol + '//' + document.location.host +
                   document.location.pathname.substring(0,document.location.pathname.lastIndexOf('/') + 1);
    }

    return BasePath;
}

/**************************************************
 *** 获取当前页面的名称（不含扩展名）
 **************************************************/
function getCurrentPageBaseName() {
    // URL中可能使用'/'，也可能使用'\'做分隔符
    var tokens = document.URL.split("//")[1].replace(/\\/g,"/").split("/");
    var lastToken = tokens[tokens.length - 1];

    var fileName = lastToken.split(".")[0] ;
    return fileName;
}

/**
 * 当前网页的目录路径
 */
function getCurrentPageBasePath() {
    var BasePath;
    if (document.location.protocol == 'file:')
    {
        BasePath = decodeURIComponent(document.location.pathname.substr(1));
        BasePath = BasePath.replace(/\\/gi,'/');

        // The way to address local files is different according to the OS.
        // In Windows it is file:// but in MacOs it is file:/// so let's get it automatically
        var sFullProtocol = document.location.href.match(/^(file\:\/{2,3})/)[1] ;

        BasePath = sFullProtocol + BasePath.substring(0,BasePath.lastIndexOf('/') + 1);
    }
    else {
        BasePath = document.location.protocol + '//' + document.location.host +
                   document.location.pathname.substring(0,document.location.pathname.lastIndexOf('/') + 1);
    }
    return BasePath;
}

/***********************
 *** 取参数
 ***********************/
function getRequestParameter( name ){
	var params = getRequestParameters();
	return params[name];
}
 
function getRequestParameters() {
    // 获取参数部分，并进行url解码
    var paramsStr = decodeURIComponent(document.location.search);
    if(paramsStr.length <=1 ){
        // 没有参数
        return {};
    }

    // 最后如果带有&，就忽略掉。如 ?a=b& 变为 a=b
    var params = paramsStr.replace(/^\?(.*?)&?$/g,"{ $1' }");
    params = params.replace(/=/g,": '");
    params = params.replace(/&/g,"', ");

    try {
        eval( "var paramsMap = " + params);
        return paramsMap;
    }
    catch (e) {
        var msg = "解析参数时出错：\n";
        msg += "原参数字符串  ：" + paramsStr + "\n";
        msg += "解析后的字符串：" + params + "\n";
        msg += "异常信息：" + e;
        alert(msg);
    }
}
