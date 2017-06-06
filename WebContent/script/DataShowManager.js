
/*************************************
 ***  在网页加载完成后加载演示数据
 *************************************/
 $(document).ready(function() {
	// 加载演示数据
	loadDemoData();
});

/************************************************************
 *** 相关方法
 ************************************************************/

/**
 * 加载演示数据，默认是放到 "[class=dataContainer]" 元素中
 * @param demoDataContainerExpression 存放演示数据的元素（容器）
 *                                   默认为 [class=dataContainer]
 * @param templateClass 显示一条数据的模板代码
 *									 默认为template
 */
function loadDemoData(demoDataContainerExpression, templateClass) {
    // 存放演示数据的元素（容器）
	if(demoDataContainerExpression == null){
		demoDataContainerExpression = ".dataContainer";
	}
	
	if(templateClass == null){
		templateClass = "template";		
	}
		
	// 没有存放演示数据的容器时，就不需要后面的显示演示数据了
	var jDemoDataContainerArrays = $(demoDataContainerExpression);
    if (jDemoDataContainerArrays.size() == 0) {
         return;
    }
	
	// 显示演示数据（可能有多处要显示）
	jDemoDataContainerArrays.each(function(){
		var jDemoDataContainer = $(this);
		
		// 如果没有指定要显示的数据项，则提示
		var dataKey = jDemoDataContainer.attr("dataKey");
		if(dataKey == null || $.trim(dataKey).length == 0){
			alert("请指定要显示的数据项（指定 dataKey 属性为 DemoData.js 中的一项数据的名称）！");
			return;
		}
	
		// 
		var items = demoData[dataKey];
		if(items == null){
			alert("请指定正确的数据项（指定 dataKey 属性为 DemoData.js 中的一项数据的名称）！");
			return;
		}
		
		// 进行替换
		$.each(items, function(index) {
			// 返回 'false' 将停止循环 (就像在普通的循环中使用 'break')。
			// 返回 'true' 跳至下一个循环(就像在普通的循环中使用'continue')
			return addDemoRecord(jDemoDataContainer, items[index], templateClass);
		});
	
		 // 显示完演示数据后移除当前所用的模板行
		jDemoDataContainer.find("." + templateClass).remove(); 
		
	});
}

/**
 * 添加一条演示数据，演示的数据行的class为“demodata_record”
 * @param jDemoDataContainer
 * @param record
 * @param templateClass
 */
function addDemoRecord(jDemoDataContainer, record, templateClass) {
	// 找出模板行
	var jTemplateRecord = jDemoDataContainer.find("." + templateClass);
    if (jTemplateRecord.size() == 0) {
        alert("没有模板行，模板的class为：" + templateClass);
        return false;
    }

    // clone后的元素要removeClass，因为模板行（class=${tempateClass}）是不显示的
    var jNewRecord = jTemplateRecord.clone(true);
    jNewRecord.removeClass(templateClass);
    jNewRecord.addClass("demodata_record");
    var newRecord = jNewRecord[0].outerHTML;
                                  
    for (var key in record) {
        //TODO 要使用 '\\$' 表示字符 '$'，为什么不是 '\$'？
        var regex = new RegExp("\\$\{" + key + "\}","g");
        newRecord = newRecord.replace(regex,record[key]);
    }

    jDemoDataContainer.append(newRecord);
    return true;
}

/*********************************************************************************
 *** outterHTML 在FireFox里就不行了，因为outerHTML不是W3C的标准属性
 *** 网上找了下使用DOM原型扩展方法解决，代码如下：
 *********************************************************************************/
if (typeof(HTMLElement) != "undefined" && !window.opera)
{
    HTMLElement.prototype.__defineGetter__("outerHTML",function()
    {
        var a = this.attributes, str = "<" + this.tagName, i = 0;
        for (; i < a.length; i++)
            if (a[i].specified)
                str += " " + a[i].name + '="' + a[i].value + '"';
        if (!this.canHaveChildren)
            return str + " />";
        return str + ">" + this.innerHTML + "</" + this.tagName + ">";
    });
    HTMLElement.prototype.__defineSetter__("outerHTML",function(s)
    {
        var r = this.ownerDocument.createRange();
        r.setStartBefore(this);
        var df = r.createContextualFragment(s);
        this.parentNode.replaceChild(df,this);
        return s;
    });
    HTMLElement.prototype.__defineGetter__("canHaveChildren",function()
    {
        return !/^(area|base|basefont|col|frame|hr|img|br|input|isindex|link|meta|param)$/.test(this.tagName.toLowerCase());
    });
}
