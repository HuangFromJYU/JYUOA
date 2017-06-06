
// 1. 自定义 ToolbarSet
FCKConfig.ToolbarSets["simple"] = [
	['Bold','Italic','Underline'],
	['Link','Unlink'],
	['Image','Smiley','SpecialChar'],
	['FontName'],
	['FontSize'],
	['TextColor','BGColor'],
] ;

FCKConfig.ToolbarSets["bbs"] = [
	['NewPage','RemoveFormat'],
	['Bold','Italic','Underline'],
	['Subscript','Superscript'],
	['JustifyLeft','JustifyCenter','JustifyRight'],
	['Link','Unlink'],
	['Image','Smiley','SpecialChar'],
	['Table'],
	['OrderedList','UnorderedList','-','Outdent','Indent'],
	['FontName'],
	['FontSize'],
	['TextColor','BGColor'],
	['FitWindow']
] ;

FCKConfig.ToolbarSets["admin"] = [
	['Source','DocProps','-','Save','NewPage','Preview','-','Templates'],
	['Cut','Copy','Paste','PasteText','PasteWord','-','Print','SpellCheck'],
	['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
	['Bold','Italic','Underline','StrikeThrough','-','Subscript','Superscript'],
	['OrderedList','UnorderedList','-','Outdent','Indent','Blockquote','CreateDiv'],
	['JustifyLeft','JustifyCenter','JustifyRight','JustifyFull'],
	['Form','Checkbox','Radio','TextField','Textarea','Select','Button','ImageButton','HiddenField'],
	['Link','Unlink','Anchor'],
	['Image','Flash','Table','Rule','Smiley','SpecialChar','PageBreak'],
	['Style','FontFormat','FontName','FontSize'],
	['TextColor','BGColor'],
	['FitWindow','ShowBlocks','-','About']		// No comma for the last row.
] ;

// 是否开启简单功能与高级功能显示
if(typeof(FCKConfig.EnableAdvanceTable) == "undefined"){ // 在页面中调用fckeditor时指定的 EnableAdvanceTable 的值会先被调用。
	FCKConfig.EnableAdvanceTable = false; // 默认为false
}
FCKConfig.AdvanceTableNum = 0;
FCKConfig.AdvanceTable = [1,3,7,8,9,12];

// 2. 添加中文字体与大小
FCKConfig.FontNames 	='宋体;楷体_GB2312;黑体;隶书;Times New Roman;Arial' ;
FCKConfig.FontSizes     ='9/最小;12/较小;16/中等;20/较大;36/最大;54/更大;';

// 3. 修改 "回车" 和 "shift + 回车" 的样式
FCKConfig.EnterMode = 'br' ;			// p | div | br
FCKConfig.ShiftEnterMode = 'p' ;	// p | div | br

// 4. 更换表情图片
FCKConfig.SmileyPath	= FCKConfig.BasePath + 'images/smiley/wangwang/' ; // 表情图片所在的文件夹
// 列出表情图片的文件名
FCKConfig.SmileyImages	= ['0.gif','1.gif','2.gif','3.gif','4.gif','5.gif','6.gif','7.gif','8.gif','9.gif','10.gif','11.gif','12.gif','13.gif','14.gif','15.gif','16.gif','17.gif','18.gif','19.gif','20.gif','21.gif','22.gif','23.gif','24.gif','25.gif','26.gif','27.gif','28.gif','29.gif','30.gif','31.gif','32.gif','33.gif','34.gif','35.gif','36.gif','37.gif','38.gif','39.gif','40.gif','41.gif','42.gif','43.gif','44.gif','45.gif','46.gif','47.gif','48.gif','49.gif','50.gif','51.gif','52.gif','53.gif','54.gif','55.gif','56.gif','57.gif','58.gif','59.gif','60.gif','61.gif','62.gif','63.gif','64.gif','65.gif','66.gif','67.gif','68.gif','69.gif','70.gif','71.gif','72.gif','73.gif','74.gif','75.gif','76.gif','77.gif','78.gif','79.gif','80.gif','81.gif','82.gif','83.gif','84.gif','85.gif','86.gif','87.gif','88.gif','89.gif','90.gif','91.gif','92.gif','93.gif','94.gif','95.gif','96.gif','97.gif','98.gif','test.gif'] ;
FCKConfig.SmileyColumns = 8 ;
FCKConfig.SmileyWindowWidth		= 668 ;
FCKConfig.SmileyWindowHeight	= 480 ;

// 5. 设置允许上传的图片类型的扩展名列表
FCKConfig.ImageUploadAllowedExtensions	= ".(jpg|gif|jpeg|png|bmp)$" ;		// empty for all

// 其它需要修改的配置 ... 
FCKConfig.LinkDlgHideTarget		= true; // false ;
FCKConfig.LinkDlgHideAdvanced	= true; // false ;

FCKConfig.ImageDlgHideLink		= true; // false ;
FCKConfig.ImageDlgHideAdvanced	= true; // false 

FCKConfig.LinkUpload = false;
